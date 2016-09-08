var Router = require('koa-router');
var app = require('koa')();
var sendfile = require('koa-sendfile');
var path = require('path');
var loginSer = require(path.resolve('public/koa/services/login/login.js'));

module.exports = function(env){

	var router = new Router();
	router.get('/login',function *() {
		var stats = yield sendfile(this, path.resolve('public/ng/modules/login/'+env+'/tpls')+'/index.html');
  		if (!this.status) this.throw(404)
	}).post('/login',function *() {
		var user = this.request.body;
		var $self = this;
		yield loginSer().sso(user)
			.then(function (parsedBody) {
				var responseText =  JSON.parse(parsedBody);
				if(responseText['errno']==0){
					var token = JSON.parse(responseText['data'])['token'];
					$self.body = responseText;
					$self.cookies.set('token',token);
					$self.status = 200;
				}else{
					$self.status = 403;
				}

			}).catch(function (err) {
		});

	});

	return router;
};