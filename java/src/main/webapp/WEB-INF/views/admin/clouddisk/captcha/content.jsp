<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<div class="bd-example" ng-init="show_modal()">
	<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
		<div class="modal-dialog" role="document">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close">
						<span aria-hidden="true">&times;</span>
						<span class="sr-only">Close</span>
					</button>
					<h4 class="modal-title" id="exampleModalLabel">云盘验证安全</h4>
				</div>
				<div class="modal-body">
					<form role="form">
						<div class="form-group pointer">
							<div style="padding:1em 0;">
								<img ng-show="cddCaptchaData.url" class="pointer" src="static/admin/img/clouddisk/icon/gifs/loading.gif" ng-click="change_captcha()" style="margin-left: 22px;width: 40px;">
								<img ng-hide="cddCaptchaData.url" class="pointer" sb-load="captcha_load()" captchaload ng-src="{{cddCaptchaData.url}}" ng-click="change_captcha()" alt="验证码">
							</div>
						</div>
						<div class="form-group {{has_error}} has-feedback">
							<label for="captcha-value" class="control-label">验证码:</label>
							<input class="form-control" type="text" id="captcha-value" placeholder="请输入正确的验证码" ng-model="captchaValue">
							<span class="glyphicon glyphicon-ok form-control-feedback"></span>
						</div>
					</form>
				</div>
				<div class="modal-footer">
					<span style="padding: 6px;" class="label label-danger">{{valid_captcha_msg}}</span>
					<button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
					<button type="button" class="btn btn-primary" ng-click="valid_captcha()" ng-disabled="disSubmit">验证</button>
				</div>
			</div>
		</div>
	</div>
</div>