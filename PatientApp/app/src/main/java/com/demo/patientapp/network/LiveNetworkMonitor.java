/*
 * Copyright © 2017 Nedbank. All rights reserved.
 */

package com.demo.patientapp.network;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class LiveNetworkMonitor {

    private final Context context;

    public LiveNetworkMonitor(final Context context) {
        this.context = context;
    }

    protected boolean isConnected() {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        if(cm != null){
            NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
            return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
        }
        return false;
    }
}