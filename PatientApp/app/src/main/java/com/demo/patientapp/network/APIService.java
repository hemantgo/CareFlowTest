package com.demo.patientapp.network;

import com.demo.patientapp.datamodel.AuthRequestDataModel;
import com.demo.patientapp.datamodel.PatientListDataModel;
import com.demo.patientapp.datamodel.PatientRequestModel;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface APIService {

    @FormUrlEncoded
    @POST("Home/Login")
    Observable<Response> fetchToken(@Body AuthRequestDataModel authRequestDataModel);

    @POST("groups/{teamId}/patients")
    Observable<PatientListDataModel> fetchPatientInfo(@Path("teamId") int teamId, @Body PatientRequestModel requestModel);
}
