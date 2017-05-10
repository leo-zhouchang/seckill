package com.imooc.dao;

import com.imooc.model.Order;
import org.apache.ibatis.annotations.Param;

/**
 * Created by zhouchang on 2016/12/2.
 */
public interface OrderDao {

    /**
     * 插入订单对象
     * @param seckillId 秒杀活动条目Id
     * @param userId 用户id
     * @return 返回值 > 0 表示插入成功
     */
    int saveOrder(@Param("seckillId") long seckillId, @Param("userId") String userId);

    /**
     * 根据ID查询 Order 并携带 Seckill 实体
     * @param seckillId
     * @return
     */
    Order queryOrderWithSeckillById(@Param("seckillId") long seckillId, @Param("userId") String userId);
}
