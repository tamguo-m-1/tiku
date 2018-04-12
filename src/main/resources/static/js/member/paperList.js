

$(function(){
	$(".updatePaperBtn").click(function(){
		var name = $(this).attr("data-name");
		var uid = $(this).attr("data-uid");
		var html = '';
		html += '<form class="layui-form" action="" style="margin-top: 20px;">';
		html += '<div class="layui-form-item">';
		html += '<label class="layui-form-label">试卷名称</label>';
		html += '<div class="layui-input-block">';
		html += '<input type="text" style="width:300px;" name="updatePaperName" lay-verify="title" autocomplete="off" placeholder="请输入试卷名称" class="layui-input" value="'+name+'">';
		html += '<input type="hidden" name="updatePaperUid" value="'+uid+'">';
		html += '</div>';     
		html += '</div>'; 
		html += '<div class="layui-form-item" style="margin-top: 40px;">';
		html += '<div class="layui-input-block">';
	    html += '<button type="button" class="layui-btn" onclick="saveUpdatePaper();">立即提交</button>';
	    html += '<button type="reset" class="layui-btn layui-btn-primary" onclick="layer.closeAll()">关闭</button>';
	    html += '</div>';
	    html += '</div>';
		html += '</form>';
		layer.open({
		  title: "编辑试卷",
		  type: 1,
		  skin: 'layui-layer-rim', //加上边框
		  area: ['460px', '240px'], //宽高
		  content: html
		});
	});
	
	$(".deletePaperBtn").click(function(){
		var uid = $(this).attr("data-uid");
		layer.confirm('您确认要删除？删除后无法恢复！', {
			btn: ['确认','取消'] //按钮
		}, function(){
			$.ajax({
				type : "get", 
				url : mainHttp + "member/paperList/deletePaper.html",
				data:{paperId:uid},
				async : true,
				dataType : "json",
				success : function(data) {// 返回数据根据结果进行相应的处理,无论请求成功还是失败，都会走这个方法的
					if(data.code === 0){
						layer.alert('操作成功', function(index){
							// 刷新页面
						});
					}else{
						layer.alert(r.message);
					}
				}
			});
		});
	});
	
	$(".addPaper").click(function(){
		
		var uid = $(this).attr("data-uid");
		
		layui.use('form', function(){
			var form = layui.form; 
		  	var html = '';
			html += '<form class="layui-form" action="" style="margin-top: 20px;">';
			html += '<div class="layui-form-item">';
			html += '<label class="layui-form-label">子卷标题</label>';
			html += '<div class="layui-input-block">';
			html += '<input type="text" style="width:400px;" name="childPaperTitle" lay-verify="title" autocomplete="off" placeholder="请输入子卷标题" class="layui-input" value="">';
			html += '<input type="hidden" name="addPaperUid" value="'+uid+'">';
			html += '</div>';     
			html += '</div>'; 
			
			html += '<div class="layui-form-item">';
			html += '<label class="layui-form-label">单选框</label>';
			html += '<div class="layui-input-block">';
			html += '<input type="radio" name="childPaperQuestionType" value="1" title="单选题" checked="">';
			html += '<input type="radio" name="childPaperQuestionType" value="2" title="多选题">';
			html += '<input type="radio" name="childPaperQuestionType" value="3" title="填空题">';
			html += '<input type="radio" name="childPaperQuestionType" value="4" title="判断题">';
			html += '<input type="radio" name="childPaperQuestionType" value="5" title="问答题">';
			html += '</div>';
			html += '</div>';

			html += '<div class="layui-form-item" style="margin-top: 40px;">';
			html += '<div class="layui-input-block">';
		    html += '<button type="button" class="layui-btn" onclick="saveAddPaper();">立即提交</button>';
		    html += '<button type="reset" class="layui-btn layui-btn-primary" onclick="layer.closeAll()">关闭</button>';
		    html += '</div>';
		    html += '</div>';
			html += '</form>';
			layer.open({
				  title: "添加子卷",
				  type: 1,
				  skin: 'layui-layer-rim', //加上边框
				  area: ['560px', '300px'], //宽高
				  content: html
				});
			form.render();
		});
	});
	
	$(".updatePaperQuestionInfoBtn").click(function(){
		var uid = $(this).attr("data-uid");
		var name = $(this).attr("data-name");
		var type = $(this).attr("data-type");
		var title = $(this).attr("data-title");
		var cuid = $(this).attr("data-cuid");
		
		layui.use('form', function(){
			var form = layui.form; 
		  	var html = '';
			html += '<form class="layui-form" action="" style="margin-top: 20px;">';
			html += '<div class="layui-form-item">';
			html += '<label class="layui-form-label">子卷标题</label>';
			html += '<div class="layui-input-block">';
			html += '<input type="text" style="width:400px;" name="childPaperTitle" lay-verify="title" autocomplete="off" placeholder="请输入子卷标题" class="layui-input" value="'+title+'">';
			html += '<input type="hidden" name="addPaperUid" value="'+uid+'">';
			html += '<input type="hidden" name="cUid" value="'+cuid+'">';
			html += '</div>';     
			html += '</div>'; 
			
			html += '<div class="layui-form-item">';
			html += '<label class="layui-form-label">单选框</label>';
			html += '<div class="layui-input-block">';
			if(type == '1'){
				html += '<input type="radio" name="childPaperQuestionType" value="1" title="单选题" checked="">';
			}else{
				html += '<input type="radio" name="childPaperQuestionType" value="1" title="单选题">';
			}
			if(type == '2'){
				html += '<input type="radio" name="childPaperQuestionType" value="2" title="多选题" checked="">';
			}else{
				html += '<input type="radio" name="childPaperQuestionType" value="2" title="多选题">';
			}
			if(type == '3'){
				html += '<input type="radio" name="childPaperQuestionType" value="3" title="填空题" checked="">';
			}else{
				html += '<input type="radio" name="childPaperQuestionType" value="3" title="填空题">';
			}
			if(type == '4'){
				html += '<input type="radio" name="childPaperQuestionType" value="4" title="判断题" checked="">';
			}else{
				html += '<input type="radio" name="childPaperQuestionType" value="4" title="判断题">';
			}
			if(type == '5'){
				html += '<input type="radio" name="childPaperQuestionType" value="5" title="问答题" checked="">';
			}else{
				html += '<input type="radio" name="childPaperQuestionType" value="5" title="问答题">';
			}
			html += '</div>';
			html += '</div>';

			html += '<div class="layui-form-item" style="margin-top: 40px;">';
			html += '<div class="layui-input-block">';
		    html += '<button type="button" class="layui-btn" onclick="saveUpdatePaper();">立即提交</button>';
		    html += '<button type="reset" class="layui-btn layui-btn-primary" onclick="layer.closeAll()">关闭</button>';
		    html += '</div>';
		    html += '</div>';
			html += '</form>';
			layer.open({
				  title: "修改子卷",
				  type: 1,
				  skin: 'layui-layer-rim', //加上边框
				  area: ['560px', '300px'], //宽高
				  content: html
				});
			form.render();
		});
	});
})

function saveUpdatePaper(){
	var paperId = $("input[name='updatePaperUid']").val();
	var name = $("input[name='updatePaperName']").val();
	$.ajax({
		type : "get", 
		url : mainHttp + "member/paperList/updatePaperName.html",
		data:{paperId:paperId,name:name},
		async : true,
		dataType : "json",
		success : function(data) {// 返回数据根据结果进行相应的处理,无论请求成功还是失败，都会走这个方法的
			if(data.code === 0){
				layer.alert('操作成功', function(index){
					// 刷新页面
				});
			}else{
				alert(r.message);
			}
		}
	});
	return false;
}

function saveAddPaper(){
	var paperId = $("input[name='addPaperUid']").val();
	var title = $("input[name='childPaperTitle']").val();
	var type = $("input[name='childPaperQuestionType']:checked").val();
	var name = $("input[name='childPaperQuestionType']:checked").attr("title");
	$.ajax({
		type : "get", 
		url : mainHttp + "member/paperList/addPaperQuestionInfo.html",
		data:{paperId:paperId,title:title,type:type,name:name},
		async : true,
		dataType : "json",
		success : function(data) {// 返回数据根据结果进行相应的处理,无论请求成功还是失败，都会走这个方法的
			if(data.code === 0){
				layer.alert('操作成功', function(index){
					// 刷新页面
				});
			}else{
				alert(r.message);
			}
		}
	});
	return false;
}

function saveUpdatePaper(){
	//var qUid = $("input[name='childPaperTitle']").val();
	var paperId = $("input[name='addPaperUid']").val();
	var title = $("input[name='childPaperTitle']").val();
	var type = $("input[name='childPaperQuestionType']:checked").val();
	var name = $("input[name='childPaperQuestionType']:checked").attr("title");
	var cuid = $("input[name='cUid']").val();
	$.ajax({
		type : "get", 
		url : mainHttp + "member/paperList/updatePaperQuestionInfo.html",
		data:{paperId:paperId,title:title,type:type,name:name,cuid:cuid},
		async : true,
		dataType : "json",
		success : function(data) {// 返回数据根据结果进行相应的处理,无论请求成功还是失败，都会走这个方法的
			if(data.code === 0){
				layer.alert('操作成功', function(index){
					// 刷新页面
				});
			}else{
				alert(r.message);
			}
		}
	});
	return false;
}
