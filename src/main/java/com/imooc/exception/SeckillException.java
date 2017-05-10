package com.imooc.exception;

/**
 * DAO层异常总类
 * Created by zhouchang on 2016/12/3.
 */
public class SeckillException extends RuntimeException {

    public SeckillException(String message) {
        super(message);
    }

    public SeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
