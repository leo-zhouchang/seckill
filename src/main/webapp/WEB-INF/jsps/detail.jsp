<%@ page import="com.fasterxml.jackson.annotation.JsonInclude" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common-tag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>秒杀商品详情</title>
    <%@include file="common-head.jsp" %>
</head>
<body>
<div class="container">
    <div class="panel default-panel text-center">
        <div class="panel-heading">
            <h1 class="text-center">${seckill.name}</h1>
        </div>
        <div class="panel-body">
            <h2 class="text-danger">
                <span class="glyphicon glyphicon-time"></span><!--记时图标-->
                <span class="glyphicon" id="seckill-box"></span><!--倒计时-->
            </h2>
            <!--TODO-->
        </div>
    </div>

    <div id="userIdModel" class="modal fade">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h3 class="modal-title text-center">
                        <span class="glyphicon glyphicon-phone"></span>用户电话
                    </h3>
                </div>
                <div class="modal-body">
                    <div class="row">
                        <div class="col-xs-8 col-xs-offset-2">
                            <input type="text" name="userId" id="killphone" placeholder="请填写您的手机号^o^" class="form-control" />
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <span id="killphoneMessage" class="glyphicon"></span>
                    <button class="btn btn-success" id="killPhoneBtn">
                        <span class="glyphicon glyphicon-phone"></span>
                        Submit
                    </button>
                </div>
            </div>
        </div>
    </div>
</div><!--container-->
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>

<script type="text/javascript" src="http://cdn.bootcss.com/jquery-cookie/1.4.1/jquery.cookie.min.js"></script>

<script type="text/javascript" src="http://cdn.bootcss.com/jquery.countdown/2.2.0/jquery.countdown.min.js"></script>

<script type="text/javascript" src="/resource/script/seckill.js"></script>
<script type="text/javascript">
    $(function(){
        seckill.detail.init({
            seckillId:${seckill.seckillId},
            startTime:${seckill.startTime.time},
            endTime:${seckill.endTime.time}
        });
    });
</script>
</body>
</html>
