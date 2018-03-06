$(function(){

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
	
	$(".question-box").bind("click",function(event){
		$(".quelist-wrap").css("left","0px");
		$(".question-box").removeClass("selected-quescontainer");
		$(this).addClass("selected-quescontainer");
		$(".queanalyse-wrap").show(800);
		var dataIndex = $(this).attr("data-index");
		$.ajax({
			type : "get", 
			url : mainHttp + "question/getQuestion/" + $(this).attr("data-id")+ ".html",
			async : true,
			dataType : "json",
			success : function(data) {// 返回数据根据结果进行相应的处理,无论请求成功还是失败，都会走这个方法的
				$(".dt-index").text(dataIndex);
				$(".ui-border-top .center-li .current").text(dataIndex);
				$(".exam-answer-content").html(data.result.answer);
				$(".exam-analysis-content").html(data.result.analysis);
			}
		});
	});
	
	$(".queanalyse-wrap .close-btn").bind("click",function(event){
		$(".queanalyse-wrap").hide(200);
		$(".quelist-wrap").css("left","");
		$(".question-box").removeClass("selected-quescontainer");
	});
	
	$(".download-btn").bind("click" , function(event){
		$(".downleader").show(200);
		$(".mask").css("display","block");
	});
	
	$(".downleader-close").bind("click",function(event){
		$(".downleader").hide(200);
		$(".mask").css("display","none");
	});
	
	$(".recpaper .recp-title .recp-li").bind("click",function(event){
		var dataClass = $(this).attr("data-class");
		$(".recpaper .recp-title .recp-li").removeClass("repc-curr");
		$(this).addClass("repc-curr");
		$(".recpaper .recp-contain").css("display","none");
		$(".recpaper .recp-dis-"+dataClass+"").css("display","block");
		
	});
	
})