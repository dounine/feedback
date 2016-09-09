var Router = require('koa-router');
var app = require('koa')();
var sendfile = require('koa-sendfile');
var path = require('path');
var loginSer = require(path.resolve('koa/services/'+path.basename(__dirname)+'/index.js'));
var config = require('../../../plugins/read-config.js');

module.exports = function(config){
	var router = new Router();
	router.get('/login',function *() {
		var stats = yield (sendfile(this, path.resolve('ng/modules/'+path.basename(__dirname)+'/app/tpls/rev')+'/index.html'));
  		if (!this.status){
  			this.throw(404);
		}
	}).post('/login',function *() {
		var user = this.request.body;
		var $self = this;
		yield (loginSer().sso(user)
			.then(function (parsedBody) {
				var responseText =  JSON.parse(parsedBody);
				if(responseText['errno']==0){
					var token = JSON.parse(responseText['data'])['token'];
					$self.redirect('/admin');
					$self.body = responseText;
					$self.cookies.set('token',token);
					$self.status = 301;
				}else{
					$self.status = 403;
				}

			}).catch(function (err) {
				console.info('登录出错啦');
		}));

	});

	return router;
};