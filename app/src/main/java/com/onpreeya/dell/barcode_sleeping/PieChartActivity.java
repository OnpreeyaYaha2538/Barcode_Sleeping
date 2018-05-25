package com.onpreeya.dell.barcode_sleeping;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.LargeValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;
import com.onpreeya.dell.barcode_sleeping.dao.DataChartStrudentCheckDao;
import com.onpreeya.dell.barcode_sleeping.manager.HttpManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PieChartActivity extends AppCompatActivity {

    BarChart barChart;
    private BarChart chart;
    float barWidth;
    float barSpace;
    float groupSpace;
    ArrayList yVals1 = new ArrayList();
    ArrayList yVals2 = new ArrayList();
    private String actId = "";
    private String typeSelect = "";
    private String actName = "";
    private String yearSelect = "";
    private Button detail1;
    private Button detail2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);



        chart = (BarChart) findViewById(R.id.bar_chart);
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            actId = bundle.getString("id");
            typeSelect = bundle.getString("typeSelect");
            actName = bundle.getString("actName");
            yearSelect = bundle.getString("yearSelect");
        }

        Log.d("PieValues" , " :: actId = "+actId
        +" typeSelect = "+typeSelect
        +" actName = "+actName
        +" yearSelect = "+yearSelect);

        callServiceChart(actId);

        detail1 = (Button) findViewById(R.id.detail1);
        detail1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PieChartActivity.this, StudentActivity.class);
                intent.putExtra("id", actId);
                intent.putExtra("checkStudentList","check");
                startActivity(intent);
            }
        });
        detail2 = findViewById(R.id.detail2);
        detail2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PieChartActivity.this,StudentActivity.class);
                intent.putExtra("id", actId);
                intent.putExtra("checkStudentList","notcheck");
                startActivity(intent);
            }
        });

    }

    private void callServiceChart(String actId) {
        Call<List<DataChartStrudentCheckDao>> call = HttpManager.getInstance().getService().getBarChart(actId);
        call.enqueue(new Callback<List<DataChartStrudentCheckDao>>() {
            @Override
            public void onResponse(Call<List<DataChartStrudentCheckDao>> call, Response<List<DataChartStrudentCheckDao>> response) {
                if(response.isSuccessful()){
                    List<DataChartStrudentCheckDao> dao = response.body();
                    Log.d("callServiceChart ::","if"+dao.size());
                    for(int i = 0 ; i < dao.size() ; i++){
                        String numStu = dao.get(i).getNumStu();
                        String numStuNotin = dao.get(i).getNumStuNotin();
                        yVals1.add(new BarEntry(i+1, (float) Integer.parseInt(numStu)));
                        yVals2.add(new BarEntry(i+1, (float) Integer.parseInt(numStuNotin)));
                    }
                    getBarChart();
                }else{
                    try {
                        Log.d("callServiceChart ::","else "+response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<List<DataChartStrudentCheckDao>> call, Throwable t) {
                Log.d("callServiceChart ::","onFailure"+t);
            }
        });
    }

    private void getBarChart() {
        barWidth = 0.3f;
        barSpace = 0f;
        groupSpace = 0.4f;
        chart.setDescription(null);
        chart.setPinchZoom(false);
        chart.setScaleEnabled(false);
        chart.setDrawBarShadow(false);
        chart.setDrawGridBackground(false);

        int groupCount = 6;

        ArrayList xVals = new ArrayList();

        xVals.add("ปี 1");
        xVals.add("ปี 2");
        xVals.add("ปี 3");
        xVals.add("ปี 4");
        
        yVals1.add(new BarEntry(2, (float) 3));
        yVals2.add(new BarEntry(2, (float) 4));
        yVals1.add(new BarEntry(3, (float) 5));
        yVals2.add(new BarEntry(3, (float) 6));
        yVals1.add(new BarEntry(4, (float) 7));
        yVals2.add(new BarEntry(4, (float) 8));


        BarDataSet set1, set2;
        set1 = new BarDataSet(yVals1, "เข้าร่วมกิจกรรม");
        //set1.setColor(R.color.colorPrimary);
        set1.setColor(Color.BLUE);
        set1.setValueTextSize(12);
        set2 = new BarDataSet(yVals2, "ไม่เข้าร่วมกิจกรรม");
        //set2.setColor(R.color.colorDetail);
        set2.setColor(Color.RED);
        set2.setValueTextSize(12);
        BarData data = new BarData(set1, set2);
        data.setValueFormatter(new LargeValueFormatter());
        chart.setData(data);
        chart.getBarData().setBarWidth(barWidth);
        chart.getXAxis().setAxisMinimum(0);
        chart.getXAxis().setAxisMaximum(0 + chart.getBarData().getGroupWidth(groupSpace, barSpace) * groupCount);
        chart.groupBars(0, groupSpace, barSpace);
        chart.getData().setHighlightEnabled(false);
        chart.invalidate();

        Legend l = chart.getLegend();
        l.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        l.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);
        l.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        l.setDrawInside(true);
        l.setYOffset(20f);
        l.setXOffset(0f);
        l.setYEntrySpace(0f);
        l.setTextSize(8f);

        //X-axis
        XAxis xAxis = chart.getXAxis();
        xAxis.setGranularity(1f);
        xAxis.setGranularityEnabled(true);
        xAxis.setCenterAxisLabels(true);
        xAxis.setDrawGridLines(false);
        xAxis.setAxisMaximum(4);
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xVals));
        //Y-axis
        chart.getAxisRight().setEnabled(false);
        YAxis leftAxis = chart.getAxisLeft();
        leftAxis.setValueFormatter(new LargeValueFormatter());
        leftAxis.setDrawGridLines(true);
        leftAxis.setSpaceTop(35f);
        leftAxis.setAxisMinimum(0f);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.action_grap){
            //Go Grap this is value if u want actId,typeSelect,actName
            Intent intent = new Intent(this, PieChartActivity.class);
            intent.putExtra("id", actId);
            intent.putExtra("typeSelect",typeSelect);
            intent.putExtra("actName",actName);
            intent.putExtra("yearSelect",yearSelect);
            startActivity(intent);
            return true;
        }
        else if(item.getItemId() == R.id.action_home){
            Intent intent = new Intent(this, MenuActivity.class);
            startActivity(intent);
            return true;
        }
//        else if(item.getItemId() == R.id.action_check){
//        Intent intent = new Intent(this, MainActivity.class);
//        startActivity(intent);
//        return true;
//
//        }
        return super.onOptionsItemSelected(item);
    }
}

