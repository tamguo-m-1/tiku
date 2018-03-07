$(function(){
	
	// 首页轮播
	$(".content-wp .banner .play-nav li").bind("click",function(event){
		$(".content-wp .banner .play-nav li").removeClass("cur");
		$(this).addClass("cur");
		
		$(".content-wp .banner .banner-list li").css("z-index","1");
		$(".content-wp .banner .banner-list a[data-index='"+$(this).find("a").text()+"']").parent().css("z-index","9");
		event.stopPropagation();
	});
})