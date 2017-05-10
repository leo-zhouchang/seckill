package com.imooc.dao;

import com.imooc.model.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by zhouchang on 2016/12/11.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class RedisDaoTest {
    @Autowired
    private RedisDao redisDao;
    @Autowired
    private SeckillDao seckillDao;
    @Test
    public void getSeckillFromRedis() throws Exception {
        long seckillId = 1000L;
        Seckill seckill = redisDao.getSeckillFromRedis(seckillId);
        if(seckill == null){
            seckill = seckillDao.querySeckill(seckillId);
            if(seckill != null){
                redisDao.putSeckillIntoRedis(seckill);
                Seckill seckill1 = redisDao.getSeckillFromRedis(seckillId);
                System.out.println(seckill1);
            }
        }
    }


}