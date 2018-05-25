package com.onpreeya.dell.barcode_sleeping.dao;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by DELL on 26/1/2561.
 */

public class ActivityDao {

    @SerializedName("rs_activity")
    List<RsActivityDao> rs_activity;

    @SerializedName("act_year_academic")
    List<ActYearAcademicDao> act_year_academic;

    public List<RsActivityDao> getRs_activity() {
        return rs_activity;
    }

    public void setRs_activity(List<RsActivityDao> rs_activity) {
        this.rs_activity = rs_activity;
    }

    public List<ActYearAcademicDao> getAct_year_academic() {
        return act_year_academic;
    }

    public void setAct_year_academic(List<ActYearAcademicDao> act_year_academic) {
        this.act_year_academic = act_year_academic;
    }


}
