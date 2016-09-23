<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Feedback</title>
		<link rel="stylesheet" href="${ctx}/public/lib/bootstrap/dist/css/bootstrap.min.css">
		<link rel="stylesheet" href="${ctx}/public/modules/customer/app/css/customer.css" type='text/css' media='all' >
        <link rel="shortcut icon" href="${ctx}/public/favicon.ico" />
	</head>
	<body ng-app="app">
		<header>
			 <!--<div class="notice">-->
		    	<!--<p>公告</p>-->
		    <!--</div>-->
	    	<div class="logo">
	    		<a href="/"><img src="${ctx}/public/modules/customer/app/img/form/feedback.png" class="pull-left"/></a>
		    		<div class="search pull-right">
		    		<input type="text" name="detectionNum" size="15" placeholder="输入关键字" style="text-indent:10px ;"/>
		    		<span><a href="Javascript:;"class="btn btn-primary">搜索</a></span>
                    <a style="margin-left: 16px;color: #ffffff;" href="/logout">退出</a>
		    	</div>

	    	</div>
	    </header>
	    
	    <div ui-view class="wrapbody"></div>
		<script src="${ctx}/public/lib/requirejs/require.js"></script>
		<script>
			require(['${ctx}/public/modules/customer/app/js/main.js?_t='+new Date().getTime()]);
		</script>

	</body>
</html>
