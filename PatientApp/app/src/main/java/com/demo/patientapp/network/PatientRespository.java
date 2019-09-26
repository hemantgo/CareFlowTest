package com.demo.patientapp.network;

import com.demo.patientapp.datamodel.AuthRequestDataModel;
import com.demo.patientapp.datamodel.PatientListDataModel;
import com.demo.patientapp.datamodel.PatientRequestModel;

import io.reactivex.Observable;
import retrofit2.Response;

public class PatientRespository implements IAPIService {

    private final APIClient mAPIClient;

    public PatientRespository(APIClient APIClient) {
        mAPIClient = APIClient;
    }

    @Override
    public Observable<Response> fetchToken(AuthRequestDataModel authRequestDataModel) {
        return mAPIClient.create(APIService.class).fetchToken(authRequestDataModel);
    }

    @Override
    public Observable<PatientListDataModel> fetchPatientInfo(int teamId, PatientRequestModel requestModel) {
        return mAPIClient.create(APIService.class).fetchPatientInfo(teamId, requestModel);
    }
}
