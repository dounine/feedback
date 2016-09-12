var app = require('koa')();//koa web应用
var path = require('path');//路径
var router = require("koa-router")();//路由中间件
var serve = require('koa-static-server');
var session = require('koa-session');//cookie
var koaBody = require('koa-body');
var json = require('koa-json');
var cors = require('koa-cors');
const routersPath = '/koa/routers/';
var config = require(path.resolve('plugins/read-config.js'))();//读取开发与生产环境配置文件

//============路由跳转=============
app.use(cors());//跨域请求,用于与browser-sync调试
app.keys = ['feedback'];//session加密值
app.use(session(app));//使用cookie
app.use(koaBody());//必需要路由用之前使用,不然获取不到表单
router.get('/',function *(next){//根路由
	this.redirect('/login');//重写向到登录页面
	this.status = 301;
});
app.use(require(path.join(__dirname,routersPath,'login/index.js'))(config).routes());//登录路由
app.use(require(path.join(__dirname,routersPath,'register/index.js'))(config).routes());//注册
app.use(require(path.join(__dirname,routersPath,'customer/index.js'))(config).routes());//委托书
app.use(require(path.join(__dirname,routersPath,'error/index.js'))(config).routes());//委托书
//app.use(require(path.join(__dirname,routersPath,'admin/index.js'))(config).routes());//后台路由

//============路由跳转=============


//============静态文件资源===========
app.use(serve({rootDir: 'ng/modules'}));
//============静态文件资源===========


app.use(router.routes());
app.listen(8888,function () {
	console.log('koa server listening on port ' + 8888);
});