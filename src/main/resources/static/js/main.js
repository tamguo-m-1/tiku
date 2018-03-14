$(function(){
	
	// 头部选项事件
	$(".header .menu .menu-contain .contain-ul li").bind("mouseover",function(event){
		$(".header .menu .menu-contain .contain-ul li i").css("transform","rotate(0deg)");
		$(this).find("i").css("transform","rotate(180deg)");
		$(".header .submenu-container .subm-ul").addClass("dis-none");
		$(".header .submenu-container .all-exm").addClass("dis-none");
		$(".header .submenu-container ."+$(this).attr("data-class")+"").removeClass("dis-none");
		$(this).addClass("li-hover");
		event.stopPropagation();
	}).bind("mouseleave",function(event){
		if(event.relatedTarget.id != "submenu"){
			$(this).find("i").css("transform","rotate(0deg)");
			$(".header .submenu-container .subm-ul").addClass("dis-none");
			$(".header .submenu-container .all-exm").addClass("dis-none");
			$(".header .menu .menu-contain .contain-ul li").removeClass("li-hover");
			event.stopPropagation();
		}
	});
	$(".header .submenu-container").bind("mouseleave",function(event){
		if(event.relatedTarget.className != "contain-ul"){
			$(".header .menu .menu-contain .contain-ul li i").css("transform","rotate(0deg)");
			$(".header .submenu-container .subm-ul").addClass("dis-none");
			$(".header .submenu-container .all-exm").addClass("dis-none");
			$(".header .menu .menu-contain .contain-ul li").removeClass("li-hover");
			event.stopPropagation();
		}
	});
	
	// 左侧导航
	$(".content-wp .navbar-container .navbar-list .navbar-list-item").bind("mouseover",function(event){
		$(".content-wp .navbar-container .navbar-list .navbar-list-item").removeClass("navbar-list-item-hover");
		$(this).addClass("navbar-list-item-hover");
		
		$(".content-wp .navbar-container .navbar-list-item-section").css("display","none");
		$("div[datatype='"+$(this).attr("datatype")+"']").css("display","block");
		event.stopPropagation();
	}).bind("mouseleave",function(event){
		if(event.relatedTarget.className != "navbar-list-item-section"){
			$(".content-wp .navbar-container .navbar-list .navbar-list-item").removeClass("navbar-list-item-hover");
			$(".content-wp .navbar-container .navbar-list-item-section").css("display","none");
			event.stopPropagation();
		}
	});
	
	$(".content-wp .navbar-container .navbar-list-item-section").bind("mouseleave",function(event){
		$(".content-wp .navbar-container .navbar-list .navbar-list-item").removeClass("navbar-list-item-hover");
		$(".content-wp .navbar-container .navbar-list-item-section").css("display","none");
		event.stopPropagation();
	});
	
	// 马上做题
	$(".detail-kpoint-2").bind("mouseover",function(event){
		$(this).find(".mask").show();
		event.stopPropagation();
	}).bind("mouseleave",function(event){
		$(this).find(".mask").hide();
		event.stopPropagation();
	});
	
	// 问题列表
	$(".quelist-wrap .question-box").bind("mouseover",function(event){
		$(this).addClass("hover-quescontainer");
		$(this).find(".view-analyse").find(".view-link").css("color","#11a68d!important");
		event.stopPropagation();
	}).bind("mouseleave",function(event){
		$(this).removeClass("hover-quescontainer");
		$(this).find(".view-analyse").find(".view-link").css("color","#11a68d!important");
		event.stopPropagation();
	});
	
	// 登录窗口拖动
	$.drag({
		target:'#TANGRAM__PSP_19__title',
		root:'#passport-login-pop',
		lock:true
	});
	
	$(".header .login-container").bind("click",function(event){
		$(".mask").css("display","block");
		$("#passport-login-pop").css("display","block");
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
					$("#TANGRAM__PSP_25__error").html('帐号或密码错误，请重新输入或者<a href="http://passport.baidu.com/?getpassindex&amp;account=smiletocandy&amp;tpl=do&amp;u=https://tiku.baidu.com/" target="_blank">找回密码</a>');
				}else if(data.code == 202){
					$("#TANGRAM__PSP_25__password").addClass("pass-text-input-error").focus();
					$("#TANGRAM__PSP_25__password").removeClass("pass-text-input-hover");
					$("#TANGRAM__PSP_25__password").val("");
					$("#TANGRAM__PSP_25__error").html('帐号或密码错误，请重新输入或者<a href="http://passport.baidu.com/?getpassindex&amp;account=smiletocandy&amp;tpl=do&amp;u=https://tiku.baidu.com/" target="_blank">找回密码</a>');
				}else if(data.code == 203){
					$("#TANGRAM__PSP_25__verifyCodeImgWrapper").css("display","block");
					$("#TANGRAM__PSP_25__password").addClass("pass-text-input-error").focus();
					$("#TANGRAM__PSP_25__password").removeClass("pass-text-input-hover");
					$("#TANGRAM__PSP_25__password").val("");
					$("#TANGRAM__PSP_25__error").html('帐号或密码错误，请重新输入或者<a href="http://passport.baidu.com/?getpassindex&amp;account=smiletocandy&amp;tpl=do&amp;u=https://tiku.baidu.com/" target="_blank">找回密码</a>');
				} else if(data.code == 204){
					$("#TANGRAM__PSP_25__verifyCode").addClass("pass-text-input-error").focus();
					$("#TANGRAM__PSP_25__verifyCode").removeClass("pass-text-input-hover");
					$("#TANGRAM__PSP_25__verifyCode").val("");
					$("#TANGRAM__PSP_25__error").html('验证码错误，请重新输入！');
				} else if(data.code == 200){
					window.location.reload();
				}
			}
		});
		return false;
	});
	
	$(".header .login-container").bind("mouseover",function(){
		$(this).addClass("black");
		$(".header .login-option").removeClass("dis-none");
	}).bind("mouseleave",function(event){
		$(this).removeClass("black");
		$(".header .login-option").addClass("dis-none");
	});
	
	$("#TANGRAM__PSP_25__smsSwitchWrapper").bind("click",function(){
		$("#passport-login-pop-api").css("display","none").css("visibility","hidden");
		$("#TANGRAM__PSP_25__sms").css("display","block").css("visibility","visible");
	});
	
	$("#TANGRAM__PSP_25__sms_btn_back").bind("click",function(){
		$("#passport-login-pop-api").css("display","block").css("visibility","visible");
		$("#TANGRAM__PSP_25__sms").css("display","none").css("visibility","hidden");
	});
	
})