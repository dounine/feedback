<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link rel="stylesheet" href="${ctx}/public/js/app/css/cdd/file/list.css">
<nav class="navbar navbar-light">
	<ul class="nav navbar-nav">
		<li class="nav-item"><a class="btn btn-primary">上传</a></li>
		<li class="nav-item"><a class="btn btn-secondary" ng-click="folder_create">创建文件夹</a></li>
	</ul>
	<form class="form-inline navbar-form pull-right">
		<input class="form-control" type="text" ng-model="fileName" placeholder="请输入文件名">
		<button class="btn btn-secondary" type="submit" ui-sref="fileSearch({path:fileName})" ng-disabled="!fileName">搜索</button>
	</form>
</nav>
<hr style="margin-bottom: 0">
<ol class="breadcrumb" style="background: none;" ng-init="fgt1=navs.length>1">
	<li ng-class="$last?'active':''" ng-repeat="nav in navs">
		<a ng-if="fgt1&&!$last" href="javascript:void(0)" ng-click="nav_click(nav)">{{nav.oriName}}</a>
		<text ng-if="$last">{{nav.oriName}}</text>
	</li>
</ol>
<ul class="ico-list">
	<li ng-animate="slide-top" class="filelist-item" ng-if="file.oriName"
		ng-dblclick="item_dblclick(this)" ng-repeat="file in files">
		<div>
			<span class="ico" ng-class="suffix_ico(file.fileType)">
				<span class="img-sibling"></span>
				<img onload="this.parentNode.className='ico show-thumb'" ng-src="{{file.imgSrc}}" ng-init="file_img_init(this)" >
			</span>
			<span class="txt">{{file.oriName}}</span>
		</div>
	</li>
</ul>
<div class="fileListMain" ng-if="emptyFolder"
	ng-cloak>
	<div ng-if="!cddSearchData.search" class="listHolder" class="clearfix" style="height: 350px;">
		<ul class="clearfix ico-list"></ul>
		<div class="emptyTips">
			<img
				src="${ctx}/static/admin/img/clouddisk/icon/file/my_empty_folder.gif"
				alt="空文件夹" title="空文件夹">
			<h1>您的这个文件夹还是空的哦~</h1>
		</div>
	</div>
	<div ng-if="cddSearchData.search" class="emptyTips">
		<img src="${ctx}/public/js/app/img/cdd/file/not-search-file.jpg" alt="没有到相关文件" title="没有到相关文件">
	</div>
</div>