package com.demo.patientapp.UI.viewmodel;

import java.util.ArrayList;

public class PatientListViewModel {
    ArrayList<PatientViewModel> patientList;

    public ArrayList<PatientViewModel> getPatientList() {
        return patientList;
    }

    public void setPatientList(ArrayList<PatientViewModel> patientList) {
        this.patientList = patientList;
    }
}
