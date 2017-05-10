package com.imooc.exception;

/**
 * 秒杀已关闭异常(可能是库存不足 或是 已到达截止时间)
 * Created by zhouchang on 2016/12/3.
 */
public class ClosedSeckillException extends SeckillException {

    public ClosedSeckillException(String message) {
        super(message);
    }

    public ClosedSeckillException(String message, Throwable cause) {
        super(message, cause);
    }
}
