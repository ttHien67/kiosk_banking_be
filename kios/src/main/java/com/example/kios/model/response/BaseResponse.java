package com.example.kios.model.response;

public class BaseResponse {
    private Object data;

    private String errorCode = null;
    private String errorDesc = null;
    private long totalRecord;


    public long getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(long totalRecord) {
        this.totalRecord = totalRecord;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorDesc() {
        return errorDesc;
    }

    public void setErrorDesc(String errorDesc) {
        this.errorDesc = errorDesc;
    }

    public BaseResponse() {
        this.data = null;
        this.errorDesc = null;
        this.errorCode = null;
    }

    public BaseResponse(Object data) {
        this.data = data;
    }

    public BaseResponse(String errorCode, String errorDesc) {
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
    }

    public BaseResponse(Object data, String errorCode, String errorDesc) {
        this.data = data;
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
    }

    public BaseResponse(Object data, int totalRecord, String errorCode, String errorDesc) {
        this.data = data;
        this.totalRecord = totalRecord;
        this.errorCode = errorCode;
        this.errorDesc = errorDesc;
    }
}


