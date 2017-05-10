package com.imooc.model;

import java.util.Date;

/**
 * order models record that who booked successfully
 * Created by zhouchang on 2016/12/2.
 */
public class Order {
    private String userId;

    private long seckillId;

    private int status;

    private Date createTime;

    private Seckill seckill;

    public Seckill getSeckill() {
        return seckill;
    }

    public void setSeckill(Seckill seckill) {
        this.seckill = seckill;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String toString() {
        return "Order{" +
                "userId='" + userId + '\'' +
                ", seckillId=" + seckillId +
                ", status=" + status +
                ", createTime=" + createTime +
                ", seckill=" + seckill +
                '}';
    }
}
