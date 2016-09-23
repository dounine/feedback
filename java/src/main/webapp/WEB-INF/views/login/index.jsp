<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE HTML>
<HTML xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<title>feedback user</title>
		<link rel="stylesheet" href="${ctx}/public/lib/bootstrap/dist/css/bootstrap.min.css">
		<link rel="stylesheet" href="${ctx}/public/lib/angular-loading-bar/build/loading-bar.min.css" type='text/css' media='all' >
		<link rel="stylesheet" href="${ctx}/public/modules/login/app/css/login.css">
		<link rel="shortcut icon" href="${ctx}/public/favicon.ico" />
	</head>
	<body ng-app="app" ctx="/">
		<div ui-view></div>
		<script src="${ctx}/public/lib/requirejs/require.js"></script>
		<script>
			require(['${ctx}/public/modules/login/app/js/main.js?_t='+new Date().getTime()]);
		</script>
	</body>
</html>
