<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="cp" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="zh-CN">
  <head>
      <meta charset="utf-8">
      <meta http-equiv="X-UA-Compatible" content="IE=edge">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <title>购物+</title>
      <link href="${cp}/css/bootstrap.min.css" rel="stylesheet">
      <link href="${cp}/css/style.css" rel="stylesheet">

      <script src="${cp}/js/jquery.min.js" type="text/javascript"></script>
      <script src="${cp}/js/bootstrap.min.js" type="text/javascript"></script>
      <script src="${cp}/js/layer.js" type="text/javascript"></script>
    <!--[if lt IE 9]>
      <script src="${cp}/js/html5shiv.min.js"></script>
      <script src="${cp}/js/respond.min.js"></script>
    <![endif]-->
  </head>
  <body>
    <!--导航栏部分-->
    <jsp:include page="include/header.jsp"/>

    <!-- 中间内容 -->
    <div class="container-fluid" style="padding-top: 80px;padding-bottom: 80px" >

        <h1 class="title center">登录</h1>
        <br/>
        <div class="col-sm-offset-2 col-md-offest-2">
            <!-- 表单输入 -->
            <div  class="form-horizontal">
                <div class="form-group">
                    <label for="inputEmail" class="col-sm-2 col-md-2 control-label">邮箱/用户名</label>
                    <div class="col-sm-6 col-md-6">
                        <input type="text" class="form-control" id="inputEmail" placeholder="xxxxxx@xx.com"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword" class="col-sm-2 col-md-2 control-label">密码</label>
                    <div class="col-sm-6 col-md-6">
                        <input type="password" class="form-control" id="inputPassword" placeholder="禁止输入非法字符" />
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-6">
                        <button class="btn btn-lg btn-primary btn-block" type="submit" onclick="startLogin()">登录</button>
                    </div>
                </div>
            </div>
            <br/>
        </div>
    </div>

    <!--尾部-->
    <jsp:include page="include/foot.jsp"/>

    <script type="text/javascript">
        function startLogin() {
            var loading = layer.load(0);
            var user = {};
            var loginResult = "";
            user.userNameOrEmail = document.getElementById("inputEmail").value;
            user.password = document.getElementById("inputPassword").value;
            $.ajax({
                async : false,
                type : 'POST',
                url : '${cp}/doLogin',
                data : user,
                dataType : 'json',
                success : function(result) {
                    loginResult = result.result;
                    layer.close(loading);
                },
                error : function(result) {
                    layer.alert('查询用户错误');
                }
            });

            if(loginResult == 'success'){
                layer.msg('登录成功',{icon:1});
                window.location.href = "${cp}/main";
            }
            else if(loginResult == 'unexist'){
                layer.msg('是不是用户名记错了？',{icon:2});
            }
            else if(loginResult == 'wrong'){
                layer.msg('密码不对哦，再想想~',{icon:2});
            }
            else if(loginResult == 'fail'){
                layer.msg('服务器异常',{icon:2});
            }

        }
    </script>

  </body>
</html>