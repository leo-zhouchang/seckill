var seckill = {
    URL : {
        now : function(){return '/seckill/now/time';},
        pubisher : function (seckillId) {
            return '/seckill/'+seckillId+'/publisher';
        },
        exection: function(seckillId,md5){
            return '/seckill/' + seckillId + '/' + md5 + '/seckill';
        }
    },
    phoneValidate:function(phoneNum){
        if(phoneNum && phoneNum.length > 0){
            return true;
        }else return false;
    },
    countDown:function(seckillId,nowTime,startTime,endTime){
        var seckillBox = $("#seckill-box");
        if(nowTime > endTime){ //秒杀已结束
            seckillBox.hide().html('活动已结束!').show(500);
        }else if(nowTime < startTime){
            //倒计时
            var killTime = new Date(startTime + 1000);
            seckillBox.countdown(killTime,function(e){
                var format = e.strftime('剩余：%D天%H小时%M分%S秒');
                seckillBox.html(format);
            }).on('finish.countdown',function(){
                seckill.startSeckill(seckillId,seckillBox);
            });
        }else{
            //开抢
            seckill.startSeckill(seckillId,seckillBox);
        }
    },
    startSeckill : function (seckillId,seckillBox) {
        seckillBox.hide().html('<button class="btn btn-success btn-lg" id="killBtn">立即抢购</button>');
        $.post(seckill.URL.pubisher(seckillId),{},function (publishResult) {
            if(publishResult && publishResult.success){
                var pubisher = publishResult.data;
                var isPublish = pubisher.published;

                if(isPublish){
                    var md5 = pubisher.md5;
                    var killUrl = seckill.URL.exection(seckillId,md5);
                    $("#killBtn").one('click',function () {
                        $(this).addClass('disabled');
                        $.post(killUrl,{},function (res) {
                            if(res && res.success){
                                var seckillData = res.data;
                                seckillBox.html('<label class="label label-success">'+ seckillData.message +'</label>');
                            }
                        });
                    });
                    seckillBox.show();
                }else{
                    //倒计时
                    var start = pubisher.start;
                    var end = pubisher.end;
                    var now = pubisher.now;
                    //seckill.countDown(seckillId,now,start,end);
                }
            }else{
                console.log(publishResult);
            }
        })
    },
    detail : {
        init : function(parameters){
            var killPhone = $.cookie('userId');
            var seckillId = parameters.seckillId;
            var startTime = parameters.startTime;
            var endTime = parameters.endTime;
            if(!seckill.phoneValidate(killPhone)){
                var killPhoneModal = $("#userIdModel");
                killPhoneModal.modal({
                    show : true,
                    backdrop:'static',
                    keyboard:false
                });
                $('#killPhoneBtn').click(function(e){
                    var inputPhone = $('#killphone').val();
                    if(seckill.phoneValidate(inputPhone)){
                        $.cookie('userId',inputPhone,{expires:7,path:'/seckill'});
                        window.location.reload();
                    }else{
                        $("#killphoneMessage").hide().html('<label class="label label-danger">用户名错误!</label>').show(300);
                    }
                });
            }else{
                $.get(seckill.URL.now(),{},function (result) {
                    if(result && result.success){
                        var nowTime = result.data;
                        seckill.countDown(seckillId,nowTime,startTime,endTime);
                    }
                });
            }
        }
    }
}