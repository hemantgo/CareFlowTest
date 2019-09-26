package com.demo.patientapp.network;

import io.reactivex.Observable;

public class PatientRespository implements IAPIService {

    private final APIClient mAPIClient;

    public PatientRespository(APIClient APIClient) {
        mAPIClient = APIClient;
    }

    @Override
    public Observable<String> fetchToken(String emailId, String password, String clientId, String redirectUrl, String responseType) {
        return mAPIClient.create(APIService.class).fetchToken(emailId, password, clientId, redirectUrl, responseType);
    }
}
