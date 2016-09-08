var gulp = require('gulp');
var uglify = require('gulp-uglify');
var concat = require('gulp-concat');
var sass = require('gulp-sass');
var bower = require('gulp-bower');
var minifycss = require('gulp-minify-css');
var minhtml = require('gulp-htmlmin');
var rename = require('gulp-rename');
var imagemin = require('gulp-imagemin');
var ngConstant = require('gulp-ng-constant');
var minimist = require('minimist');
var gutil = require('gulp-util');
var del = require('del');

var paths = {
	jss: ['./app/js/**/*.js'],
	imgs: ['./app/img/**/*.*'],
	htmls: ['./app/tpls/**/*.html'],
	sasss: ['./app/**/*.scss']
}

gulp.task('clean', function() {
	del(['dist/*'])
});

gulp.task('compress', ['compress-js', 'compress-html', 'compress-img'], function() {

});
gulp.task('compress-js', function() {
	return gulp.src(paths.jss)
		.pipe(rename(function(path){
			path.extname=".min.js"
		}))
		.pipe(uglify())
		.pipe(gulp.dest('dist/js/'));
});
gulp.task('compress-img', function() {
	return gulp.src(paths.imgs)
		.pipe(imagemin({
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
		.pipe(minhtml(options))
		.pipe(gulp.dest('dist/tpls/'));
})
gulp.task('build-sass', function() { //先删除build
	return gulp.src(paths.sasss)
		.pipe(sass({
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
gulp.task('watch', ['sass:watch'], function() {

});

gulp.task('copy-bower', ['clean'], function() {
	return gulp.src(['bower_components/**/*.min.js',
			'bower_components/**/*.min.css',
			'bower_components/**/require.js'
		])
		.pipe(gulp.dest('dist/lib/'));
});

//默认development环境
var knowOptions = {
	string: 'env',
	default: {
		env: process.env.NODE_ENV || 'development'
	}
};

var options = minimist(process.argv.slice(2), knowOptions);

//生成filename文件，存入string内容
function string_src(filename, string) {
	var src = require('stream').Readable({
		objectMode: true
	});
	src._read = function() {
		this.push(new gutil.File({
			cwd: "",
			base: "",
			path: filename,
			contents: new Buffer(string)
		}));
		this.push(null);
	}
	return src;
}

gulp.task('constants', function() {
	//读入config.json文件
	var myConfig = require('../../../../config.json');
	//取出对应的配置信息
	var envConfig = myConfig[options.env];
	//生成config.[js,json]文件
	var json = JSON.stringify(envConfig);
	string_src("./config.js", 'appconfig = ' + json)
		.pipe(gulp.dest('dist/'));
	return string_src("./config.json", json)
		.pipe(gulp.dest('dist/'));
});

gulp.task('config-koa', ['constants'], function() { //angularjs 配置
	var myConfig = require('./dist/config.json');
	var cons = "module.exports = function(){\n";
	for(var name in myConfig) {
		cons += ("this." + name + "=\"" + myConfig[name]+"\";");
	}
	cons += "return this;}"
	string_src("./config-koa.js", cons)
		.pipe(gulp.dest('dist/js'));
});

gulp.task('config', ['config-koa'], function() { //angularjs 配置
	var myConfig = require('./dist/config.json');
	var cons = "define(['angular'], function(angular) {\n";
	cons += "\"use strict\";\n";
	cons += "return angular.module(\"app.login.config\",[])";
	cons += ".constant(\"config\",{"
	for(var name in myConfig) {
		cons += ("\"" + name + "\":");
		cons += ("\"" + myConfig[name] + "\",");
	}
	cons += "})});"
	string_src("./config.js", cons)
		.pipe(gulp.dest('dist/js'));
});

gulp.task('default', function(appconfig) {
	gulp.run('config', 'copy-bower', 'build-sass', 'compress');
});