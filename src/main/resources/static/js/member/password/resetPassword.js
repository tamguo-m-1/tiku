$(function(){

	$("#password").bind("focus",function(){
		$("#password").addClass("pass-input-focus");
		$("#password").removeClass("pass-input-error");
		$("#pass_input_msg_Pwd").text("");
	}).bind("blur",function(){
		$("#password").removeClass("pass-input-focus");
	});
	
	$("#verifypwd").bind("focus",function(){
		$("#verifypwd").addClass("pass-input-focus");
		$("#verifypwd").removeClass("pass-input-error");
		$("#repassword_msg").text("");
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
		if($("#password").val() != $("#verifypwd").val()){
			$("#verifypwd").addClass("pass-input-error");
			$("#verifypwd").val("");
			$("#repassword_msg").text("确认新密码和新密码不同");
			flag = false;
		}
		return flag;
	});
	
})