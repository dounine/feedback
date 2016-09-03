<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<HTML xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>dounine 集成测试框架</title>
		<link rel="stylesheet" href="${ctx}/public/js/lib/bootstrap/bootstrap.css">
		<link rel="stylesheet" href="${ctx}/public/js/lib/angular-loading-bar/loading-bar.css">
		<link rel="icon" href="${ctx}/static/public/favicon/favicon-48.ico" sizes="48x48">
		<link rel="stylesheet" href="${ctx}/static/admin/app/css/base.css">
	</head>
	<body ng-app="dnnApp" class="container" ctx="${ctx}">
		<div ui-view></div>
		<script></script>
		<script src="${ctx}/public/js/lib/requirejs/require.js"></script>
		<script>
			require(['${ctx}/public/js/main.js?______t='+new Date().getTime()]);
		</script>
	</body>
</html>