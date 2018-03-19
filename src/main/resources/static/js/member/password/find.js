$(function(){
	
	$("#account").bind("focus",function(){
		$("#account").addClass("pass-input-focus");
		$("#pass-auth-select .pass-input-msg").text("");
		$("#account").removeClass("pass-input-error");
	}).bind("blur",function(){
		$("#account").removeClass("pass-input-focus");
		// 验证码用户名/邮箱/手机号是否存在
		$.ajax({
			type : "get", 
			url : "checkAccount.html",
			async : true,
			data:{account:$("#account").val()},
			dataType : "json",
			success : function(data) {
				if(data.code == 201){
					$("#pass-auth-select .pass-input-msg").text("用户名不存在，请重新输入");
					$("#account").addClass("pass-input-error");
				}
			}
		});
	});
	
	$("#account").bind("keyup",function(){
		if($("#account").val() != ""){
			$("#userName_clearBtn").show();	
		}else{
			$("#userName_clearBtn").hide();
		}
	});
	
	$("#userName_clearBtn").bind("mouseover",function(){
		$("#userName_clearBtn").addClass("userName-clearbtn-hover");
	}).bind("mouseleave",function(){
		$("#userName_clearBtn").removeClass("userName-clearbtn-hover");
	}).bind("click",function(){
		$("#account").val("");
		$("#userName_clearBtn").hide();
		$("#account").trigger("focus");
	});
	
	$("#veritycode").bind("focus",function(){
		$("#veritycode").addClass("pass-input-focus");
		$("#veritycode").removeClass("pass-input-error");
		$(".vcode-container .pass-input-msg").text("");
	}).bind("blur",function(){
		$("#veritycode").removeClass("pass-input-focus");
	});
	
	$("#submit").bind("click",function(){
		var flag = true;
		if($("#account").val() == ""){
			$("#pass-auth-select .pass-input-msg").text("请您输入用户名/邮箱/手机");
			$("#account").addClass("pass-input-error");
			flag = false;
		}
		if($("#veritycode").val() == ""){
			$(".vcode-container .pass-input-msg").text("请您输入验证码");
			$("#veritycode").addClass("pass-input-error");
			flag = false;
		}
		// 验证码用户名/邮箱/手机号是否存在
		$.ajax({
			type : "get", 
			url : "checkAccount.html",
			async : true,
			data:{account:$("#account").val()},
			dataType : "json",
			success : function(data) {
				if(data.code == 201){
					$("#pass-auth-select .pass-input-msg").text("用户名不存在，请重新输入");
					$("#account").addClass("pass-input-error");
					flag = false;
				}
			}
		});
		if(!flag){
			return false;
		}
	});
})