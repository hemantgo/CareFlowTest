/*
 * Copyright © 2017 Nedbank. All rights reserved.
 */

package com.demo.patientapp.network;

import android.support.annotation.NonNull;

import com.demo.patientapp.exceptions.NoNetworkException;

import java.io.IOException;


import okhttp3.Interceptor;
import okhttp3.Response;

public class NetworkMonitorInterceptor implements Interceptor {

    private LiveNetworkMonitor mLiveNetworkMonitor;


    public NetworkMonitorInterceptor(LiveNetworkMonitor liveNetworkMonitor) {
        this.mLiveNetworkMonitor = liveNetworkMonitor;
    }

    @Override
    public Response intercept(@NonNull final Chain chain) throws IOException {
        if (mLiveNetworkMonitor.isConnected()) {
            return chain.proceed(chain.request());
        } else {
            throw new NoNetworkException();
        }
    }
}