$(function () {
		var count=0;
	$('#tijiBtn').click(function () {
		if(count>=1){
			window.location.href="form.html"
		}
		
		count ++
	});
	$('.clearWuli').click(function () {
		$('#huaxue input').each(function () {
//			$('#huaxue input').val(" ");
			
			
		})
	})
	 //重置按钮事件  
        $("#resetBtn").on("click",function(){  
        	window.location.href="form.html"
         })
})
