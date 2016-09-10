var Router = require('koa-router');
var app = require('koa')();
var sendfile = require('koa-sendfile');
var path = require('path');
var moduleName = path.basename(__dirname);
var loginSer = require(path.resolve('public/koa/services/'+moduleName+'/index.js'));

module.exports = function(config){
	var router = new Router();
	router.get('/'+moduleName,function *() {
		var stats = yield (sendfile(this, path.resolve('public/ng/modules/'+moduleName+'/'+config['rootFold']+'/tpls')+'/index.html'));
  		if (!this.status) this.throw(404)
	}).post('/admin',function *() {
		var user = this.request.body;
		var $self = this;
		yield (loginSer().sso(user)
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
		}));

	});

	return router;
};