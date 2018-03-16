$(function(){
	
	$("#TANGRAM__PSP_3__userName").bind("focus",function(){
		$("#TANGRAM__PSP_3__userNameTip").show();
		$("#TANGRAM__PSP_3__userNameError").hide();
		$("#TANGRAM__PSP_3__userName").addClass("pass-text-input-focus");
	}).bind("blur",function(){
		// 检查是否已经存在该用户名
		$("#TANGRAM__PSP_3__userName").removeClass("pass-text-input-focus");
		$("#TANGRAM__PSP_3__userName").removeClass("pass-text-input-error");
		$("#TANGRAM__PSP_3__userNameTip").hide();
		var username = $("#TANGRAM__PSP_3__userName").val();
		$.ajax({
			type : "get", 
			url : "checkUsername.html",
			async : true,
			data:{username:username},
			dataType : "json",
			success : function(data) {
				if(data.code == 201){
					$("#TANGRAM__PSP_3__userNameError").show();
					$("#TANGRAM__PSP_3__userNameError").text("此用户名太受欢迎,请更换一个");
					$("#TANGRAM__PSP_3__userNameTip").hide();
					$("#TANGRAM__PSP_3__userName").addClass("pass-text-input-error");
				}
			}
		});
	});
	
	$("#TANGRAM__PSP_3__phone").bind("focus",function(){
		$("#TANGRAM__PSP_3__phone").addClass("pass-text-input-focus");
		$("#TANGRAM__PSP_3__phoneError").hide();
	}).bind("blur",function(){
		$("#TANGRAM__PSP_3__phone").removeClass("pass-text-input-focus");
		$("#TANGRAM__PSP_3__phone").removeClass("pass-text-input-error");
		var phoneReg=/^[1][3,4,5,7,8,6][0-9]{9}$/;  
        if (!phoneReg.test($(this).val()) && $(this).val() != "") {  
            $("#TANGRAM__PSP_3__phoneError").show();
            $("#TANGRAM__PSP_3__phoneError").text("手机号码格式不正确");
            $("#TANGRAM__PSP_3__phone").addClass("pass-text-input-error");
            return false;
        }
        var mobile = $("#TANGRAM__PSP_3__phone").val();
		if(mobile != ""){
			$.ajax({
				type : "get", 
				url : "checkMobile.html",
				async : true,
				data:{mobile:mobile},
				dataType : "json",
				success : function(data) {
					if(data.code == 201){
						$("#TANGRAM__PSP_3__phoneError").show();
			            $("#TANGRAM__PSP_3__phoneError").text("该手机号已经存在");
			            $("#TANGRAM__PSP_3__phone").addClass("pass-text-input-error");
					}
				}
			});
		}
	});
	
	$("#TANGRAM__PSP_3__password").bind("focus",function(){
		$("#TANGRAM__PSP_3__password").addClass("pass-text-input-focus");
		$("#TANGRAM__PSP_3__passwordTip").hide();
	}).bind("blur",function(){
		$("#TANGRAM__PSP_3__password").removeClass("pass-text-input-focus");
		$("#TANGRAM__PSP_3__password").removeClass("pass-text-input-error");
		// 由数字和字母组成，并且要同时含有数字和字母，且长度要在8-16位之间
		var passwordReg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$/;
		if(!passwordReg.test($(this).val()) && $(this).val() != ""){
			$("#TANGRAM__PSP_3__passwordTip").show();
			$("#TANGRAM__PSP_3__passwordTipText").text("数字、字母组合且长度要在8-16位之间");
			$("#TANGRAM__PSP_3__password").addClass("pass-text-input-error");
		}
	});
	
	$("#TANGRAM__PSP_3__verifyCodeSend").bind("click",function(){
		$("#TANGRAM__PSP_3__verifyCodeSend").addClass("pass-text-input-disabled");
		$("#TANGRAM__PSP_3__verifyCodeSend").val("58秒后重新获取激活码");
		$("#TANGRAM__PSP_3__verifyCodeSendTip").show();
		$("#TANGRAM__PSP_3__verifyCodeSend").attr("disabled","disabled");
        var u = 60;
        var t = function() {
            if (u < 2) {
            	$("#TANGRAM__PSP_3__verifyCodeSend").val("获取短信验证码").removeClass("pass-text-input-disabled");
            	$("#TANGRAM__PSP_3__verifyCodeSendTip").hide();
            	$("#TANGRAM__PSP_3__verifyCodeSend").attr("disabled","");
                return
            } else {
                u--;
                $("#TANGRAM__PSP_3__verifyCodeSend").val(u+"秒后重新获取激活码")
            }
            setTimeout(t, 1000)
        };
        t();
	});
	
	$("#TANGRAM__PSP_3__isAgree").bind("click",function(){
		if($("#TANGRAM__PSP_3__isAgree").is(':checked')){
			$("#TANGRAM__PSP_3__isAgreeError").hide();
		}
	});
	
	$("#TANGRAM__PSP_3__submit").bind("click",function(){
		$("#TANGRAM__PSP_3__submit").attr("disabled","disabled");
		var flag = true;
		var username = $("#TANGRAM__PSP_3__userName").val();
		if(username == ""){
			flag = false;
			$("#TANGRAM__PSP_3__userNameError").show();
			$("#TANGRAM__PSP_3__userNameError").text("请输入用户名");
			$("#TANGRAM__PSP_3__userNameTip").hide();
			$("#TANGRAM__PSP_3__userName").addClass("pass-text-input-error");
		}
		$.ajax({
			type : "get", 
			url : "checkUsername.html",
			async : true,
			data:{username:username},
			dataType : "json",
			success : function(data) {
				if(data.code == 201){
					$("#TANGRAM__PSP_3__userNameError").show();
					$("#TANGRAM__PSP_3__userNameError").text("此用户名太受欢迎,请更换一个");
					$("#TANGRAM__PSP_3__userNameTip").hide();
					$("#TANGRAM__PSP_3__userName").addClass("pass-text-input-error");
					flag = false;
				}
			}
		});
		if($("#TANGRAM__PSP_3__phone").val() == ""){
			$("#TANGRAM__PSP_3__phoneError").show();
            $("#TANGRAM__PSP_3__phoneError").text("请输入手机号");
            $("#TANGRAM__PSP_3__phone").addClass("pass-text-input-error");
            flag = false;
		}
		var phoneReg=/^[1][3,4,5,7,8,6][0-9]{9}$/;  
        if (!phoneReg.test($("#TANGRAM__PSP_3__phone").val()) && $("#TANGRAM__PSP_3__phone").val() != "") {  
            $("#TANGRAM__PSP_3__phoneError").show();
            $("#TANGRAM__PSP_3__phoneError").text("手机号码格式不正确");
            $("#TANGRAM__PSP_3__phone").addClass("pass-text-input-error");
            flag = false;
        }
        var mobile = $("#TANGRAM__PSP_3__phone").val();
		if(mobile != ""){
			$.ajax({
				type : "get", 
				url : "checkMobile.html",
				async : true,
				data:{mobile:mobile},
				dataType : "json",
				success : function(data) {
					if(data.code == 201){
						$("#TANGRAM__PSP_3__phoneError").show();
			            $("#TANGRAM__PSP_3__phoneError").text("该手机号已经存在");
			            $("#TANGRAM__PSP_3__phone").addClass("pass-text-input-error");
			            flag = false;
					}
				}
			});
		}
		if($("#TANGRAM__PSP_3__password").val() == ""){
			$("#TANGRAM__PSP_3__passwordTip").show();
			$("#TANGRAM__PSP_3__passwordTipText").text("请输入密码");
			$("#TANGRAM__PSP_3__password").addClass("pass-text-input-error");
			flag = false;
		}
		var passwordReg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$/;
		if(!passwordReg.test($("#TANGRAM__PSP_3__password").val()) && $("#TANGRAM__PSP_3__password").val() != ""){
			$("#TANGRAM__PSP_3__passwordTip").show();
			$("#TANGRAM__PSP_3__passwordTipText").text("数字、字母组合且长度要在8-16位之间");
			$("#TANGRAM__PSP_3__password").addClass("pass-text-input-error");
			flag = false;
		}
		if($("#TANGRAM__PSP_3__verifyCode").val() == ""){
			$("#TANGRAM__PSP_3__verifyCodeError").show();
			$("#TANGRAM__PSP_3__verifyCodeError").text("请输入验证码");
			$("#TANGRAM__PSP_3__verifyCode").addClass("pass-text-input-error");
			flag = false;
		}
		if(!$("#TANGRAM__PSP_3__isAgree").is(':checked')){
			$("#TANGRAM__PSP_3__isAgreeError").show();
			$("#TANGRAM__PSP_3__isAgreeError").text("请勾选“阅读并接受百度用户协议”");
			flag = false;
		}
		if(flag){
			$.ajax({
				type : "get", 
				url : "subRegister.html",
				async : true,
				data:{username:$("#TANGRAM__PSP_3__userName").val() , password:$("#TANGRAM__PSP_3__password").val() , verifyCode:$("#TANGRAM__PSP_3__verifyCode").val(),
					mobile:$("#TANGRAM__PSP_3__phone").val()},
				dataType : "json",
				success : function(data) {
					if(data.code == 201){
						$("#TANGRAM__PSP_3__userNameError").show();
						$("#TANGRAM__PSP_3__userNameError").text("此用户名太受欢迎,请更换一个");
						$("#TANGRAM__PSP_3__userNameTip").hide();
						$("#TANGRAM__PSP_3__userName").addClass("pass-text-input-error");
					}else if(data.code == 202){
						$("#TANGRAM__PSP_3__phoneError").show();
			            $("#TANGRAM__PSP_3__phoneError").text("该手机号已经存在");
			            $("#TANGRAM__PSP_3__phone").addClass("pass-text-input-error");
					}else if(data.code == 200){
						window.location.href = "/";
					}
				}
			});
		}
		$("#TANGRAM__PSP_3__submit").removeAttr("disabled");
		return false;
	});
})