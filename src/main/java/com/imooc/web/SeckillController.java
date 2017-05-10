package com.imooc.web;

import com.imooc.dto.ExecuteSeckillResult;
import com.imooc.dto.PublishResult;
import com.imooc.dto.SeckillResult;
import com.imooc.enums.SeckillEnum;
import com.imooc.exception.ClosedSeckillException;
import com.imooc.exception.RepeatSeckillException;
import com.imooc.exception.SeckillException;
import com.imooc.model.Seckill;
import com.imooc.service.SeckeillService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Date;
import java.util.List;

/**
 * Created by zhouchang on 2016/12/4.
 */
@Controller
@RequestMapping(value={"/seckill"})
public class SeckillController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private SeckeillService seckeillService;

    @RequestMapping(value = {"/list"},
            method = {RequestMethod.GET})
    public String list(Model model){
        List<Seckill> list = this.seckeillService.queryAllSeckills(0,10);
        model.addAttribute("list",list);
        return "list";
    }

    @RequestMapping(value = {"/{seckillId}/detail"},
            method = RequestMethod.GET)
    public String detail(Model model, @PathVariable Long seckillId){
        if(seckillId == null){
            return "redirect:/seckill/list";
        }
        Seckill seckill = this.seckeillService.querySeckillById(seckillId);
        if(seckill == null){
            return "forward:/seckill/list";
        }
        model.addAttribute("seckill",seckill);
        return "detail";
    }

    @RequestMapping(value = {"/{seckillId}/publisher"},
            method = {RequestMethod.POST},
            produces = {"application/json;charset=UTF-8"})
    @ResponseBody
    public SeckillResult<PublishResult> publishAddr(@PathVariable Long seckillId){
        SeckillResult<PublishResult> result = null;
        try {
            PublishResult publishResult = this.seckeillService.publishSeckillUrl(seckillId);
            result = new SeckillResult<PublishResult>(true,publishResult);
        } catch (Exception e) {
            logger.error(e.getLocalizedMessage());
            result = new SeckillResult<PublishResult>(false,e.getMessage());
        }
        return result;
    }

    @RequestMapping(value = {"/{seckillId}/{md5}/seckill"},
            method = RequestMethod.POST,
            produces = "application/json;charset=UTF-8")
    @ResponseBody
    public SeckillResult<ExecuteSeckillResult> executeSeckill(@PathVariable Long seckillId,
                                                              @PathVariable String md5,
                                                              @CookieValue(required = false) String userId){

        if(userId == null || "".equals(userId)){
            new SeckillResult<ExecuteSeckillResult>(false,"用户未注册");
        }
        try {
            ExecuteSeckillResult executeSeckillResult = this.seckeillService.seckillExecution(seckillId,userId,md5);
            return new SeckillResult<ExecuteSeckillResult>(true,executeSeckillResult);
        } catch (RepeatSeckillException er){
            ExecuteSeckillResult executeSeckillResult = new ExecuteSeckillResult(false,seckillId, SeckillEnum.REPEATED);
            return new SeckillResult<ExecuteSeckillResult>(true,executeSeckillResult);
        } catch (ClosedSeckillException ec){
            ExecuteSeckillResult executeSeckillResult = new ExecuteSeckillResult(false,seckillId, SeckillEnum.END);
            return new SeckillResult<ExecuteSeckillResult>(true,executeSeckillResult);
        } catch (SeckillException e) {
            logger.error(e.getLocalizedMessage());
            ExecuteSeckillResult executeSeckillResult = new ExecuteSeckillResult(false,seckillId, SeckillEnum.INNER_ERROR);
            return new SeckillResult<ExecuteSeckillResult>(true,executeSeckillResult);
        }
    }

    @RequestMapping(value="/now/time",method = RequestMethod.GET,
            produces = "application/json;charset=UTF-8")
    public @ResponseBody SeckillResult<Long> time(){
        Date nowTime = new Date();
        return new SeckillResult<Long>(true,nowTime.getTime());
    }
}
