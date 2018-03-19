$(function(){

	$("#password").bind("focus",function(){
		$("#password").addClass("pass-input-focus");
	}).bind("blur",function(){
		$("#password").removeClass("pass-input-focus");
	});
	
	$("#verifypwd").bind("focus",function(){
		$("#verifypwd").addClass("pass-input-focus");
	}).bind("blur",function(){
		$("#verifypwd").removeClass("pass-input-focus");
	});
	
	$("#submit-resetpwd").bind("click",function(){
		var flag = true;
		if($("#password").val() == ""){
			$("#password").addClass("pass-input-error");
			$("#pass_input_msg_Pwd").text("请您填写密码");
			flag = false;
		}
		if($("#verifypwd").val() == ""){
			$("#verifypwd").addClass("pass-input-error");
			$("#repassword_msg").text("请您输入确认密码");
			flag = false;
		}
		if(!flag){
			return false;
		}
	});
	
})