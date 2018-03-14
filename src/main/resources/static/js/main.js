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
	
})