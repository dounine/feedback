regApp.controller('register',function ($scope) {
	 $scope.userdata ={};

        //表单提交
        $scope.submitForm = function () {
            console.log($scope.userdata)
        };
        $scope.reg1= false
        
})

regApp.controller('reg1',function ($scope) {
//	$scope.disabled = true;
	$scope.next = function () {
		console.log($('.agree').Attr()) 
	}
	
})

regApp.directive('yanz',function () {
	return{
		restrict: 'EA',
		replace: true,
		link: function(scope, el, attrs, controller){
			el.on('click',function () {
				
				
				
//				$('.reg3-con input').each(function () {
//					if ($('.reg3-con input').hasClass('form-err')) {
//						console.log(11)
//				}else{
//					console.log(22)
//				}
//				})
//				$('.reg1.btn').click(function () {
					alert(1)
//				})
				
				
			})
		}
	}
})