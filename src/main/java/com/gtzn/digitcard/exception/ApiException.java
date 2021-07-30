package com.gtzn.digitcard.exception;

public class ApiException extends RuntimeException {

    protected String errorCode = "500";
    protected Object data;

    public ApiException(String errorCode, String message, Object data, Throwable e) {
        super(message, e);
        this.errorCode = errorCode;
        this.data = data;
    }

    public ApiException(String errorCode, String message, Object data) {
        this(errorCode, message, data, null);
    }

    public ApiException(String errorCode, String message) {
        this(errorCode, message, null, null);
    }

    public ApiException(String errorCode, String message, Throwable e) {
        this(errorCode, message, null, e);
    }

    public ApiException(String message, Throwable e) {
        this(null, message, null, e);
    }

    public ApiException() {
        super();
    }

    public ApiException(Throwable e) {
        super(e);
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
