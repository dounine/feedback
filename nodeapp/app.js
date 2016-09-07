var app = require('koa')();//koa web应用
var path = require('path');//路径
var router = require("koa-router")();//路由中间件
var serve = require('koa-static-server');
var session = require('koa-session');//cookie
var koaBody = require('koa-body');
var json = require('koa-json');
const routersPath = '/public/koa/routers/';
const env = 'app';


//============路由跳转=============
app.keys = ['some secret hurr'];
app.use(session(app));
app.use(koaBody());//必需要路由用之前使用,不然获取不到表单
router.get('/',function *(next){//根路由
	this.redirect('/login');//重写向到登录页面
	this.status = 301;
});
app.use(require(path.join(__dirname,routersPath,'login/login.js'))(env).routes());//登录路由

//============路由跳转=============


//============静态文件资源===========
app.use(serve({rootDir: 'public/ng/modules'}));
//============静态文件资源===========


app.use(router.routes());
app.listen(8888,function () {
	console.log('koa server listening on port ' + 8888);
});