define(['app'],function (app){
	app.controller('tijiao',function ($scope) {
		$scope.tiji = {
			'title': "确定提交信息",
			'yes' : "确定",
			'no' : "取消，继续编辑"
		}
		$scope.queding = function () {
			$scope.tiji = {
			'title': "提交成功！",
			'yes' : "继续添加信息",
			'no' : "查看状态",
			};
			$('#tijiBtn').click(function () {
					window.location.href="index.html"
				
				count ++
			});
		}
	})

})
