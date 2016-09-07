define(['app'],function(app){

	 		app.controller('menu',function ($scope) {
		$scope.addmenu = false;
		$scope.addcla = false;
		$scope.flex1 = function () {
            $scope.addmenu = !$scope.addmenu;
            $scope.addcla =  !$scope.addcla
        }
		
		$scope.information = false;
		$scope.infocla = false;
		$scope.flex2 = function () {
			$scope.information=!$scope.information;
			$scope.infocla = !$scope.infocla
		}
	})
	 
})
