package com.imooc.dao;

import com.imooc.model.Seckill;
import org.apache.ibatis.annotations.Param;

import javax.print.DocFlavor;
import java.util.Date;
import java.util.List;

/**
 * Created by zhouchang on 2016/12/2.
 */
public interface SeckillDao {

    /**
     * 减库存操作
     * @param seckillId 秒杀活动条目的id
     * @param killTime 实际秒杀时间
     * @return
     */
    int reduceNumber(@Param("seckillId") long seckillId, @Param("killTime") Date killTime);

    /**
     * 查询单个的秒杀活动条目
     * @param seckillId
     * @return
     */
    Seckill querySeckill(long seckillId);

    /**
     * 根据偏移量 查询所有的秒杀活动条目
     * @param offset
     * @param limit
     * @return
     */
    List<Seckill> queryAllSeckills(@Param("offset") int offset, @Param("limit")int limit);
}
