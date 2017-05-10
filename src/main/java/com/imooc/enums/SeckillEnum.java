package com.imooc.enums;

/**
 * Created by zhouchang on 2016/12/3.
 */
public enum SeckillEnum {
    SUCCESS(0,"秒杀成功"),
    END(-1,"秒杀关闭"),
    REPEATED(-2,"重复秒杀"),
    REWROTE(-3,"数据篡改"),
    INNER_ERROR(-4,"内部错误");


    private int status;

    private String message;

    SeckillEnum(int status, String message) {
        this.status = status;
        this.message = message;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public static SeckillEnum indexOf(int index){
        for (SeckillEnum seckillEnum : values()){
            if(seckillEnum.getStatus() == index){
                return seckillEnum;
            }
        }
        return null;
    }
}
