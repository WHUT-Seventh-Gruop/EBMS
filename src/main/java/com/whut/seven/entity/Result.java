package com.whut.seven.entity;

import com.fasterxml.jackson.annotation.JsonInclude;


/**
 * @author aaaaaaa
 * mag 返回的信息
 * success请求时候成功
 * detail返回的数据
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Result<T> {
    private String message;
    private boolean success;
    private  T detail;
    public void setSuccessAndMsg(boolean aSuccess,String aMsg){
        message =aMsg;
        success=aSuccess;
    }
    public void setSuccessAndMsgAndDetail(boolean aSuccess,String aMsg,T aDetail){
        message =aMsg;
        success=aSuccess;
        detail=aDetail;
    }

    @Override
    public String toString() {
        return "Result{" +
                "message='" + message + '\'' +
                ", success=" + success +
                ", detail=" + detail +
                '}';
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public T getDetail() {
        return detail;
    }

    public void setDetail(T detail) {
        this.detail = detail;
    }

    public Result() {
    }

    public Result(String message, boolean success, T detail) {
        this.message = message;
        this.success = success;
        this.detail = detail;
    }
}
