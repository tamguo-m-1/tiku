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
	    html += '<button type="reset" class="layui-btn layui-btn-primary">关闭</button>';
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
			if(data.code == 0){
				alert(1);
			}
		}
	});
	return false;
}

