package com.demo.patientapp.network;

import com.demo.patientapp.datamodel.AuthRequestDataModel;
import com.demo.patientapp.datamodel.PatientListDataModel;
import com.demo.patientapp.datamodel.PatientRequestModel;

import io.reactivex.Observable;
import retrofit2.Response;

public interface IAPIService {

    Observable<Response> fetchToken(AuthRequestDataModel authRequestDataModel);

    Observable<PatientListDataModel> fetchPatientInfo(int teamId, PatientRequestModel requestModel);
}
