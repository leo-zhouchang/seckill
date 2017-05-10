package com.imooc.dto;

import java.util.Date;

/**
 * 接口暴露的结果信息
 * Created by zhouchang on 2016/12/3.
 */
public class PublishResult {

    private boolean isPublished; //秒杀是否开启

    private long seckillId; //秒杀条目id

    private String md5; //暴露接口的密文

    private long start; //开启时间

    private long end; //结束时间

    private long now; //服务器当前时间 用以调整客户端时差

    public PublishResult() {}

    public PublishResult(boolean isPublished, long seckillId) {
        this.isPublished = isPublished;
        this.seckillId = seckillId;
    }

    public PublishResult(boolean isPublished, long seckillId, String md5) {
        this.isPublished = isPublished;
        this.seckillId = seckillId;
        this.md5 = md5;
    }

    public PublishResult(boolean isPublished, long seckillId, long start, long end, long now) {
        this.isPublished = isPublished;
        this.seckillId = seckillId;
        this.start = start;
        this.end = end;
        this.now = now;
    }

    public boolean isPublished() {
        return isPublished;
    }

    public void setPublished(boolean published) {
        isPublished = published;
    }

    public long getSeckillId() {
        return seckillId;
    }

    public void setSeckillId(long seckillId) {
        this.seckillId = seckillId;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public long getNow() {
        return now;
    }

    public void setNow(long now) {
        this.now = now;
    }

    @Override
    public String toString() {
        return "PublishResult{" +
                "isPublished=" + isPublished +
                ", seckillId=" + seckillId +
                ", md5='" + md5 + '\'' +
                ", start=" + start +
                ", end=" + end +
                ", now=" + now +
                '}';
    }
}
