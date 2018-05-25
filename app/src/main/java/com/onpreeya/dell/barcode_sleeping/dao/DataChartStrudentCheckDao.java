package com.onpreeya.dell.barcode_sleeping.dao;

import com.google.gson.annotations.SerializedName;

public class DataChartStrudentCheckDao {

    @SerializedName("num_stu")
    private String numStu;

    @SerializedName("num_stu_notin")
    private String numStuNotin;

    @SerializedName("col_year")
    private int colYear;

    public String getNumStu() {
        return numStu;
    }

    public void setNumStu(String numStu) {
        this.numStu = numStu;
    }

    public String getNumStuNotin() {
        return numStuNotin;
    }

    public void setNumStuNotin(String numStuNotin) {
        this.numStuNotin = numStuNotin;
    }

    public int getColYear() {
        return colYear;
    }

    public void setColYear(int colYear) {
        this.colYear = colYear;
    }
}
