$(function(){
	
	// 首页轮播
	$(".content-wp .banner .play-nav li").bind("click",function(event){
		$(".content-wp .banner .play-nav li").removeClass("cur");
		$(this).addClass("cur");
		
		$(".content-wp .banner .banner-list li").css("z-index","1");
		$(".content-wp .banner .banner-list a[data-index='"+$(this).find("a").text()+"']").parent().css("z-index","9");
		event.stopPropagation();
	});
	
	// 章节div
	$(".content-wp .course-container .course-list a").bind("click",function(){
		$(".content-wp .course-container .course-list a").removeClass("course-item-select");
		$(this).addClass("course-item-select");
		var courseId = $(this).attr("courseid");
		$.ajax({
			type : "get", 
			url : mainHttp + "chapter/findChapter/" +courseId+".html",
			async : true,
			dataType : "json",
			success : function(data) {// 返回数据根据结果进行相应的处理,无论请求成功还是失败，都会走这个方法的
				var html = "";
				for(var i=0 ; i<data.length ; i++){
					if(i >= 5){
						break;
					}
					html += '<li class="chapterlist-item">';
					html += '<p class="chapterlist-item-name">';
					html += '<a class="chapterlist-item-name-link" data-kp="a9d6195f312b3169a451a405" href="/tikupc/chapterlist/1bfd700abb68a98271fefa04-16-knowpoint-11#dct-0">';
					html += '<span>'+data[i].name+'</span>';
					html += '</a>';
					html += '</p>';
					html += '<p class="chapterlist-detail"><span>'+data[i].pointNum+'</span>知识点 | <span>'+data[i].questionNum+'</span>试题</p>';
					html += '<a class="chapterlist-item-btn" data-kp="a9d6195f312b3169a451a405" href="/tikupc/chapterlist/1bfd700abb68a98271fefa04-16-knowpoint-11#dct-0">开始学习</a>';
					html += '</li>';
				}
				$(".course-container .chapter-list ul").html(html);
			}
		});
	});
	
	// 城市
	$(".gaokaopaper-container .cityArea a").bind("click",function(){
		$(".gaokaopaper-container .cityArea a").removeClass("selected");
		$(this).addClass("selected");
		
		var areaId = $(this).attr("provinceid");
		$.ajax({
			type : "get", 
			url : mainHttp + "/paper/area/"+areaId+"-1.html",
			async : true,
			dataType : "json",
			success : function(data) {// 返回数据根据结果进行相应的处理,无论请求成功还是失败，都会走这个方法的
				var html = "";
				for(var i=0 ; i<data.length ; i++){
					if(i >= 6){
						break;
					}
					html += '<li class="list-item">';
					html += '<p class="list-item-name">';
					html += '<a class="list-item-name-link" data-paperid="d08fb966f5335a8102d220a5" href="/tikupc/paperdetail/d08fb966f5335a8102d220a5">'+data[i].name+'</a>';
					html += '</p>';
					html += '<p class="list-item-detail">';
					html += '<a class="list-item-link list-item-link-jiexi" data-paperid="d08fb966f5335a8102d220a5" href="/tikupc/paperdetail/d08fb966f5335a8102d220a5?showan=1" target="_blank">解析</a><span class="item-separate">|</span>';
					html += '<a "=" " class=" list-item-link  list-item-link-xiazai" data-paperid="d08fb966f5335a8102d220a5 " href="/tikupc/paperdetail/d08fb966f5335a8102d220a5" target="_blank ">下载</a><span class="item-separate ">|</span><a class="list-item-link list-item-link-gufen " data-paperid="d08fb966f5335a8102d220a5 " href="/gaokaogufen/paperdetail/d08fb966f5335a8102d220a5 ">估分</a></p>';
					html += '</li>';
				}
				$(".gaokaopaper-container .paper-main .paper-list-zhenti").html(html);
			}
		});
		
		$.ajax({
			type : "get", 
			url : mainHttp + "/paper/area/"+areaId+"-2.html",
			async : true,
			dataType : "json",
			success : function(data) {// 返回数据根据结果进行相应的处理,无论请求成功还是失败，都会走这个方法的
				var html = "";
				for(var i=0 ; i<data.length ; i++){
					if(i >= 6){
						break;
					}
					html += '<li class="list-item">';
					html += '<p class="list-item-name">';
					html += '<a class="list-item-name-link" data-paperid="d08fb966f5335a8102d220a5" href="/tikupc/paperdetail/d08fb966f5335a8102d220a5">'+data[i].name+'</a>';
					html += '</p>';
					html += '<p class="list-item-detail">';
					html += '<a class="list-item-link list-item-link-jiexi" data-paperid="d08fb966f5335a8102d220a5" href="/tikupc/paperdetail/d08fb966f5335a8102d220a5?showan=1" target="_blank">解析</a><span class="item-separate">|</span>';
					html += '<a "=" " class=" list-item-link  list-item-link-xiazai" data-paperid="d08fb966f5335a8102d220a5 " href="/tikupc/paperdetail/d08fb966f5335a8102d220a5" target="_blank ">下载</a><span class="item-separate ">|</span><a class="list-item-link list-item-link-gufen " data-paperid="d08fb966f5335a8102d220a5 " href="/gaokaogufen/paperdetail/d08fb966f5335a8102d220a5 ">估分</a></p>';
					html += '</li>';
				}
				$(".gaokaopaper-container .paper-main .paper-list-moni").html(html);
			}
		});
		
		$.ajax({
			type : "get", 
			url : mainHttp + "/paper/area/"+areaId+"-n.html",
			async : true,
			dataType : "json",
			success : function(data) {// 返回数据根据结果进行相应的处理,无论请求成功还是失败，都会走这个方法的
				var html = "";
				for(var i=0 ; i<data.length ; i++){
					html += '<li class="hotpaper-list-item">';
					html += '<a class="hotpaper-name" data-paperid="d08fb966f5335a8102d220a5" href="/tikupc/paperdetail/d08fb966f5335a8102d220a5">';
					html += '<span class="rank-'+(i+1)+' hotpaper-rank"></span><span>'+data[i].name+'</span>'
					html += '</a>';
					html += '</li>';
				}
				$(".gaokaopaper-container .paper-main .hotpaper-list").html(html);
			}
		});
		
		// 获取学校
		$.ajax({
			type : "get", 
			url : mainHttp + "school/area/"+areaId+".html",
			async : true,
			dataType : "json",
			success : function(data) {// 返回数据根据结果进行相应的处理,无论请求成功还是失败，都会走这个方法的
				
				var html = '';
				for(var i=0 ; i<data.length ; i++){
					if(i == 2){
						html += '<div class="school-list-item school-list-item-lastchild">';
					}else{
						html += '<div class="school-list-item">';	
					}
					html += '<div class="school-wrap school-wrap-bg1">';
					html += '<a class="famous-school-link" data-schoolid="924cf7ec4afe04a1b071de05" href="/tikupc/paperlist/1bfd700abb68a98271fefa04-0-0-0-0-1-download?school_id=924cf7ec4afe04a1b071de05">';
					html += '<div class="school-info">';
					html += '<p class="name">'+data[i].name+'</p>';
					html += '<p class="info">';
					html += '<b>同地区推荐 </b> 共<span class="info-num">3</span>套试卷';
					html += '</p>';
					html += '</div>';
					html += '</a>';
					html += '</div>';
					html += '<ul class="paper-wrap">';
					for(var n=0 ; n<data[i].paperList.length ; n++){
						html += '<li class="paper-item">';
						html += '<a class="paper-item-name" data-paperid="5ff2971ea76e58fafab0030f" href="/tikupc/paperdetail/5ff2971ea76e58fafab0030f">'+data[i].paperList[n].name+'</a>';
						html += '</li>';
					}
					html += '</ul>';
					html += '</div>';
				}
				$(".gaokaopaper-container .school-paper-main .school-list").html(html);
			}
		});
	});
})