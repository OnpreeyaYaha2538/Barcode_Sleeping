package com.onpreeya.dell.barcode_sleeping;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.mikephil.charting.charts.PieChart;
import com.onpreeya.dell.barcode_sleeping.dao.ActivityDao;
import com.onpreeya.dell.barcode_sleeping.manager.HttpManager;
import com.onpreeya.dell.barcode_sleeping.manager.http.ActivityListDao;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.http.GET;

public class MenuActivity extends AppCompatActivity implements View.OnClickListener {

    final String TAG = "MenuActivity";


   //private Button action;
   private Spinner spinYear;
   private Spinner spinOrga;
   private String typeSelect;
   private String yearSelect;
   //private Spinner spinSubOrga;
   private TextView text;
   private Button pieChart;
   private ArrayList<String> spinYearList = new ArrayList<String>();
   private ArrayList<String> spinOrgaList = new ArrayList();

   RecyclerView rvItem;
    PieChart chart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        initInstand();
        serviceActivity();
       // action.setOnClickListener(this);
         setspinner();

        rvItem = (RecyclerView)findViewById(R.id.rvItem);
        rvItem.setHasFixedSize(true);

        LinearLayoutManager manager = new LinearLayoutManager(this);

        rvItem.setLayoutManager(manager);
        //pieChart = findViewById(R.id.pie_chart);

//        pieChart.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void o
// nClick(View view) {
//                Intent intent = new Intent(MenuActivity.this, PieChartActivity.class);
//                startActivity(intent);
//            }
//        });


    }


    private void setspinner() {
        spinOrgaList.add("โครงการ");
        spinOrgaList.add("กิจกรรม");
//        spinOrgaList.add("นอกวิทยาลัย");
        ArrayAdapter<String> adapterThai = new ArrayAdapter<String>(getApplicationContext(),
                R.layout.spinner_layout,R.id.txtview, spinOrgaList);
        spinOrga.setAdapter(adapterThai);
        spinOrga.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                Toast.makeText(getApplicationContext(),spinOrgaList.get(i)+"",Toast.LENGTH_LONG).show();
                if(spinOrgaList.get(i) == "โครงการ"){
                    typeSelect = "1";
                }else {
                    typeSelect = "2";
                }
                serviceGetListNameActivity(yearSelect,typeSelect);
            }

            private void serviceGetListNameActivity(final String yearSelect, final String typeSelect) {
                Call<List<ActivityListDao>> call = HttpManager.getInstance().getService().getListNameActivity(yearSelect,typeSelect);
                call.enqueue(new Callback<List<ActivityListDao>>() {
                    @Override
                    public void onResponse(Call<List<ActivityListDao>> call, Response<List<ActivityListDao>> response) {
                        List<ActivityListDao> dao = response.body();
                        List<ActivityListDao> ItemList = dao;
                        //ArrayList<Item> itemsList = generatedDummy();

                        ItemAdapter adapter = new ItemAdapter(getApplicationContext(), ItemList,typeSelect,yearSelect);
                        rvItem.setAdapter(adapter);
                        for(int i = 0 ; i < dao.size() ; i++ ) {
                            Log.d("eiei",dao.get(i).getAct_id()+ " " +dao.get(i).getAct_name());
                        }
                    }

                    @Override
                    public void onFailure(Call<List<ActivityListDao>> call, Throwable t) {

                    }
                });
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private void serviceActivity() {
        Call<ActivityDao> call = HttpManager.getInstance().getService().getActivy();
        call.enqueue(new Callback<ActivityDao>() {
            @Override
            public void onResponse(Call<ActivityDao> call, Response<ActivityDao> response) {

                if(response.isSuccessful()){
                    ActivityDao dao = response.body();

                    for(int i = 0 ; i < dao.getAct_year_academic().size() ; i++ ){
                        Log.d("eiei",""+dao.getAct_year_academic().get(i).getAct_year_academic());
                        spinYearList.add(dao.getAct_year_academic().get(i).getAct_year_academic());
                    }
                    ArrayAdapter<String> adapterThai = new ArrayAdapter<String>(getApplicationContext(),
                            R.layout.spinner_layout,R.id.txtview, spinYearList);
                    spinYear.setAdapter(adapterThai);
                    spinYear.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                        @Override
                        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                            ViewGroup vg=(ViewGroup)view;
                            TextView tv=(TextView)vg.findViewById(R.id.txtview);
                            Toast.makeText(getApplicationContext(), tv.getText().toString(),Toast.LENGTH_LONG).show();
                            yearSelect = tv.getText().toString();
                            serviceGetListNameActivity(tv.getText().toString(),typeSelect);
                        }

                        private void serviceGetListNameActivity(String year,String type) {
                            Call<List<ActivityListDao>> call = HttpManager.getInstance().getService().getListNameActivity(year,type);
                            call.enqueue(new Callback<List<ActivityListDao>>() {
                                @Override
                                public void onResponse(Call<List<ActivityListDao>> call, Response<List<ActivityListDao>> response) {
                                    if(response.isSuccessful()) {
                                        List<ActivityListDao> dao = response.body();
                                        List<ActivityListDao> ItemList = dao;
                                        //ArrayList<Item> itemsList = generatedDummy();

                                        ItemAdapter adapter = new ItemAdapter(getApplicationContext(), ItemList,typeSelect,yearSelect);
                                        rvItem.setAdapter(adapter);
                                        for(int i = 0 ; i < dao.size() ; i++ ) {
                                            Log.d("eiei",dao.get(i).getAct_id()+ " " +dao.get(i).getAct_name());
                                        }

                                    }else{

                                    }
                                }

                                @Override
                                public void onFailure(Call<List<ActivityListDao>> call, Throwable t) {

                                }
                            });
                        }

                        @Override
                        public void onNothingSelected(AdapterView<?> adapterView) {

                        }
                    });

                }else {
                    Log.d("eiei","wtf");
                }
            }

            @Override
            public void onFailure(Call<ActivityDao> call, Throwable t) {
                Log.d("eiei",""+t);
            }
        });
    }

    @Override
    public void onClick(View view) {
//       switch (view.getId()){
//          case  R.id.Action :{
//                Intent intent = new Intent(MenuActivity.this,MainActivity.class);
//                 startActivity(intent);
//           }
//       }
    }

    public void initInstand(){
     //   action =  findViewById(R.id.Action);
        spinYear = findViewById(R.id.planets_spinner);
        spinOrga = findViewById(R.id.planets_spinner1);
       // spinSubOrga = findViewById(R.id.planets_spinner2);
     //   text = findViewById(R.id.textsub);



    }

//    public void whatTheHex(View view) {
//        Intent intent = new Intent(MenuActivity.this, PieChartActivity.class);
//        startActivity(intent);
//    }
}

