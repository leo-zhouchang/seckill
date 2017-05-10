package com.imooc.service;

import com.imooc.dto.ExecuteSeckillResult;
import com.imooc.dto.PublishResult;
import com.imooc.exception.ClosedSeckillException;
import com.imooc.exception.RepeatSeckillException;
import com.imooc.exception.SeckillException;
import com.imooc.model.Seckill;

import java.util.List;

/**
 * Created by zhouchang on 2016/12/3.
 */
public interface SeckeillService {

    /**
     * 分页查询秒杀活动列表
     * @param offset
     * @param limit
     * @return
     */
    List<Seckill> queryAllSeckills(int offset, int limit);

    /**
     * 查询单个秒杀活动
     * @param seckillId 秒杀id
     * @return
     */
    Seckill querySeckillById(long seckillId);

    /**
     * 暴露接口
     * @param seckillId
     * @return
     */
    PublishResult publishSeckillUrl(long seckillId);

    /**
     * 执行秒杀操作
     * @param seckillId
     * @param userId
     * @param md5
     * @return
     * @throws SeckillException 除下面两个异常外的其他异常
     * @throws ClosedSeckillException 活动关闭异常
     * @throws RepeatSeckillException 重复操作异常
     */
    ExecuteSeckillResult seckillExecution(long seckillId, String userId, String md5)
            throws SeckillException,ClosedSeckillException,RepeatSeckillException;
}
