$(function(){
	
	$("#TANGRAM__PSP_3__userName").bind("focus",function(){
		$("#TANGRAM__PSP_3__userName").addClass("pass-text-input-focus");
	}).bind("keyup",function(){
		if($("#TANGRAM__PSP_3__userName").val() != ""){
			$("#TANGRAM__PSP_3__userName_clearbtn").show();
		}else{
			$("#TANGRAM__PSP_3__userName_clearbtn").hide();
		}
	}).bind("blur",function(){
		$("#TANGRAM__PSP_3__userName").removeClass("pass-text-input-focus");
	});
	
	$("#TANGRAM__PSP_3__userName_clearbtn").bind("click",function(){
		$("#TANGRAM__PSP_3__userName").val("");
		$("#TANGRAM__PSP_3__userName_clearbtn").hide();
		$("#TANGRAM__PSP_3__userName").focus();
	});
	
	$("#TANGRAM__PSP_3__verifyCodeChange").bind("click",function(){
		$("#TANGRAM__PSP_3__verifyCodeImg").attr("src",mainHttp + "admin/captcha.jpg?t=" + $.now());
	});
	
	$("#TANGRAM__PSP_3__password").bind("focus",function(){
		$("#TANGRAM__PSP_3__password").addClass("pass-text-input-focus");
	}).bind("blur",function(){
		$("#TANGRAM__PSP_3__password").removeClass("pass-text-input-focus");
	}).bind("keyup",function(){
		if($("#TANGRAM__PSP_3__password").val() != ""){
			$("#TANGRAM__PSP_3__password_clearbtn").show();
		}else{
			$("#TANGRAM__PSP_3__password_clearbtn").hide();
		}
	});
	
	$("#TANGRAM__PSP_3__password_clearbtn").bind("click",function(){
		$("#TANGRAM__PSP_3__password").val("");
		$("#TANGRAM__PSP_3__password_clearbtn").hide();
		$("#TANGRAM__PSP_3__password").focus();
	});
	
	$("#TANGRAM__PSP_3__submit").bind("click",function(){
		if($("#TANGRAM__PSP_3__userName").val() == ""){
			$("#TANGRAM__PSP_3__userName").focus();
			$("#TANGRAM__PSP_3__error").text("请您输入手机/邮箱/用户名");
			return false;
		}
		if($("#TANGRAM__PSP_3__password").val() == ""){
			$("#TANGRAM__PSP_3__password").focus();
			$("#TANGRAM__PSP_3__error").text("请您输入密码");
			return false;
		}
		return true;
	});
})