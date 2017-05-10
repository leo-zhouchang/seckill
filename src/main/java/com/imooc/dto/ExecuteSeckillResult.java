package com.imooc.dto;

import com.imooc.enums.SeckillEnum;
import com.imooc.model.Order;

/**
 * Created by zhouchang on 2016/12/3.
 */
public class ExecuteSeckillResult {

    private boolean isSuccess; // 是否秒杀成功

    private long seckillId; //秒杀条目的id

    private int status; //秒杀状态

    private String message; //没有秒杀到的原因说明

    private Order order;

    public ExecuteSeckillResult(boolean isSuccess, long seckillId, String message) {
        this.isSuccess = isSuccess;
        this.seckillId = seckillId;
        this.message = message;
    }

    public ExecuteSeckillResult(boolean isSuccess, long seckillId, SeckillEnum seckillEnum){
        this.isSuccess = isSuccess;
        this.seckillId = seckillId;
        this.message = seckillEnum.getMessage();
        this.status = seckillEnum.getStatus();
    }

    public ExecuteSeckillResult(boolean isSuccess, long seckillId, SeckillEnum seckillEnum, Order order) {
        this.isSuccess = isSuccess;
        this.seckillId = seckillId;
        this.message = seckillEnum.getMessage();
        this.order = order;
        this.status = seckillEnum.getStatus();
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        isSuccess = success;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ExecuteSeckillResult{" +
                "isSuccess=" + isSuccess +
                ", seckillId=" + seckillId +
                ", status=" + status +
                ", message='" + message + '\'' +
                ", order=" + order +
                '}';
    }
}
