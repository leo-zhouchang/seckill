package com.imooc.service.impl;

import com.imooc.dto.ExecuteSeckillResult;
import com.imooc.dto.PublishResult;
import com.imooc.model.Seckill;
import com.imooc.service.SeckeillService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.*;

/**
 * Created by zhouchang on 2016/12/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class SeckillServiceImplTest {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckeillService seckeillService;

    @Test
    public void queryAllSeckills() throws Exception {
        List<Seckill> seckillList = this.seckeillService.queryAllSeckills(0,10);
        logger.debug("seckillList - > {}",seckillList);
    }

    @Test
    public void querySeckillById() throws Exception {
        Seckill seckill = this.seckeillService.querySeckillById(1001L);
        logger.debug("seckill - > {}" , seckill);
    }

    @Test
    public void publishSeckillUrl() throws Exception {
        PublishResult publishResult = this.seckeillService.publishSeckillUrl(1000L);
        logger.debug("publishResult= {}",publishResult);
        if(publishResult != null && publishResult.isPublished()){
            ExecuteSeckillResult executeSeckillResult =  this.seckeillService.seckillExecution(publishResult.getSeckillId(),"zhouchang",publishResult.getMd5());
            logger.debug("executeSeckillResult=> {}",executeSeckillResult);
        }
    }


}