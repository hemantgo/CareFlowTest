package com.demo.patientapp.UI.viewmodel;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class PatientRequestViewModel {

    int ItemsPerPage;
    ArrayList<String> Include;
    int page;

    public int getItemsPerPage() {
        return ItemsPerPage;
    }

    public void setItemsPerPage(int itemsPerPage) {
        ItemsPerPage = itemsPerPage;
    }

    public ArrayList<String> getInclude() {
        return Include;
    }

    public void setInclude(ArrayList<String> include) {
        Include = include;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }
}
