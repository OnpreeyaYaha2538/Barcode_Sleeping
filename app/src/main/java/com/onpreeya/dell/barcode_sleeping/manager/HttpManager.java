package com.onpreeya.dell.barcode_sleeping.manager;

import android.content.Context;

import com.onpreeya.dell.barcode_sleeping.manager.http.ApiService;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by DELL on 26/1/2561.
 */

public class HttpManager {
    private static HttpManager instance;

    public static HttpManager getInstance() {
        if (instance == null)
            instance = new HttpManager();
        return instance;
    }

    private Context mContext;
    private ApiService service;

    private HttpManager() {
        mContext = Contextor.getInstance().getContext();
        OkHttpClient okHttpClient = UnsafeOkHttpClient.getUnsafeOkHttpClient();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://10.80.39.23/pcksite/")
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        service = retrofit.create(ApiService.class);
    }
    public  ApiService getService(){
        return  service;
    }
}
