var Feedback = (function () {

	function Feedback() {

	}
	Feedback.prototype.init_model = function(){
			//$feedback.init_customer_info()
		 //arguments[0]

	}

	Feedback.prototype.init_customer_info = function(){
		$.ajax({
			type:'get',
			url:$feedback.userUrl+"current_user",
			success:function(){

			}
		});
	}

	Feedback.prototype.save = function(){
		$.ajax({
			type: "POST",
			url:$feedback.feedbackUrl+"save",
			data:$feedback.addFrom.serialize(),//
			async: false,
			error: function(request) {
				alert("Connection error");
			},
			success: function(data) {
				alert("success");
			}
		});


	}


	return Feedback;
})();
