$(function(){
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
})