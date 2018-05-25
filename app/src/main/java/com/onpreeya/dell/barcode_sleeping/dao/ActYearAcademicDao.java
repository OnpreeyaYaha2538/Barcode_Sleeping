package com.onpreeya.dell.barcode_sleeping.dao;

import com.google.gson.annotations.SerializedName;

/**
 * Created by DELL on 6/3/2561.
 */

public class ActYearAcademicDao {
    @SerializedName("act_year_academic")
    String act_year_academic;

    public String getAct_year_academic() {
        return act_year_academic;
    }

    public void setAct_year_academic(String act_year_academic) {
        this.act_year_academic = act_year_academic;
    }
}
