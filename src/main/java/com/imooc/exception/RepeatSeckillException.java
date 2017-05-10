package com.imooc.exception;

/**
 * 重复秒杀异常
 * Created by zhouchang on 2016/12/3.
 */
public class RepeatSeckillException extends SeckillException {

    public RepeatSeckillException(String message) {
        super(message);
    }

    public RepeatSeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
