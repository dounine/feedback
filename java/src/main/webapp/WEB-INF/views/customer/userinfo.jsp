<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="mainbody">
	<div class="mainbody-top">
		<h5 class="pull-left">用户信息</h5>
	</div>
	<div class="col-lg-12 userinfo ">
		<form class="form-horizontal" role="form" name="userinfo " ng-init="s='has-success';e='has-error';">
			<fieldset class="form-group
                            {{userinfo.usename.$valid?s:e}}{{userinfo.usename.$dirty?'':'-'}}">
				<label  class="col-sm-1 control-label text-right">姓名：</label>
				<div class="col-sm-3">
					<input type="text" autofocus required class="form-control form-control-success form-control-error" placeholder="请输入姓名" ng-model="usename" name="usename" ng-minlength="2">
				</div>
			</fieldset>
			<fieldset class="form-group
                            {{userinfo.details_address.$valid?s:e}}{{userinfo.details_address.$dirty?'':'-'}}">
				<label  class="col-sm-1 control-label text-right">通讯地址：</label>
				<div class="col-sm-3">
					<input type="text" required class="form-control form-control-success form-control-error" placeholder="请输入通讯地址" ng-model="details_address" name="details_address" ng-maxlength="30" ng-minlength="2">
				</div>
			</fieldset>
			<fieldset class="form-group
                            {{userinfo.details_telephone.$valid?s:e}}{{userinfo.details_telephone.$dirty?'':'-'}}">
				<label  class="col-sm-1 control-label text-right">电话：</label>
				<div class="col-sm-3">
					<input type="text" required class="form-control form-control-success form-control-error" placeholder="请输入电话" ng-model="details_telephone" name="details_telephone" ng-maxlength="13" ng-minlength="7">
				</div>
			</fieldset>
			<fieldset class="form-group
                            {{userinfo.details_email.$valid?s:e}}{{userinfo.details_email.$dirty?'':'-'}}">
				<label  class="col-sm-1 control-label text-right">邮箱：</label>
				<div class="col-sm-3">
					<input type="email" required class="form-control form-control-success form-control-error" placeholder="请输入邮箱" ng-model="details_email" name="details_email" ng-maxlength="30" ng-minlength="6">
				</div>
			</fieldset>
			<fieldset class="form-group
                            {{userinfo.details_company.$valid?s:e}}{{userinfo.details_company.$dirty?'':'-'}}">
				<label  class="col-sm-1 control-label text-right">单位名称：</label>
				<div class="col-sm-3">
					<input type="text" required class="form-control form-control-success form-control-error" placeholder="请输入单位名称" ng-model="details_company" name="details_company" ng-maxlength="30" ng-minlength="4">
				</div>
			</fieldset>
			<fieldset class="form-group
                            {{userinfo.details_postcodes.$valid?s:e}}{{userinfo.details_postcodes.$dirty?'':'-'}}">
				<label  class="col-sm-1 control-label text-right">邮编：</label>
				<div class="col-sm-3">
					<input type="text" required class="form-control form-control-success form-control-error" placeholder="请输入邮编" ng-model="details_postcodes" name="details_postcodes" ng-maxlength="6" ng-minlength="6">
				</div>
			</fieldset>
			<fieldset class="form-group
                            {{userinfo.details_fax.$valid?s:e}}{{userinfo.details_fax.$dirty?'':'-'}}">
				<label  class="col-sm-1 control-label text-right">传真：</label>
				<div class="col-sm-3">
					<input type="text" required class="form-control form-control-success form-control-error" placeholder="请输入传真" ng-model="details_fax" name="details_fax" ng-maxlength="18" ng-minlength="7">
				</div>
			</fieldset>

			<fieldset class="button col-md-offset-1 col-lg-1">
				<button type="submit" ng-disabled="userinfo.$invalid" class="btn btn-danger">修 改</button>

			</fieldset>






		  <!--<div class="form-group row">-->
		    <!--<label for="inputEmail3" class="col-sm-2 control-label text-right"><span>*</span> 姓名：</label>-->
		    <!--<div class="col-sm-3">-->
		      <!--<input type="text" class="form-control" id="inputEmail3" required name="username" ng-model="username"-->
		      	<!--ng-class="{'form-err':userinfo.username.$invalid && userinfo.username.$touched ,'form-success':userinfo.username.$valid}">-->
		    <!--</div>-->
		    <!--<div class="col-sm-2 form-iferr" ng-if="userinfo.username.$error.required && userinfo.username.$touched">-->
		    	<!--用户名不能为空-->
		    <!--</div>-->
		  <!--</div>-->
		  <!--<div class="form-group row">-->
		    <!--<label for="inputPassword3" class="col-sm-2 control-label text-right"><span>*</span> 通讯地址：</label>-->
		    <!--<div class="col-sm-3">-->
		      <!--<input type="text" class="form-control" required name="details.address" ng-model="details.address"-->
		      	<!--ng-class="{'form-err':userinfo.details.address.$invalid && userinfo.details.address.$touched ,'form-success':userinfo.details.address.$valid}">-->
		    <!--</div>-->
		    <!--<div class="col-sm-2 form-iferr" ng-if="userinfo.details.address.$error.required && userinfo.details.address.$touched">-->
		    	<!--通讯地址不能为空-->
		    <!--</div>-->
		  <!--</div>-->
		<!--<div class="form-group row">-->
		    <!--<label for="inputPassword3" class="col-sm-2 control-label text-right"><span>*</span> 电话：</label>-->
		    <!--<div class="col-sm-3">-->
		      <!--<input type="tel" class="form-control"  required name="details.telephone" ng-model="details.telephone"-->
		      	<!--ng-class="{'form-err':userinfo.details.telephone.$invalid && userinfo.tel.$touched ,'form-success':userinfo.details.telephone.$valid}">-->
		    <!--</div>-->
		    <!--<div class="col-sm-2 form-iferr" ng-if="userinfo.details.telephone.$error.required && userinfo.details.telephone.$touched">-->
		    	<!--电话不能为空-->
		    <!--</div>-->
		  <!--</div>-->
		  <!--<div class="form-group row">-->
		    <!--<label for="inputPassword3" class="col-sm-2 control-label text-right"><span>*</span> 邮箱：</label>-->
		    <!--<div class="col-sm-3">-->
		      <!--<input type="email" class="form-control"  required ng-model="details.email" name="details.email"-->
		      	<!--ng-class="{'form-err':userinfo.details.email.$invalid && userinfo.details.email.$touched ,'form-success':userinfo.details.email.$valid}">-->
		    <!--</div>-->
		    <!--<div class="col-sm-2 form-iferr" ng-if="userinfo.details.email.$error.required && userinfo.details.email.$touched">-->
		    	<!--邮箱不能为空-->
		    <!--</div>-->
		  <!--</div>-->
		  <!--<div class="form-group row">-->
		    <!--<label for="inputPassword3" class="col-sm-2 control-label text-right"><span>*</span> 单位名称：</label>-->
		    <!--<div class="col-sm-3">-->
		      <!--<input type="text" class="form-control"  required name="details.company" ng-model="details.company"-->
		      	<!--ng-class="{'form-err':userinfo.details.company.$invalid && userinfo.details.company.$touched ,'form-success':userinfo.details.company.$valid}">-->
		    <!--</div>-->
		    <!--<div class="col-sm-2 form-iferr" ng-if="userinfo.details.company.$error.required && userinfo.details.company.$touched">-->
		    	<!--单位名称不能为空-->
		    <!--</div>-->
		  <!--</div>-->
		  <!--<div class="form-group row">-->
		    <!--<label for="inputPassword3" class="col-sm-2 control-label text-right"><span>*</span> 邮编：</label>-->
		    <!--<div class="col-sm-3">-->
		      <!--<input type="tel" class="form-control"  required name="details.postcodes" ng-model="details.postcodes"-->
		      	<!--ng-class="{'form-err':userinfo.details.postcodes.$invalid && userinfo.details.postcodes.$touched ,'form-success':userinfo.details.postcodes.$valid}">-->
		    <!--</div>-->
		    <!--<div class="col-sm-2 form-iferr" ng-if="userinfo.details.postcodes.$error.required && userinfo.details.postcodes.$touched">-->
		    	<!--邮编不能为空-->
		    <!--</div>-->
		  <!--</div>-->
		  <!--<div class="form-group row">-->
		    <!--<label for="inputPassword3" class="col-sm-2 control-label text-right"><span>*</span> 传真：</label>-->
		    <!--<div class="col-sm-3">-->
		      <!--<input type="tel" class="form-control"  required name="fax" ng-model="fax"-->
		      	<!--ng-class="{'form-err':userinfo.details.fax.$invalid && userinfo.details.fax.$touched ,'form-success':userinfo.details.fax.$valid}">-->
		    <!--</div>-->
		    <!--<div class="col-sm-2 form-iferr" ng-if="userinfo.details.fax.$error.required && userinfo.details.fax.$touched">-->
		    	<!--传真不能为空-->
		    <!--</div>-->
		  <!--</div>-->
		  <!--<div class="form-group row">-->
		    <!--<div class="col-sm-offset-2 col-sm-3">-->
		      <!--<button type="submit" class="btn btn-danger" ng-disabled="userinfo.username.$invalid||userinfo.address.$invalid||-->
		      	<!--userinfo.tel.$invalid ||userinfo.email.$invalid||userinfo.postcode.$invalid||userinfo.company.$invalid||-->
		      	<!--userinfo.fax.$invalid">确定修改</button>-->
		    <!--</div>-->
		  <!--</div>-->
		</form>


	</div>

</div>
