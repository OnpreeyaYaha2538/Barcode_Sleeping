package com.onpreeya.dell.barcode_sleeping.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DELL on 6/3/2561.
 */

public class RsActivityDao {
    @SerializedName("act_name")
    String actName;

    @SerializedName("act_agency_name")
    String actAgecyName;

    public String getActName() {
        return actName;
    }

    public void setActName(String actName) {
        this.actName = actName;
    }

    public String getActAgecyName() {
        return actAgecyName;
    }

    public void setActAgecyName(String actAgecyName) {
        this.actAgecyName = actAgecyName;
    }

}
