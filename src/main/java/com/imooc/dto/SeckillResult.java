package com.imooc.dto;


import java.io.Serializable;

/**
 * Created by zhouchang on 2016/12/5.
 */
public class SeckillResult<T> implements Serializable {

    private boolean success;

    private String message;

    private T data;

    public SeckillResult(boolean success, String message) {
        this.success = success;
        this.message = message;
    }

    public SeckillResult(boolean success, T data) {
        this.success = success;
        this.data = data;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
