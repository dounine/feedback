<%@ page language="java" contentType="text/html; charset=UTF-8"
pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<html>
<head lang="en">
    <meta charset="UTF-8">
    <title>feedback 登录</title>
    <link rel="stylesheet" href="${ctx}/public/lib/bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="${ctx}/public/app/login/css/login.css">
    <script src="${ctx}/public/lib/angular/angular.js"></script>
    <script src="${ctx}/public/app/login/js/login.js"></script>

</head>
<body>
    <div class="loginForm container" ng-app="myApp">
        <div class="loginbody">
            <div class="logo"><img src="${ctx}/public/app/login/imgs/feedback.png"/></div>
            <form action="${ctx}/login" method="post" class="form-horizontal" name="signUp" ng-controller="form">
                <div class="form-group" >
                    
                    <div class=" input_con ">
                    	<div class="username pull-left"></div>
                    	<div class="pull-left">
                    		<input type="text" class="form-control" placeholder="请输入用户名" class="userinput"
                                ng-class="{'form-err':signUp.username.$invalid && signUp.username.$touched ,'form-success':signUp.username.$valid}"
                                name="username" required ng-maxlength ="18" ng-minlength ="5"
                                ng-model="userdata.username"/>
                            <input type="text" name="user.name" value="nihao"/>
                    	</div>
                    

                    </div>
                </div>
                <div class="form-group">
                    
                    <div class=" input_con">
                    	<div class="passwo pull-left"></div>
                    	<div class="pull-left">
                    		<input type="password" class="form-control" placeholder="请输入密码"
                                ng-class="{'form-err':signUp.password.$invalid && signUp.password.$touched ,'form-success':signUp.password.$valid}"
                                name="password" required ng-maxlength ="18" ng-minlength ="6"
                                ng-model="userdata.password"/>
                    	</div>


                    </div>
                </div>
                <div class="tis">
                    <c:if test="${msg==null}">
                	<p class="form-iferr" ng-if="(signUp.username.$error.minlength||signUp.username.$error.maxlength) && signUp.username.$touched">用户名长度在5-18位之间</p>
                	<p class="form-iferr" ng-if="(signUp.password.$error.minlength||signUp.password.$error.maxlength) && signUp.password.$touched">密码长度在6-18位之间</p>
                    </c:if>
                    <c:if test="${msg!=null}">
                        <p class="form-iferr">${msg}</p>
                    </c:if>
                </div>

               	<div class="sign">
               		<button ng-disabled="signUp.username.$error.minlength||signUp.username.$error.maxlength||signUp.password.$error.minlength||signUp.password.$error.maxlength" class="btn btn-info btn-lg sign_btn">登陆</button>
               	</div>
            </form>
        </div>
    </div>
</body>
</html>