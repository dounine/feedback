var Router = require('koa-router');
var app = require('koa')();
var sendfile = require('koa-sendfile');
var path = require('path');
module.exports = function(){

	var router = new Router();

	router.get('/login',function *(next) {
		//this.body = path.resolve('public/ng/modules/login/app/tpls')+'/index.html';
		var stats = yield sendfile(this, path.resolve('public/ng/modules/login/app/tpls')+'/index.html');
  		if (!this.status) this.throw(404)
	});

	return router;
};