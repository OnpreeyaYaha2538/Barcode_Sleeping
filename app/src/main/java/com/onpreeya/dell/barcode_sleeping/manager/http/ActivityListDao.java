package com.onpreeya.dell.barcode_sleeping.manager.http;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DELL on 15/3/2561.
 */

public class ActivityListDao {

    @SerializedName("act_id")
    String act_id;

    public String getAct_id() {
        return act_id;
    }

    public void setAct_id(String act_id) {
        this.act_id = act_id;
    }

    @SerializedName("act_name")
    String act_name;

    public String getAct_name() {
        return act_name;
    }

    public void setAct_name(String act_name) {
        this.act_name = act_name;
    }
}
