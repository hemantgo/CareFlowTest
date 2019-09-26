/*
 * Copyright © 2017 Nedbank. All rights reserved.
 */

package com.demo.patientapp.error;

/**
 * Created by kapilvij on 23/08/2017.
 */
public enum ErrorType {
    GENERIC,
    NO_INTERNET,
    SOCKET_TIMEOUT,
    SSL_ERROR,
    HTTP_GENERIC,
    HTTP_FORBIDDEN,
    HTTP_BAD_REQUEST,
    HTTP_AUTHORIZATION,
    HTTP_SERVER_ERROR,
    HTTP_NOT_FOUND,
    HTTP_NO_CONTENT
}
