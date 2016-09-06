var app = require('koa')();//koa web应用
var path = require('path');//路径
var router = require("koa-router")();//路由中间件
var serve = require('koa-static-server');//
const routersPath = '/public/koa/modules/routers/';


//============路由跳转=============
router.get('/',function *(next){//根路由
	this.redirect('/login');//重写向到登录页面
	this.status = 301;
});

app.use(require(path.join(__dirname,routersPath,'user/user.js'))().routes());//用户路由
app.use(require(path.join(__dirname,routersPath,'login/login.js'))().routes());//登录路由

//============路由跳转=============


//============静态文件资源===========
app.use(serve({rootDir: 'public/ng/modules'}));
//============静态文件资源===========


app.use(router.routes());
app.listen(8888,function () {
	console.log('koa server listening on port ' + 8888);
});