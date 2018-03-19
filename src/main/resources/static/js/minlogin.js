$(function(){
	// 登录窗口拖动
	$.drag({
		target:'#TANGRAM__PSP_19__title',
		root:'#passport-login-pop',
		lock:true
	});
	
	$(".header .login-container").bind("click",function(event){
		if($(this).find("span").length == 1){
			$(".mask").css("display","block");
			$("#passport-login-pop").css("display","block");
		}
	});
	
	$("#TANGRAM__PSP_19__closeBtn").bind("click",function(event){
		$(".mask").css("display","none");
		$("#passport-login-pop").css("display","none");
	});
	
	$(".pass-login-pop-content .pass-text-input").bind("focus",function(event){
		$(this).addClass("pass-text-input-hover");
	}).bind("blur",function(event){
		$(this).removeClass("pass-text-input-hover");
		$(this).removeClass("pass-text-input-error");
	}).bind("keyup",function(event){
		if($(this).val() != ""){
			$("#"+$(this).attr("id")+"_clearbtn").css("display","block").css("visibility","visible");
		}
	});
	
	$(".pass-login-pop-content .pass-clearbtn").bind("click",function(event){
		$(this).css("display","none").css("visibility","hidden");
		$(this).prev().val("");
		$(this).prev().trigger("focus");
	})
	
	$("#TANGRAM__PSP_25__submit").bind("click",function(event){
		if($("#TANGRAM__PSP_25__userName").val() == ""){
			$("#TANGRAM__PSP_25__userName").focus();
			$("#TANGRAM__PSP_25__error").html('请输入用户名/手机号/邮箱');
			return false;
		}
		if($("#TANGRAM__PSP_25__password").val() == ""){
			$("#TANGRAM__PSP_25__password").focus();
			$("#TANGRAM__PSP_25__error").html('请输入密码');
			return false;
		}
		var username = $("#TANGRAM__PSP_25__userName").val();
		var password = $("#TANGRAM__PSP_25__password").val();
		$.ajax({
			type : "get", 
			url : mainHttp + "login.html",
			async : true,
			data:{username:username,password:password,captcha:$("#TANGRAM__PSP_25__verifyCode").val()},
			dataType : "json",
			success : function(data) {// 返回数据根据结果进行相应的处理,无论请求成功还是失败，都会走这个方法的
				if(data.code == 201){
					$("#TANGRAM__PSP_25__userName").addClass("pass-text-input-error").focus();
					$("#TANGRAM__PSP_25__userName").removeClass("pass-text-input-hover");
					$("#TANGRAM__PSP_25__userName").val("");
					$("#TANGRAM__PSP_25__verifyCode").val("");
					$("#TANGRAM__PSP_25__verifyCodeChange").trigger("click");
					$("#TANGRAM__PSP_25__error").html('帐号或密码错误，请重新输入或者<a href="http://passport.baidu.com/?getpassindex&amp;account=smiletocandy&amp;tpl=do&amp;u=https://tiku.baidu.com/" target="_blank">找回密码</a>');
				}else if(data.code == 202){
					$("#TANGRAM__PSP_25__password").addClass("pass-text-input-error").focus();
					$("#TANGRAM__PSP_25__password").removeClass("pass-text-input-hover");
					$("#TANGRAM__PSP_25__password").val("");
					$("#TANGRAM__PSP_25__verifyCode").val("");
					$("#TANGRAM__PSP_25__verifyCodeChange").trigger("click");
					$("#TANGRAM__PSP_25__error").html('帐号或密码错误，请重新输入或者<a href="http://passport.baidu.com/?getpassindex&amp;account=smiletocandy&amp;tpl=do&amp;u=https://tiku.baidu.com/" target="_blank">找回密码</a>');
				}else if(data.code == 203){
					$("#TANGRAM__PSP_25__verifyCodeImgWrapper").css("display","block");
					$("#TANGRAM__PSP_25__password").addClass("pass-text-input-error").focus();
					$("#TANGRAM__PSP_25__password").removeClass("pass-text-input-hover");
					$("#TANGRAM__PSP_25__password").val("");
					$("#TANGRAM__PSP_25__verifyCode").val("");
					$("#TANGRAM__PSP_25__verifyCodeChange").trigger("click");
					$("#TANGRAM__PSP_25__error").html('帐号或密码错误，请重新输入或者<a href="http://passport.baidu.com/?getpassindex&amp;account=smiletocandy&amp;tpl=do&amp;u=https://tiku.baidu.com/" target="_blank">找回密码</a>');
				} else if(data.code == 204){
					$("#TANGRAM__PSP_25__verifyCodeImgWrapper").css("display","block");
					$("#TANGRAM__PSP_25__verifyCode").addClass("pass-text-input-error").focus();
					$("#TANGRAM__PSP_25__verifyCode").removeClass("pass-text-input-hover");
					$("#TANGRAM__PSP_25__verifyCode").val("");
					$("#TANGRAM__PSP_25__verifyCodeChange").trigger("click");
					$("#TANGRAM__PSP_25__error").html('请输入验证码！');
				} else if(data.code == 205){
					$("#TANGRAM__PSP_25__verifyCode").addClass("pass-text-input-error").focus();
					$("#TANGRAM__PSP_25__verifyCode").removeClass("pass-text-input-hover");
					$("#TANGRAM__PSP_25__verifyCode").val("");
					$("#TANGRAM__PSP_25__verifyCodeChange").trigger("click");
					$("#TANGRAM__PSP_25__error").html('验证码错误，请重新输入！');
				} else if(data.code == 200){
					window.location.reload();
				}
			}
		});
		return false;
	});
	
	$(".header .login-container").bind("mouseover",function(){
		if($(this).find("img").length == 1){
			$(this).addClass("black");
			$(".header .login-option").removeClass("dis-none");
			$(this).find("i").css("transform","rotate(180deg)");
		}
	}).bind("mouseleave",function(event){
		if($(this).find("img").length == 1){
			$(this).removeClass("black");
			$(".header .login-option").addClass("dis-none");
			$(this).find("i").css("transform","rotate(0deg)");
		}
	});
	
	$("#TANGRAM__PSP_25__smsSwitchWrapper").bind("click",function(){
		$("#passport-login-pop-api").css("display","none").css("visibility","hidden");
		$("#TANGRAM__PSP_25__sms").css("display","block").css("visibility","visible");
		$("#TANGRAM__PSP_25__footerQrcodeBtn").show();
		$("#TANGRAM__PSP_25__footerULoginBtn").hide();
	});
	
	$("#TANGRAM__PSP_25__sms_btn_back").bind("click",function(){
		$("#passport-login-pop-api").css("display","block").css("visibility","visible");
		$("#TANGRAM__PSP_25__sms").css("display","none").css("visibility","hidden");
		$("#TANGRAM__PSP_25__footerULoginBtn").show();
		$("#TANGRAM__PSP_25__footerQrcodeBtn").hide();
	});
	
	$("#TANGRAM__PSP_25__verifyCodeChange").bind("click",function(){
		$("#TANGRAM__PSP_25__verifyCodeImg").attr("src",mainHttp + "admin/captcha.jpg?t=" + $.now());
	});
	
})