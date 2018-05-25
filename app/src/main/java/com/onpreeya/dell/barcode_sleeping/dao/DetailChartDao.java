package com.onpreeya.dell.barcode_sleeping.dao;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class DetailChartDao {
    @SerializedName("stdCode")
    private String stdCode;

    @SerializedName("prefixName")
    private String prefixName;

    @SerializedName("stdName")
    private String stdName;

    public String getStdCode() {
        return stdCode;
    }

    public void setStdCode(String stdCode) {
        this.stdCode = stdCode;
    }

    public String getPrefixName() {
        return prefixName;
    }

    public void setPrefixName(String prefixName) {
        this.prefixName = prefixName;
    }

    public String getStdName() {
        return stdName;
    }

    public void setStdName(String stdName) {
        this.stdName = stdName;
    }

    public String getStdSurname() {
        return stdSurname;
    }

    public void setStdSurname(String stdSurname) {
        this.stdSurname = stdSurname;
    }

    @SerializedName("stdSurname")
    private String stdSurname;
}
