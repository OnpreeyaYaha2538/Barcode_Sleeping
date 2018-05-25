package com.onpreeya.dell.barcode_sleeping;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.onpreeya.dell.barcode_sleeping.adapter.StudentAdapter;
import com.onpreeya.dell.barcode_sleeping.dao.DetailChart4YearDao;
import com.onpreeya.dell.barcode_sleeping.dao.DetailChartDao;
import com.onpreeya.dell.barcode_sleeping.manager.HttpManager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Callback;
import retrofit2.Response;

public class StudentActivity extends AppCompatActivity {

    RecyclerView rvStudent;
    String actId = "4";
    String checkStudentList = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        rvStudent = findViewById(R.id.rvStudent);
        rvStudent.setHasFixedSize(true);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            actId = bundle.getString("id");
            checkStudentList = bundle.getString("checkStudentList");
        }

        Log.d("ggwp ::","if"+checkStudentList);
        setViewAdapter(actId,checkStudentList);



        LinearLayoutManager manager = new LinearLayoutManager(this);
        rvStudent.setLayoutManager(manager);

    }

    private void setViewAdapter(String actId, final String checkStudentList) {

        retrofit2.Call<List<DetailChart4YearDao>> call = HttpManager.getInstance().getService().getDetailChart(actId);
        call.enqueue(new Callback<List<DetailChart4YearDao>>() {
            @Override
            public void onResponse(retrofit2.Call<List<DetailChart4YearDao>> call, Response<List<DetailChart4YearDao>> response) {
                if(response.isSuccessful()){
                    List<DetailChart4YearDao> dao = response.body();
                    List<DetailChartDao> itemList = new ArrayList<>();

                    Log.d("callServiceChart ::","if"+dao.get(0).getNumStu());

                    if(checkStudentList.equals("check")){
                        int index = 0;
                        for(int i = 0 ; i < dao.size() ; i++){
                            for(int j = 0 ; j < dao.get(i).getNumStu().size() ; j++){
                                Log.d("callDetailChart ::",""+dao.get(i).getNumStu().get(j).getStdName());
                                DetailChartDao dd = new DetailChartDao();
                                dd.setStdCode(dao.get(i).getNumStu().get(j).getStdCode());
                                dd.setPrefixName(dao.get(i).getNumStu().get(j).getPrefixName());
                                dd.setStdName(dao.get(i).getNumStu().get(j).getStdName());
                                dd.setStdSurname(dao.get(i).getNumStu().get(j).getStdSurname());
                                itemList.add(dd);
                                index++;
                            }
                        }
                    }else{
                        int index = 0;
                        for(int i = 0 ; i < dao.size() ; i++){
                            for(int j = 0 ; j < dao.get(i).getNumStuNotin().size() ; j++){
                                Log.d("callDetailChart ::",""+dao.get(i).getNumStuNotin().get(j).getStdName());
                                DetailChartDao dd = new DetailChartDao();
                                dd.setStdCode(dao.get(i).getNumStuNotin().get(j).getStdCode());
                                dd.setPrefixName(dao.get(i).getNumStuNotin().get(j).getPrefixName());
                                dd.setStdName(dao.get(i).getNumStuNotin().get(j).getStdName());
                                dd.setStdSurname(dao.get(i).getNumStuNotin().get(j).getStdSurname());
                                itemList.add(dd);
                                index++;
                            }
                        }
                    }


                    StudentAdapter studentAdapter = new StudentAdapter(itemList);
                    rvStudent.setAdapter(studentAdapter);

                }else{
                    try {
                        Log.d("callServiceChart ::","else "+response.errorBody().string());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(retrofit2.Call<List<DetailChart4YearDao>> call, Throwable t) {

            }
        });

    }

}
