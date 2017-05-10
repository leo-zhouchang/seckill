package com.imooc.dao;

import com.imooc.model.Order;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by zhouchang on 2016/12/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class OrderDaoTest {

    @Autowired(required = true)
    private OrderDao orderDao;

    @Test
    public void saveOrder() throws Exception {
        int i = this.orderDao.saveOrder(1000l,"zhouchang");
        System.out.println("influenced row:" + i);
    }

    @Test
    public void queryOrderWithSeckillById() throws Exception {
        Order order = this.orderDao.queryOrderWithSeckillById(1000L,"zhouchang");
        System.out.println(order);
    }

}