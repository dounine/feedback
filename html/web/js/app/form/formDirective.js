define(['app'],function (app) {
	app.directive('cleartab',function () {
		return{
			restrict:'A',
			link:function (scope,element,attrs) {
				$('.clearWuli').click(function () {
					$('#wuli input').each(function () {
						$('#wuli input').val(" ");
//						$("#huaxue input[type='checkbox']").attr("checked",false)
					})
					
				})
				$('.clearhuaxue').click(function () {
					$('#huaxue input').each(function () {
						$('#huaxue input').val(" ");
					})
					
				})
		
			}
		}
	})
	
	app.directive('clear',function () {
		return{
			restrict:'A',
			 link:function(scope,element,attrs){
			 	 $(element).click(function(){
			 	 	window.location.reload()
			 	 })
			}
		}
	})
})
