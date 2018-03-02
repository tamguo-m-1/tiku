$(function(){

	// 头部选项事件
	$(".header .menu .menu-contain .contain-ul li").bind("mouseover",function(event){
		$(this).find("i").css("transform","rotate(180deg)");
		$(".header .submenu-container .subm-ul").addClass("dis-none");
		$(".header .submenu-container .all-exm").addClass("dis-none");
		$(".header .submenu-container ."+$(this).attr("data-class")+"").removeClass("dis-none");
		event.stopPropagation();
	}).bind("mouseleave",function(event){
		$(this).find("i").css("transform","rotate(0deg)");
		$(".header .submenu-container .subm-ul").addClass("dis-none");
		$(".header .submenu-container .all-exm").addClass("dis-none");
		event.stopPropagation();
	});
	
	// 左侧导航
	$(".content-wp .navbar-container .navbar-list .navbar-list-item").bind("mouseover",function(event){
		$(".content-wp .navbar-container .navbar-list .navbar-list-item").removeClass("navbar-list-item-hover");
		$(this).addClass("navbar-list-item-hover");
		
		$(".content-wp .navbar-container .navbar-list-item-section").css("display","none");
		$("div[datatype='"+$(this).attr("datatype")+"']").css("display","block");
		event.stopPropagation();
	}).bind("mouseleave",function(event){
		$(".content-wp .navbar-container .navbar-list .navbar-list-item").removeClass("navbar-list-item-hover");
		$(".content-wp .navbar-container .navbar-list-item-section").css("display","none");
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
	
	$(".question-box").bind("click",function(event){
		$(".quelist-wrap").css("left","0px");
		$(".queanalyse-wrap").show();
		$.ajax({
			type : "get", 
			url : "./../../../getQuestion/" + $(this).attr("data-id")
					+ ".html",
			async : true,
			dataType : "json",
			success : function(data) {// 返回数据根据结果进行相应的处理,无论请求成功还是失败，都会走这个方法的
				$(".dt-index").text($(this).attr("data-index"));
				$(".exam-answer-content").html(data.result.answer);
				$(".exam-analysis-content").html(data.result.analysis);
			}
		});
		event.stopPropagation();
	});
	
})