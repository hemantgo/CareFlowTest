package com.demo.patientapp.mapper;

import com.demo.patientapp.UI.viewmodel.PatientListViewModel;
import com.demo.patientapp.UI.viewmodel.PatientRequestViewModel;
import com.demo.patientapp.UI.viewmodel.PatientViewModel;
import com.demo.patientapp.datamodel.PatientDataModel;
import com.demo.patientapp.datamodel.PatientListDataModel;
import com.demo.patientapp.datamodel.PatientRequestModel;

import java.util.ArrayList;

public class DataModelToViewModelMapper {

    public PatientListViewModel mapPatientListDataToViewModel(PatientListDataModel patientListDataModel) {
        if(patientListDataModel != null) {
            PatientListViewModel patientListViewModel = new PatientListViewModel();
            patientListViewModel.setPatientList(new ArrayList<>());
            for(PatientDataModel patientDataModel : patientListDataModel.getPatientList()) {
                PatientViewModel patientViewModel = new PatientViewModel();

                // TODO convert here.
                patientListViewModel.getPatientList().add(patientViewModel);
            }
            return patientListViewModel;
        }
        return null;
    }
}
