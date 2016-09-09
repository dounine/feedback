var gulp = require('gulp');
var gulpSequence = require('gulp-sequence').use(gulp);//顺序执行或并行
var gutil = require('gulp-util');
var minimist = require('minimist');
var cache = require('gulp-cached');//可记录修改过的文件,利器
var plugins = require('gulp-load-plugins')();
var browserSync = require("browser-sync").create();
var rev = require('gulp-rev');
var revCollector = require('gulp-rev-collector');//html内容版本号替换
var config = require('../../../plugins/read-config.js');
var path = require('path');
var cssmin = require('gulp-minify-css');

function isPro() {
    if (config(true) === true) {
        return true;
    }
    return false;
}

gulp.task('clean', function () {
    return gulp.src('app/**/rev/')
        .pipe(plugins.clean());
});


function string_src(filename, string) {
    var src = require('stream').Readable({
        objectMode: true
    });
    src._read = function () {
        var file = new gutil.File({
            cwd: process.cwd(),
            base: "./",
            path: filename,
            contents: new Buffer(string)
        });
        this.emit('data', file);
        this.emit('end');
    }
    return src;
}

gulp.task('copy-css', function () {
    return gulp.src(['./app/css/**/*.css'])
        .pipe(gulp.dest('app/css/rev/'));
});
gulp.task('copy-sass', function () {
    return gulp.src(['./app/sass/**/*.+(sass|scss|less)'])
        .pipe(gulp.dest('app/sass/rev/'));
});
gulp.task('copy-js', function () {
    return gulp.src(['./app/js/**/*.js'])
        .pipe(gulp.dest('app/js/rev/'));
});
gulp.task('copy-html', function () {
    return gulp.src(['./app/tpls/**/*.html'])
        .pipe(gulp.dest('app/tpls/rev/'));
});
gulp.task('copy-module',['copy-css','copy-sass','copy-js','copy-html']);//复制自己的所有文件到rev目录中,用于生产环境测试及开发

gulp.task('copy-lib', function () {//复制第三方库文件
    return gulp.src(['bower_components/**/*.min.+(js|css)',
        'bower_components/**/require.js'
    ]).pipe(gulp.dest('app/lib/'));
});
gulp.task('build-css', function () {//编译sass|scss文件
    return gulp.src('./app/sass/rev/**/*.+(scss|sass|less)')
        .pipe(plugins.sass({
            outputStyle: 'compressed'
        }))
        .pipe(gulp.dest('app/sass/rev/'));
});

gulp.task('create-config-ng', function () { //生成angularjs 配置
    var myConfig = config();
    var cons = "define(['app'], function(app) {\n";
    cons += "\"use strict\";\n";
    cons += "return app";
    cons += ".constant(\"config\",{"
    for (var name in myConfig) {
        cons += ("\"" + name + "\":");
        cons += ("\"" + myConfig[name] + "\",");
    }
    cons += ("\"moduleName\":");
    cons += ("\"" + path.basename(__dirname) + "\",");
    cons += "})});"
    return string_src("./__config-ng.js", cons)
        .pipe(gulp.dest('./app/js/rev/'));
});

gulp.task('compress-js', function () {//压缩js
    return gulp.src('./app/js/rev/**/*.js')
        .pipe(plugins.uglify())
        .pipe(gulp.dest('app/js/rev/'));
});
gulp.task('compress-css', function () {//压缩css
    return gulp.src('./app/css/rev/**/*.css')
        .pipe(cssmin())
        .pipe(gulp.dest('app/css/rev/'));
});
gulp.task('compress-sass-css', function () {//压缩sass中的css
    return gulp.src('./app/sass/rev/**/*.css')
        .pipe(cssmin())
        .pipe(gulp.dest('app/sass/rev/'));
});
gulp.task('compress-img', function () {//压缩img
    return gulp.src('./app/img/rev/**/*.+(png|gif|jpg)')
        .pipe(plugins.imagemin({
            optimizationLevel: 5
        }))
        .pipe(gulp.dest('app/img/rev/'));
});
gulp.task('compress-html', function () {//压缩html
    var options = {
        removeComments: true,
        collapseWhitespace: true,
        collapseBooleanAttributes: true,
        removeEmptyAttributes: true,
        removeScriptTypeAttributes: true,
        removeStyleLinkTypeAttributes: true,
        minifyJS: true,
        minifyCSS: true
    };
    return gulp.src('./app/tpls/rev/**/*.html')
        .pipe(plugins.htmlmin(options))
        .pipe(gulp.dest('app/tpls/rev/'));
});
gulp.task('compress-all', ['compress-sass-css','compress-css', 'compress-js', 'compress-html', 'compress-img']);

gulp.task('version-js', function () {//生成js版本号
    return gulp.src('./app/js/rev/**/*.js')
        .pipe(rev())
        .pipe(gulp.dest('app/js/rev/'))
        .pipe(rev.manifest())
        .pipe(gulp.dest('app/js/rev/'));
});
gulp.task('version-css', function () {//生成css版本号
    return gulp.src('app/css/rev/**/*.css')
        .pipe(rev())
        .pipe(gulp.dest('app/css/rev/'))
        .pipe(rev.manifest())
        .pipe(gulp.dest('app/css/rev/'));
});
gulp.task('version-sass-css', function () {//生成sass中的css版本号
    return gulp.src('app/sass/rev/**/*.css')
        .pipe(rev())
        .pipe(gulp.dest('app/sass/rev/'))
        .pipe(rev.manifest())
        .pipe(gulp.dest('app/sass/rev/'));
});
gulp.task('version-html', function () {//生成html版本号
    return gulp.src(['app/**/rev/rev-manifest.json', 'app/tpls/rev/**/*.html'])
        .pipe(revCollector({
            replaceReved: true
        }))
        .pipe(gulp.dest('app/tpls/rev/'))
        .pipe(plugins.if('rev-manifest.json',plugins.clean()));
});
gulp.task('version-all',gulpSequence(['version-js','version-sass-css','version-css'],'version-html'));

gulp.task('clean-rev', function () {//清除临时文件
    gulp.src(['app/css/rev/**/*.css', '!app/css/rev/**/*-*.css'])
        .pipe(plugins.clean());
    //gulp.src(['app/js/rev/**/*.js', '!app/js/rev/**/*-*.js']).pipe(plugins.clean());//TODO 删除后存在main.js获取不到未添加版本号的js文件
    gulp.src(['app/sass/rev/**/*.+(sass|scss|less|css)', '!app/sass/rev/**/*-*.css'])
        .pipe(plugins.clean());
});
gulp.task('bs-start',function () {
    return browserSync.init({
        proxy: config()['lurl']
    });
});
gulp.task('bs-watch',function () {
    return gulp.watch('**/*.js,**/*.css,**/*.html').on('change', browserSync.reload);
});
gulp.task('bs',gulpSequence(isPro()?'pro':'dev','bs-start','bs-watch'));//启动浏览器并自动刷新，用于开发模式
gulp.task('pro', gulpSequence('dev','compress-all', 'version-all','clean-rev'));//生产环境打包配置
gulp.task('dev', gulpSequence('clean', ['copy-module', 'copy-lib'], ['create-config-ng', 'build-css'],'create-config-ng'));//开发环境打包配置
gulp.task('default',[isPro()?'pro':'dev']);//默认会根据/config.json文件进行环境的打包