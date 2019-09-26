package com.demo.patientapp.UI.splash;

import com.demo.patientapp.UI.base.BaseView;

public interface SplashView extends BaseView {
    void showError(String message);

    void moveToNextScreen();

    void storeToken(String token);
}
