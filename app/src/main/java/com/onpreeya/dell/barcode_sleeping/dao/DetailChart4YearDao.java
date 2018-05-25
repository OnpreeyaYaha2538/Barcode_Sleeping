package com.onpreeya.dell.barcode_sleeping.dao;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailChart4YearDao {

    @SerializedName("num_stu")
    List<DetailChartDao> numStu;

    @SerializedName("col_year")
    int colYear;

    @SerializedName("num_stu_notin")
    List<DetailChartDao> numStuNotin;

    public List<DetailChartDao> getNumStu() {
        return numStu;
    }

    public void setNumStu(List<DetailChartDao> numStu) {
        this.numStu = numStu;
    }

    public int getColYear() {
        return colYear;
    }

    public void setColYear(int colYear) {
        this.colYear = colYear;
    }

    public List<DetailChartDao> getNumStuNotin() {
        return numStuNotin;
    }

    public void setNumStuNotin(List<DetailChartDao> numStuNotin) {
        this.numStuNotin = numStuNotin;
    }

}
