define(['app'],function (app) {
	'use strict';
		app.run(['$rootScope',function($rootScope){
        $rootScope.nativeId=getCurrentNativeId();
        function getCurrentNativeId(){
                var str = "#/index";
                var href=window.location.href;
                var index = href.indexOf("#/");
                if(index != -1){
                        str = href.substring(index,href.length);
                        console.log(str)
                }
                return str;
               
        }
}])
.directive('native',['$rootScope',function($rootScope,$cookies){
        return{
            restrict:'A',
            link:function(scope,element,attrs){
                $(element).click(function(){
                        scope.$apply(function(){
                                $rootScope.nativeId = attrs.href;
//                          console.log($rootScope.nativeId)
                        });
				if($(this).has(".onMenu")){
						$(this).addClass("onMenu").parents("li").siblings("li").find("a").removeClass("onMenu");
					}

                });
               
            }
        }
}]);
})
