regApp.controller('register',function ($scope) {
	 $scope.userdata ={};

        //表单提交
        $scope.submitForm = function () {
            console.log($scope.userdata)
        };
})

regApp.directive('yanz',function () {
	return{
		restrict: 'EA',
		replace: true,
		link: function(scope, el, attrs, controller){
			el.on('click',function () {
				$('.reg3-con input').each(function () {
					if ($('.reg3-con input').hasClass('form-err')) {
						console.log(11)
				}else{
					console.log(22)
				}
				})
				
				
			})
		}
	}
})