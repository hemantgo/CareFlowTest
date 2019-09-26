package com.demo.patientapp.UI.patientlist_fragment;

import com.demo.patientapp.UI.base.BaseView;
import com.demo.patientapp.UI.viewmodel.PatientListViewModel;
import com.demo.patientapp.UI.viewmodel.PatientViewModel;

import java.util.ArrayList;

public interface PatientListView extends BaseView {
    void showError(String message);

    void showPatientData(ArrayList<PatientViewModel> result);

    void addLoadingToAdapter();

    void foundLastPage();
}
