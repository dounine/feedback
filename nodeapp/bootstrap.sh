#!/bin/bash
dir=$(dirname $0;pwd)
$dir/public/ng/modules/login/gulp.sh
nodemon
