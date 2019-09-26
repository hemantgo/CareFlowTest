package com.demo.patientapp.UI.patient_detail;

import com.demo.patientapp.UI.base.BaseView;
import com.demo.patientapp.UI.viewmodel.PatientViewModel;

import java.util.ArrayList;

public interface PatientDetailView extends BaseView {
    void setName(String name);
    void setClinicalData(String clinical);
    void setLocation(String location);
    void setNHSNumber(String nhsnumber);
}
