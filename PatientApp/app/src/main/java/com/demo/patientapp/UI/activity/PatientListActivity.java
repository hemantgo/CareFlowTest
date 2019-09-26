package com.demo.patientapp.UI.activity;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;

import com.demo.patientapp.R;
import com.demo.patientapp.UI.base.AppBaseActivity;
import com.demo.patientapp.UI.patientlist_fragment.PatientListFragment;
import com.demo.patientapp.UI.viewmodel.PatientViewModel;
import com.demo.patientapp.common.Constants;

public class PatientListActivity extends AppBaseActivity implements PatientListFragment.OnPatientSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        // Create and set Patient List Fragment as default.
        Fragment patientListFragment = new PatientListFragment();
        this.setDefaultFragment(patientListFragment);
    }

    /**
     * This method is used to set the default fragment that will be shown.
     */
    private void setDefaultFragment(Fragment defaultFragment) {
        this.replaceFragment(R.id.container, defaultFragment);
    }

    @Override
    public void onPatientSelected(PatientViewModel item) {
        Intent intent = new Intent(this, PatientDetailActivity.class);
        intent.putExtra(Constants.EXTRA_PATIENT_DATA, item);
        startActivity(intent);
    }

    @Override
    public void close() {
        finish();
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        if (fragment instanceof PatientListFragment) {
            PatientListFragment patientListFragment = (PatientListFragment) fragment;
            patientListFragment.setListener(this);
        }
    }
}
