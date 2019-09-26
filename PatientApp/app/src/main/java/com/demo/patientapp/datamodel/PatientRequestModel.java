package com.demo.patientapp.datamodel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PatientRequestModel {

    @SerializedName("ItemsPerPage")
    int ItemsPerPage;
    @SerializedName("Include")
    ArrayList<String> Include;
    @SerializedName("page")
    int page;

    public void setItemsPerPage(int itemsPerPage) {
        ItemsPerPage = itemsPerPage;
    }

    public void setInclude(ArrayList<String> include) {
        Include = include;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
