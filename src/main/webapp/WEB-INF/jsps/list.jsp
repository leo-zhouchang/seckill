<%@ page import="com.fasterxml.jackson.annotation.JsonInclude" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="common-tag.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>秒杀商品列表</title>
    <%@include file="common-head.jsp" %>
</head>
<body>
    <div class="container">
        <div class="panel default-panel">
            <div class="panel-heading text-center">
                <h2>秒杀商品列表</h2>
            </div>
            <div class="panel-body">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>名称</th>
                            <th>库存</th>
                            <th>开始时间</th>
                            <th>结束时间</th>
                            <th>创建时间</th>
                            <th>详情</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="sk" items="${list}">
                            <tr>
                                <td>${sk.name}</td>
                                <td>${sk.number}</td>
                                <td>
                                    <fmt:formatDate value="${sk.startTime}" pattern="yyyy-MM-dd HH:mm:ss" />
                                </td>
                                <td>
                                    <fmt:formatDate value="${sk.endTime}" pattern="yyyy-MM-dd HH:mm:ss" />
                                </td>
                                <td>
                                    <fmt:formatDate value="${sk.createTime}" pattern="yyyy-MM-dd HH:mm:ss" />
                                </td>
                                <td><a href="/seckill/${sk.seckillId}/detail" class="btn btn-info" target="_blank">Link</a></td>
                            </tr>
                        </c:forEach>
                    </tbody>

                </table>
            </div>
        </div>
        <div class="panel pandel-default">
            <form role="form">
                <div class="form-group">
                    <input type="text" class="form-control" id="source" placeholder="^o^">
                </div>
                <div class="form-group">
                    <input type="text" class="form-control" id="result" value="" />
                </div>
                <div class="form-group">
                    <input type="button" class="btn btn-success" id="submit" onclick="code('encode')" value="submit" />
                    <input type="button" class="btn btn-success" id="resubmit" onclick="code('decode')" value="resubmit" />
                </div>
            </form>
        </div>
    </div><!--container-->
<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.static.runoob.com/libs/bootstrap/3.3.7/js/bootstrap.min.js"></script>
<script type="text/javascript">
    function code(type) {
        var str = $('#source').val();
        if(str != ""){
            var url = "/code/ende/coding";
            $.post(url,{str:str,type:type},function(data){
                $('#result').val(data);
            });
        }

    }

</script>
</body>
</html>
