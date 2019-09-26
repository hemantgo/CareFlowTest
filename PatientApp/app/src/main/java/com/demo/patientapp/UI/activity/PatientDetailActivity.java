package com.demo.patientapp.UI.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;

import com.demo.patientapp.R;
import com.demo.patientapp.UI.base.AppBaseActivity;
import com.demo.patientapp.UI.patient_detail.PatientDetailFragment;
import com.demo.patientapp.UI.viewmodel.PatientViewModel;
import com.demo.patientapp.common.Constants;

public class PatientDetailActivity extends AppBaseActivity implements PatientDetailFragment.OnPatientDetailListener {

    private PatientViewModel mPatientViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_detail);

        if(getIntent() != null && getIntent().getExtras() != null) {
            mPatientViewModel = (PatientViewModel) getIntent().getExtras().get(Constants.EXTRA_PATIENT_DATA);
        }

        if(mPatientViewModel != null) {
            // Create and set Patient List Fragment as default.
            Fragment patientListFragment = PatientDetailFragment.newInstance(mPatientViewModel);
            this.setDefaultFragment(patientListFragment);
        } else {
            showToast(R.string.no_data_found);
            close();
        }
    }

    /**
     * This method is used to set the default fragment that will be shown.
     */
    private void setDefaultFragment(Fragment defaultFragment) {
        this.replaceFragment(R.id.container, defaultFragment);
    }

    @Override
    public void close() {
        finish();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof PatientDetailFragment) {
            PatientDetailFragment patientDetailFragment = (PatientDetailFragment) fragment;
            patientDetailFragment.setListener(this);
        }
    }
}
