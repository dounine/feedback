<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="container reg2 ">
	<img src="${ctx}/public/modules/register/app/img/form/reg2.png"/>

	<div class="row">
		<div class="reg2-con col-lg-10 col-sm-offset-1">
			<div><strong>验证链接已发送至</strong><span>{{emailName}}</span></div>
			<p>请登录邮箱，点击激活链接完成注册，激活链接1小时有效。</p>
			<button class="btn btn-danger" ng-click="reciveMail()">请查收邮件</button>
		</div>
	</div>

</div>