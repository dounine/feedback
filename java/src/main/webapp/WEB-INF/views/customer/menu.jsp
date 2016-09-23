<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div  >
	<ul>
		<li class="li-root" >
			<a href="javascript:;" ng-click="flex1()" ><span ng-class="{true: 'icon_right', false: 'icon_bonton'}[addcla]"></span> 委托书类型</a>
			<ul ng-hide="addmenu">
				<li class="li-sec" ui-sref-active="active"><a href="javascript:;" ui-sref="index" ><span style="background:url(${ctx}/public/modules/customer/app/img/form/book.png); background-size: cover;" ></span> 检测委托书</a></li>
				<li class="li-sec" ui-sref-active="active" ><a href="javascript:;" ><span style="background:url(${ctx}/public/modules/customer/app/img/form/book2.png); background-size: cover;"></span> 其他委托书</a></li>
			</ul>
		</li>
		
		<li class="li-root" ui-sref-active="active">
			<a href="javascript:;" ui-sref="userinfo"><span class="icon_right"></span> 用户信息</a>
		</li>
		
		<li class="li-root">
			<a href="javascript:;" ng-click="flex2()">
				<span ng-class="{true: 'icon_right', false: 'icon_bonton'}[infocla]"></span> 
			<font>信息处理</font></a>
			<ul ng-hide="information">
				<li class="li-sec" ui-sref-active="active"><a href="javascript:;"  ui-sref="unpro"><span style="background:url(${ctx}/public/modules/customer/app/img/form/wcl.png); background-size: cover;"></span>  未处理信息</a></li>
				<li class="li-sec" ui-sref-active="active"><a href="javascript:;"  ui-sref="proing"><span style="background:url(${ctx}/public/modules/customer/app/img/form/clz.png); background-size: cover;"></span> 处理中信息</a></li>
				<li class="li-sec" ui-sref-active="active"><a href="javascript:;"  ui-sref="proed"><span style="background:url(${ctx}/public/modules/customer/app/img/form/clwb.png); background-size: cover;"></span> 处理完毕</a></li>
			</ul>
		</li>
		<li class="li-root" ui-sref-active="active">
			<a href="javascript:;"><span class="icon_right"></span> 其他功能</a>
			
		</li>
	</ul>
</div>
