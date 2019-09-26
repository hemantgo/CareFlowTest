package com.demo.patientapp.UI.splash;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.demo.patientapp.R;
import com.demo.patientapp.UI.viewmodel.AuthRequestViewModel;
import com.demo.patientapp.common.utils.PrefUtils;
import com.demo.patientapp.mapper.ViewModelToDataModelMapper;
import com.demo.patientapp.network.APIClient;
import com.demo.patientapp.network.LiveNetworkMonitor;
import com.demo.patientapp.UI.activity.PatientListActivity;

public class SplashActivity extends AppCompatActivity implements SplashView{

    SplashPresenter mSplashPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        if(getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        ViewModelToDataModelMapper viewModelToDataModelMapper = new ViewModelToDataModelMapper();
        APIClient apiClient = APIClient.getInstance(new LiveNetworkMonitor(this));
        mSplashPresenter = new SplashPresenter(apiClient, viewModelToDataModelMapper);
        mSplashPresenter.bind(this);

        mSplashPresenter.getAccessToken(getRequestModel());
    }

    private AuthRequestViewModel getRequestModel() {
        AuthRequestViewModel authRequestViewModel = new AuthRequestViewModel();
        authRequestViewModel.setEmailAddress("careflowinterviewcandidate@gmail.com");
        authRequestViewModel.setPassword("Car3F!0wT3st");
        authRequestViewModel.setClient_Id("DocComMobile");
        authRequestViewModel.setRedirect_url("https://www.careflowconnect.com");
        authRequestViewModel.setResponse_type("token");
        return authRequestViewModel;
    }

    @Override
    public void showError(String message) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }

    @Override
    public void storeToken(String token) {
        PrefUtils.storeToken(this, token);
    }

    @Override
    public void moveToNextScreen() {
        Intent intent = new Intent(SplashActivity.this, PatientListActivity.class);
        startActivity(intent);
    }

    @Override
    public void close() {
        finish();
    }
}
