package com.onpreeya.dell.barcode_sleeping;

import android.app.ActionBar;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.onpreeya.dell.barcode_sleeping.dao.CheckDao;
import com.onpreeya.dell.barcode_sleeping.dao.StudentDao;
import com.onpreeya.dell.barcode_sleeping.manager.HttpManager;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    public static final int REQUEST_QR_SCAN = 4;
    TextView textContent;
    List<StudentDao> studentList = new ArrayList<StudentDao>();
    List<String> student = new ArrayList<String>();
    RecyclerView rvId;
    EditText TextStudent;
    String actId = "";
    String typeSelect = "";
    String yearSelect = "";
    String actName = "";
    StudentDao dao = new StudentDao();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        textContent = (TextView)findViewById(R.id.textContent);
        TextStudent = (EditText)findViewById(R.id.TextStudent);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            actId = bundle.getString("id");
            typeSelect = bundle.getString("typeSelect");
            actName = bundle.getString("actName");
            yearSelect = bundle.getString("yearSelect");
            textContent.setText(actName);
        }
        ImageButton buttonIntent = (ImageButton) findViewById(R.id.buttonIntent);
        buttonIntent.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                Intent intent =
                        new Intent("com.google.zxing.client.android.SCAN");
                startActivityForResult(Intent.createChooser(intent
                        , "Scan with"), REQUEST_QR_SCAN);
            }
        });

        rvId = (RecyclerView)findViewById(R.id.rvId);
        rvId.setHasFixedSize(true);

        LinearLayoutManager manager = new LinearLayoutManager(this);

        rvId.setLayoutManager(manager);


    }

    public void onActivityResult(int requestCode, int resultCode
            , Intent intent) {
        if (requestCode == REQUEST_QR_SCAN && resultCode == RESULT_OK) {
            String contents = intent.getStringExtra("SCAN_RESULT");
            //textContent.setText(contents);
            TextStudent.setText(contents);
            callService();

        }
    }

    public void submitId(View view) {

        if(TextStudent.getText().equals("")){

        }else {
                //dao.setStudentId(TextStudent.getText()+"");
                //studentList.add(dao);

           callService();

            //studentList.add(dao);

        }
    }

    private void callService() {
        Call <CheckDao> call = HttpManager.getInstance().getService().getCheckName(TextStudent.getText()+"",typeSelect,actId);
        call.enqueue(new Callback<CheckDao>() {
            @Override
            public void onResponse(Call<CheckDao> call, Response<CheckDao> response) {
                if(response.isSuccessful()){
                    CheckDao dao = response.body();
                    if(dao.getStatusCheck().equals("1")){
                        student.add(TextStudent.getText()+"");
                        ItemAdapterId adapter = new ItemAdapterId(getApplicationContext(), student);
                        rvId.setAdapter(adapter);
                        TextStudent.setText("");
                        Toast.makeText(MainActivity.this, "บันทึกสำเร็จ", Toast.LENGTH_SHORT).show();
                    }else if(dao.getStatusCheck().equals("0")){
                        Toast.makeText(MainActivity.this, "รหัสนักศึกษานี้เคยเช็คชื่อแล้ว", Toast.LENGTH_SHORT).show();
                    }else if(dao.getStatusCheck().equals("who")){
                        Toast.makeText(MainActivity.this, "รหัสนักศึกษานี้ไม่มีอยู่ในระบบ", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        Toast.makeText(MainActivity.this, "เกิดข้อผิดพลาด", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Log.d("ErrorRetofit:else",response.message());
                }
            }

            @Override
            public void onFailure(Call<CheckDao> call, Throwable t) {
                Log.d("ErrorRetofit:onFailure",""+t);
            }
        });

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
//            Intent intent = new Intent(this, MainActivity.class);
//            startActivity(intent);
//            return true;
//
//        }
        return super.onOptionsItemSelected(item);
    }
}





