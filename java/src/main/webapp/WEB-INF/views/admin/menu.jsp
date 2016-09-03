<%@ page language="java" contentType="text/html; charset=UTF-8"
		 pageEncoding="UTF-8"%>
<style>
	.pointer:hover{
		color: blue;
	}
</style>
<div class="jumbotron">
	<h1>feeback 客户模块系统!</h1>
	<p>...</p>
	<p><a class="btn btn-primary btn-lg" href="#" role="button">查看详细</a></p>
</div>
<ul class="list-group">
	<li class="list-group-item">
		<div class="row">
			<div class="col-md-8">
				<div class="page-header">
					<h1>360云盘接口 <small>用于数据存储</small></h1>
				</div>
			</div>
			<div class="col-md-4">
				<button class="btn btn-primary" ui-sref="clouddisk">开始使用</button>
				<button class="btn btn-secondary" ng-click="clear()" data-toggle="popover" title="Popover title" data-content="And here's some amazing content. It's very engaging. Right?">清除缓存</button>
			</div>
		</div>
	</li>
	<li class="list-group-item">
		<div class="row">
			<div class="col-md-8">
				<div class="page-header">
					<h1>客户检验系统 <small>用于跟踪同步</small></h1>
				</div>
			</div>
			<div class="col-md-4">
				<button class="btn btn-primary" ui-sref="feeback">开始使用</button>
			</div>
		</div>
	</li>
</ul>

<div class="bd-example">
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
						<span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel">温馨提示</h4>
				</div>
				<div class="modal-body">
					<span>用户缓存信息已经清空!</span>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
				</div>
			</div>
		</div>
	</div>
</div>