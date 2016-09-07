var Router = require('koa-router');


module.exports = function(){

	var router = new Router();

	router.get('/user',function *(next) {
		this.body = 'Hello World!';
	});

	return router;
};