<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>注册页面</title>

	<title>feedback user</title>
	<link rel="stylesheet" href="${ctx}/public/lib/bootstrap/dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="${ctx}/public/lib/angular-loading-bar/build/loading-bar.min.css" type='text/css'
		  media='all'>
	<link rel="stylesheet" href="${ctx}/public/modules/register/app/css/register.css">
	<link rel="shortcut icon" href="${ctx}/public/favicon.ico" />
</head>
<body ng-app="app" style="width: 900px;margin: 0 auto;">
<header class="container"><a href="/"><img src="${ctx}/public/modules/register/app/img/form/Feedbacklogo.png"/></a></header>
<h4 class="container">用户注册</h4>

<div ui-view></div>

<div class="container text-center m-t-lg">
	Copyright <span>&copy</span>2004-2016 12345.com版权所有
</div>

<script src="${ctx}/public/lib/requirejs/require.js"></script>
<script>
	require(['${ctx}/public/modules/register/app/js/main.js?_t=' + new Date().getTime()]);
</script>
</body>
</html>
