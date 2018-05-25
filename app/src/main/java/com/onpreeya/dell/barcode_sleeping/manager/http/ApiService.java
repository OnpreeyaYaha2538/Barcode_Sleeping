package com.onpreeya.dell.barcode_sleeping.manager.http;

import com.onpreeya.dell.barcode_sleeping.dao.ActivityDao;
import com.onpreeya.dell.barcode_sleeping.dao.CheckDao;
import com.onpreeya.dell.barcode_sleeping.dao.DataChartStrudentCheckDao;
import com.onpreeya.dell.barcode_sleeping.dao.DetailChart4YearDao;
import com.onpreeya.dell.barcode_sleeping.dao.DetailChartDao;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;


/**
 * Created by DELL on 26/1/2561.
 */

public interface ApiService {
        @GET("sas/Mobile/Json_activity")
        Call<ActivityDao> ActivityListDao();

        @GET("index.php/sas/Affairs/Activity/act_mo")
        Call<ActivityDao> getActivy();

        @GET("index.php/sas/Affairs/Activity/get_act_name/{yearac}/{type}" )
        Call <List<ActivityListDao>> getListNameActivity(@Path("yearac") String yearac , @Path("type") String type);

        @GET("index.php/sas/Affairs/Activity/Check_stdid/{stdcode}/{typeppAc}/{passppAc}")
        Call <CheckDao> getCheckName(@Path("stdcode") String stdcode,@Path("typeppAc") String typeppAc,@Path("passppAc") String passppAc);

        @GET("index.php/sas/Activity/Activity_report/Report_chart/{passppAc}")
        Call <List<DataChartStrudentCheckDao>> getBarChart(@Path("passppAc") String passppAc);

        @GET("index.php/sas/Activity/Activity_report/Detail_chart/{passppAc}")
        Call<List<DetailChart4YearDao>> getDetailChart(@Path("passppAc") String passppAc);

}
