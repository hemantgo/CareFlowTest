package com.demo.patientapp.UI.splash;

import android.annotation.SuppressLint;

import com.demo.patientapp.UI.base.BasePresenter;
import com.demo.patientapp.UI.viewmodel.AuthRequestViewModel;
import com.demo.patientapp.mapper.DataModelToViewModelMapper;
import com.demo.patientapp.mapper.ViewModelToDataModelMapper;
import com.demo.patientapp.network.APIClient;
import com.demo.patientapp.network.APIService;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

public class SplashPresenter extends BasePresenter<SplashView> {

    private APIClient mApiClient;
    private ViewModelToDataModelMapper mViewModelToDataModelMapper;

    public SplashPresenter(APIClient apiClient) {
        mApiClient = apiClient;
    }

    @SuppressLint("CheckResult")
    public void getAccessToken(AuthRequestViewModel authRequestViewModel) {
        mApiClient.create(APIService.class)
                .fetchToken(mViewModelToDataModelMapper.mapAuthRequestViewModelToDataModel(authRequestViewModel))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    if(view != null) {
                        if(result != null && result.headers() != null) {
                            String token = result.headers().get("access_token");
                            // Not storing other values as not handling it for now.
                            view.storeToken(token);
                            view.moveToNextScreen();
                            view.close();
                        }
                    }
                }, throwable -> {
                    if(view != null) {
                        view.showError("Enable to get access token.");
                    }
                });
    }
}
