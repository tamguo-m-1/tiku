$(function(){

	$("#pass-input-mobileVcode").bind("focus",function(){
		$("#pass-input-mobileVcode").addClass("pass-input-focus");
	}).bind("blur",function(){
		$("#pass-input-mobileVcode").removeClass("pass-input-focus");
	});
	
	$("#pass-button-new1").bind("click",function(){
		$("#forgot-mobileVcode-success").text("短信已经发送");
		$("#forgot-mobileVcode-success").show();
		$("#pass-button-new1").css("disabled","disabled");
        var u = 60;
        var t = function() {
            if (u < 2) {
            	$("#pass-button-new1").text("重新发送");
            	$("#pass-button-new1").css("disabled","");
                return
            } else {
                u--;
                $("#pass-button-new1").text("重新发送("+u+")");
            }
            setTimeout(t, 1000)
        };
        t();
	});
	
})