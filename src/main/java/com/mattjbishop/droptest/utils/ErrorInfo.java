package com.mattjbishop.droptest.utils;

/**
 * Created by matt on 22/08/2014.
 * 200 - OK - not an error
 * 201 - Created - not an error
 * 304 - Not Modified - not an error?
 * 400 - Bad Request
 * 401 - Unauthorized
 * 403 - Forbidden
 * 404 - Not Found
 * 500 - Internal Server Error
 */
public class ErrorInfo {
    final transient String developerMessage;

    final transient String userMessage;

    final transient String errorCode;

    final transient String moreInfo;

    public ErrorInfo(String developerMessage, String userMessage, String errorCode, String moreInfo) {
        this.developerMessage = developerMessage;
        this.userMessage = userMessage;
        this.errorCode = errorCode;
        this.moreInfo = moreInfo;
    }

    public String getDeveloperMessage() {
        return developerMessage;
    }

    public String getUserMessage() {
        return userMessage;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getMoreInfo() {
        return moreInfo;
    }
}
