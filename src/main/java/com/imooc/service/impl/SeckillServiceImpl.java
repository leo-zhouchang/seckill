package com.imooc.service.impl;

import com.imooc.dao.OrderDao;
import com.imooc.dao.RedisDao;
import com.imooc.dao.SeckillDao;
import com.imooc.dto.ExecuteSeckillResult;
import com.imooc.dto.PublishResult;
import com.imooc.enums.SeckillEnum;
import com.imooc.exception.ClosedSeckillException;
import com.imooc.exception.RepeatSeckillException;
import com.imooc.exception.SeckillException;
import com.imooc.model.Order;
import com.imooc.model.Seckill;
import com.imooc.service.SeckeillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.Date;
import java.util.List;

/**
 * Created by zhouchang on 2016/12/3.
 */
@Service("seckillService")
public class SeckillServiceImpl implements SeckeillService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired(required = true)
    private SeckillDao seckillDao;

    @Autowired(required = true)
    private OrderDao orderDao;

    @Autowired
    private RedisDao redisDao;

    private static String SALT = "dJS!dFd@dWi#f3284djf12133$%7DDhSKWWI(A*&D^%S#@aff";

    public List<Seckill> queryAllSeckills(int offset, int limit) {
        return this.seckillDao.queryAllSeckills(offset,limit);
    }

    public Seckill querySeckillById(long seckillId) {
        Seckill seckill = this.redisDao.getSeckillFromRedis(seckillId);
        if(seckill == null){
            seckill = this.seckillDao.querySeckill(seckillId);
            if(seckill == null){
                return null;
            }else{
                this.redisDao.putSeckillIntoRedis(seckill);
            }
        }
        return seckill;
    }

    public PublishResult publishSeckillUrl(long seckillId) {

        Seckill seckill = this.redisDao.getSeckillFromRedis(seckillId);
        if(seckill == null){
            seckill = this.seckillDao.querySeckill(seckillId);
            if(seckill == null){
                return new PublishResult(false,seckillId);
            }else{
                this.redisDao.putSeckillIntoRedis(seckill);
            }
        }

        Date start = seckill.getStartTime();
        Date end = seckill.getEndTime();
        Date now = new Date();
        if(start.getTime() > now.getTime() || now.getTime() > end.getTime()){
            return new PublishResult(false,seckillId,start.getTime(),end.getTime(),now.getTime());
        }
        String md5 = this.getMD5(seckillId);
        return new PublishResult(true,seckillId,md5);
    }

    @Transactional
    public ExecuteSeckillResult seckillExecution(long seckillId, String userId, String md5) throws SeckillException, ClosedSeckillException, RepeatSeckillException {
        try {
            if(md5 == null || !md5.equals(this.getMD5(seckillId))){
                throw new SeckillException("data rewrote!");
            }
            Date nowTime = new Date();
            int updatedRowNum = this.seckillDao.reduceNumber(seckillId,nowTime);
            if(updatedRowNum <= 0){
                throw new ClosedSeckillException("seckill colsed!");
            }else {
                int insertedNum = this.orderDao.saveOrder(seckillId,userId);
                if (insertedNum <= 0 ){
                    throw new RepeatSeckillException("repeated seckill!");
                }else {
                    Order order = this.orderDao.queryOrderWithSeckillById(seckillId,userId);
                    return new ExecuteSeckillResult(true,seckillId, SeckillEnum.SUCCESS,order);
                }
            }
        }catch (ClosedSeckillException e){
            throw e;
        }catch (RepeatSeckillException e2){
            throw e2;
        }catch (SeckillException e3){
            logger.error(e3.getLocalizedMessage(),e3);
            throw e3;
        }
    }

    private String getMD5(long seckillId){
        String source = seckillId + "/" + SALT;
        return DigestUtils.md5DigestAsHex(source.getBytes());
    }
}
