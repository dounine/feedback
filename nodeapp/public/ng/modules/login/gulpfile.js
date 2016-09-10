var gulp = require('gulp');
var gutil = require('gulp-util');
var minimist = require('minimist');
var del = require('del');
var cache = require('gulp-cached');//可记录修改过的文件,利器
var plugins = require('gulp-load-plugins')();
var browserSync = require("browser-sync").create();
var rev = require('gulp-rev');
var revCollector = require('gulp-rev-collector');//html内容版本号替换
var config = require('../../../gulp-plugins/read-config.js');
var gulpSequence = require('gulp-sequence').use(gulp);//顺序执行或并行

var paths = {
	jss: ['./app/js/**/*.js'],
	imgs: ['./app/img/**/*.*'],
	htmls: ['./app/tpls/**/*.html'],
	sasss: ['./app/**/*.scss']
}

gulp.task('clean', function() {
	del(['dist'])
});


gulp.task('compress-js', function() {
	return gulp.src(paths.jss)
		.pipe(plugins.rename(function(path){
			path.extname=".min.js"
		}))
		.pipe(plugins.uglify())
		.pipe(gulp.dest('dist/js/'));
});
gulp.task('compress-img', function() {
	return gulp.src(paths.imgs)
		.pipe(plugins.imagemin({
			optimizationLevel: 5
		}))
		.pipe(gulp.dest('dist/img/'));
});
gulp.task('compress-html', function() {
	var options = {
		removeComments: true, //清除HTML注释
		collapseWhitespace: true, //压缩HTML
		collapseBooleanAttributes: true, //省略布尔属性的值 <input checked="true"/> ==> <input />
		removeEmptyAttributes: true, //删除所有空格作属性值 <input id="" /> ==> <input />
		removeScriptTypeAttributes: true, //删除<script>的type="text/javascript"
		removeStyleLinkTypeAttributes: true, //删除<style>和<link>的type="text/css"
		minifyJS: true, //压缩页面JS
		minifyCSS: true //压缩页面CSS
	};
	return gulp.src(paths.htmls)
		.pipe(plugins.htmlmin(options))
		.pipe(gulp.dest('dist/tpls/'));
});
gulp.task('compress', ['compress-js', 'compress-html', 'compress-img']);

gulp.task('build-sass', function() {
	return gulp.src(paths.sasss)
		.pipe(plugins.sass({
			outputStyle: 'compressed'
		})) //压缩最小
		.pipe(gulp.dest('dist/'));
});

gulp.task('sass:watch', function() { //scss文件变化侦听
	return gulp.watch(paths.sasss, ['build-sass']);
});
gulp.task('config:watch', function() { //scss文件变化侦听
	return gulp.watch('../../../../config.json', ['default']);
});
gulp.task('watch', ['sass:watch','config:watch']);

gulp.task('copy-bower', function() {
	return gulp.src(['bower_components/**/*.min.+(js|css)',
			'bower_components/**/require.js'
		]).pipe(gulp.dest('dist/lib/'));
});

//默认development环境
var knowOptions = {
	string: 'env',
	default: {
		env: process.env.NODE_ENV || 'dev'
	}
};
var options = minimist(process.argv.slice(2), knowOptions);

//生成filename文件，存入string内容
function string_src(filename, string) {
	var src = require('stream').Readable({
		objectMode: true
	});
	src._read = function() {
		var file = new gutil.File({
			cwd: process.cwd(),
			base: "./",
			path: filename,
			contents: new Buffer(string)
		});
		this.emit('data',file);
		this.emit('end');
	}
	return src;
}
gulp.task('cons-js', function() {
	var envConfig = config(options.env);
	var json = JSON.stringify(envConfig);
	string_src("./config.js", 'appconfig = ' + json)
		.pipe(gulp.dest('dist/'));
});
gulp.task('constants',['cons-js']);


gulp.task('config-koa', function() { //angularjs 配置
	var myConfig = config(options.env);
	var cons = "module.exports = function(){\n";
	for(var name in myConfig) {
		cons += ("this." + name + "=\"" + myConfig[name]+"\";");
	}
	cons += ("this.moduleName=");
	cons += ("\"login\";");
	cons += "return this;}"
	return string_src("./config-koa.js", cons)
		.pipe(gulp.dest('dist/js'));
});

gulp.task('config', ['constants','config-koa'], function() { //angularjs 配置
	var myConfig = config(options.env);
	var cons = "define(['app'], function(app) {\n";
	cons += "\"use strict\";\n";
	cons += "return app";
	cons += ".constant(\"config\",{"
	for(var name in myConfig) {
		cons += ("\"" + name + "\":");
		cons += ("\"" + myConfig[name] + "\",");
	}
	cons += ("\"moduleName\":");
	cons += ("\"login\",");
	cons += "})});"
	return string_src("./config.js", cons)
		.pipe(gulp.dest('dist/js'));
});
gulp.task('browser-sync',['rev'], function() {
	var myConfig = config(options.env);
	browserSync.init({
		proxy: myConfig['lurl']
	});
	return gulp.watch('**/*.js,**/*.css,**/*.html').on('change',browserSync.reload);
});
gulp.task('rev-css', function () {
	return gulp.src('dist/css/*.css')
		.pipe(rev())
		.pipe(gulp.dest('dist/css'))
		.pipe(rev.manifest())
		.pipe(gulp.dest('dist/css'));
});
gulp.task('rev-js', function () {
	return gulp.src('./dist/js/*.min.js')
		.pipe(rev())
		.pipe(gulp.dest('dist/js'))
		.pipe(rev.manifest())
		.pipe(gulp.dest('dist/js'));
});
gulp.task('rev-html',['rev-css','rev-js'], function () {
	return gulp.src(['dist/**/*.json', 'dist/tpls/**/*.html'])
		.pipe(revCollector({
			replaceReved: true
		}))
		.pipe(gulp.dest('dist/tpls/'));
});
gulp.task('rev',gulpSequence('default',['rev-html']));
gulp.task('default',gulpSequence('clean','copy-bower',['config', 'build-sass', 'compress']));