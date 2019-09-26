package com.demo.patientapp.UI.patient_detail;

import android.text.TextUtils;

import com.demo.patientapp.UI.base.BasePresenter;
import com.demo.patientapp.UI.viewmodel.PatientViewModel;

public class PatientDetailPresenter extends BasePresenter<PatientDetailView> {


    public PatientDetailPresenter() {
    }

    public void setPatientDetail(PatientViewModel patientDetail) {
        if(view != null) {
            // TODO uncomment once we know the response of patient
//            view.setName(patientDetail);
//
//            if(TextUtils.isEmpty(patientDetail.clinical())) {
//                view.setClinicalData(patientDetail.clinical());
//            }
//            if(TextUtils.isEmpty(patientDetail.location())) {
//                view.setLocation(patientDetail.location());
//            }
//            if(TextUtils.isEmpty(patientDetail.NHSNumber())) {
//                view.setNHSNumber(patientDetail.NHSNumber());
//            }
        }
    }
}

