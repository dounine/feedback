var Router = require('koa-router');
var app = require('koa')();
var sendfile = require('koa-sendfile');
var path = require('path');
module.exports = function(env){

	var router = new Router();

	router.get('/login',function *(next) {
		var stats = yield sendfile(this, path.resolve('public/ng/modules/login/'+env+'/tpls')+'/index.html');
  		if (!this.status) this.throw(404)
	});

	return router;
};