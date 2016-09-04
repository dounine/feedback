<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE HTML>
<HTML xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>用户添加</title>
	</head>
    <style>
        div li{
            padding: 0 10px;
        }
        div li:nth-child(odd){
            background: #cccccc;
        }
        form input{
            margin:4px;
        }
    </style>
	<body style="padding: 20px">
        <form action="${ctx}/user/add" method="post">
            <input type="text" name="username" /><br/>
            <input type="password" name="password" /></br>
            <input type="radio" name="userType" checked value="MANAGER" id="m"><label for="m">管理员</label> <input value="CUSTOM" type="radio" name="userType" id="w"><label for="w">客户</label><br/>
            <span style="color:red">${msg}</span></br>
            <input type="submit" value="添加" />
            <a href="${ctx}/admin/index" />返回</a>
        </form>

        <div style="width:300px;margin:10px auto;">
            <c:forEach var="user" items="${users}">
                <li style="width: 300px;list-style: none;float: left;height: 30px;line-height: 30px;border-bottom: 1px solid #cccccc;"><a style="float: left;text-decoration: none" href="#">${user.username}</a><span style="float:right;">
                    <c:choose>
                    <c:when test='${user.userType=="MANAGER"}'>
                        管理员${i}
                    </c:when>
                    <c:otherwise>
                        客户
                    </c:otherwise>
                </c:choose>
                </span></li>
            </c:forEach>
        </div>
	</body>
</html>