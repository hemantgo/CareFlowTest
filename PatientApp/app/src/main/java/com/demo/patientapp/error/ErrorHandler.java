/*
 * Copyright © 2017 Nedbank. All rights reserved.
 */

package com.demo.patientapp.error;

import com.demo.patientapp.exceptions.NoContentException;
import com.demo.patientapp.exceptions.NoNetworkException;

import java.net.SocketException;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

import retrofit2.HttpException;

import static com.demo.patientapp.error.HttpStatus.AUTHORIZATION;
import static com.demo.patientapp.error.HttpStatus.BAD_REQUEST;
import static com.demo.patientapp.error.HttpStatus.FORBIDDEN;
import static com.demo.patientapp.error.HttpStatus.NOT_FOUND;
import static com.demo.patientapp.error.HttpStatus.SERVER_ERROR;

public class ErrorHandler {

    ErrorProvider mErrorProvider;
    public ErrorHandler(ErrorProvider errorProvider) {
        mErrorProvider = errorProvider;
    }

    public Error getErrorMessage(Throwable throwable) {
        Error error = new Error();
        if (throwable instanceof NoNetworkException) {
            error.setMessage(mErrorProvider.getMessage(ErrorType.NO_INTERNET));
        } else if (throwable instanceof SocketException || throwable instanceof SocketTimeoutException) {
            error.setMessage(mErrorProvider.getMessage(ErrorType.SOCKET_TIMEOUT));
        } else if (throwable instanceof UnknownHostException) {
            error.setMessage(mErrorProvider.getMessage(ErrorType.HTTP_NOT_FOUND));
        } else if (throwable instanceof HttpException) {
            // non-2XX http error
            int errorCodes = ((HttpException) throwable).code();
            switch (errorCodes) {
                case BAD_REQUEST:
                    error.setMessage(mErrorProvider.getMessage(ErrorType.HTTP_BAD_REQUEST));
                    break;
                case AUTHORIZATION:
                    error.setMessage(mErrorProvider.getMessage(ErrorType.HTTP_AUTHORIZATION));
                    break;
                case FORBIDDEN:
                    error.setMessage(mErrorProvider.getMessage(ErrorType.HTTP_FORBIDDEN));
                    break;
                case NOT_FOUND:
                    error.setMessage(mErrorProvider.getMessage(ErrorType.HTTP_NOT_FOUND));
                    break;
                case SERVER_ERROR:
                    error.setMessage(mErrorProvider.getMessage(ErrorType.HTTP_SERVER_ERROR));
                    break;
                default:
                    error.setMessage(mErrorProvider.getMessage(ErrorType.HTTP_SERVER_ERROR));
                    break;
            }
            error.setCode(errorCodes);
        } else if (throwable instanceof NoContentException) {
            error.setMessage(mErrorProvider.getMessage(ErrorType.HTTP_NO_CONTENT));
            error.setCode(HttpStatus.NO_CONTENT);
        } else {
            error.setMessage(mErrorProvider.getMessage(ErrorType.GENERIC));
        }
        return error;
    }
}
