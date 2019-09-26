/*
 * Copyright © 2017 Nedbank. All rights reserved.
 */

package com.demo.patientapp.error;

import android.content.Context;

import com.demo.patientapp.R;

public class ErrorProvider {

    private final Context context;

    public ErrorProvider(final Context context) {
        this.context = context;
    }

    public String getMessage(final ErrorType messageType) {
        switch (messageType) {
            case GENERIC:
                return context.getString(R.string.error_generic);
            case SOCKET_TIMEOUT:
                return context.getString(R.string.error_socket_timeout);
            case SSL_ERROR:
                return context.getString(R.string.error_ssl_error);
            case HTTP_GENERIC:
                return context.getString(R.string.error_http_generic);
            case HTTP_FORBIDDEN:
                return context.getString(R.string.error_http_forbidden);
            case HTTP_BAD_REQUEST:
                return context.getString(R.string.error_http_bad_request);
            case HTTP_AUTHORIZATION:
                return context.getString(R.string.error_http_authorization);
            case HTTP_SERVER_ERROR:
                return context.getString(R.string.error_server_error);
            case HTTP_NOT_FOUND:
                return context.getString(R.string.error_not_found);
            case NO_INTERNET:
                return context.getString(R.string.error_no_network);
            case HTTP_NO_CONTENT:
                return context.getString(R.string.error_no_content);
            default:
                return "";
        }
    }
}
