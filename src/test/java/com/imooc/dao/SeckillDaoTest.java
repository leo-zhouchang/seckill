package com.imooc.dao;


import com.imooc.model.Seckill;
import org.junit.Test;
import org.junit.runner.RunWith;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

import java.util.Date;
import java.util.List;


/**
 * Created by zhouchang on 2016/12/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class SeckillDaoTest {

    @Resource
    private SeckillDao seckillDao;

    @Test
    public void querySeckill() throws Exception {
        long id = 1000;
        Seckill seckill = this.seckillDao.querySeckill(id);
        System.out.println(seckill);
    }

    @Test
    public void queryAllSeckills() throws Exception {
        List<Seckill> seckills = this.seckillDao.queryAllSeckills(0,10);
        System.out.println(seckills);
    }

    @Test
    public void reduceNumber() throws Exception {
        Date d = new Date();
        int influencedRowNum = seckillDao.reduceNumber(1000l,d);
        System.out.println(influencedRowNum);
    }

}