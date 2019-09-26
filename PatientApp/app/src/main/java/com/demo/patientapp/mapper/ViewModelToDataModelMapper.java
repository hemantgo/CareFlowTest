package com.demo.patientapp.mapper;

import com.demo.patientapp.UI.viewmodel.AuthRequestViewModel;
import com.demo.patientapp.UI.viewmodel.PatientListViewModel;
import com.demo.patientapp.UI.viewmodel.PatientRequestViewModel;
import com.demo.patientapp.UI.viewmodel.PatientViewModel;
import com.demo.patientapp.datamodel.AuthRequestDataModel;
import com.demo.patientapp.datamodel.PatientDataModel;
import com.demo.patientapp.datamodel.PatientListDataModel;
import com.demo.patientapp.datamodel.PatientRequestModel;

import java.util.ArrayList;

public class ViewModelToDataModelMapper {

    public PatientRequestModel mapPatientRequestViewModelToDataModel(PatientRequestViewModel patientRequestViewModel) {
        if(patientRequestViewModel != null) {
            PatientRequestModel patientRequestModel = new PatientRequestModel();
            patientRequestModel.setItemsPerPage(patientRequestViewModel.getItemsPerPage());
            patientRequestModel.setPage(patientRequestViewModel.getPage());
            patientRequestModel.setInclude(patientRequestViewModel.getInclude());
            return patientRequestModel;
        }
        return null;
    }

    public AuthRequestDataModel mapAuthRequestViewModelToDataModel(AuthRequestViewModel authRequestViewModel) {
        if(authRequestViewModel != null) {
            AuthRequestDataModel authRequestDataModel = new AuthRequestDataModel();
            authRequestDataModel.setEmailAddress(authRequestViewModel.getEmailAddress());
            authRequestDataModel.setPassword(authRequestViewModel.getPassword());
            authRequestDataModel.setClient_Id(authRequestViewModel.getClient_Id());
            authRequestDataModel.setRedirect_url(authRequestViewModel.getRedirect_url());
            authRequestDataModel.setResponse_type(authRequestViewModel.getResponse_type());
            return authRequestDataModel;
        }
        return null;
    }
}
