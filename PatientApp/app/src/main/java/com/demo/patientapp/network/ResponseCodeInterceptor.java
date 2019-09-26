/*
 * Copyright © 2017 Nedbank. All rights reserved.
 */

package com.demo.patientapp.network;

import android.support.annotation.NonNull;
import android.util.Log;

import com.demo.patientapp.error.HttpStatus;
import com.demo.patientapp.exceptions.NoContentException;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

public class ResponseCodeInterceptor implements Interceptor {


    public ResponseCodeInterceptor() {
    }

    @Override
    public Response intercept(@NonNull final Chain chain) throws IOException {
        Request request = chain.request();
        Response response = chain.proceed(request);
        final int responseCode = response.code();
        if (responseCode == HttpStatus.NO_CONTENT) {
            throw new NoContentException();
        } else if (responseCode == HttpStatus.FORBIDDEN) {
            // TODO Handle here to notify if token is expired
            Log.d("Forbidden", "Forbidden Exception: Token expired");
        }
        return response;

    }
}