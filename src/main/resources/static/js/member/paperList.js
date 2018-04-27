layui.use(['laypage', 'layer'], function(){
	  var laypage = layui.laypage,layer = layui.layer;
	  laypage.render({elem: 'pagination',count: vue.totalCount , limit: 2 , layout: ['count', 'prev', 'page', 'next', 'skip'],
		  jump: function(obj){
			  vue.currPage = obj.curr;
			  vue.reload();
		  }
	  });
});
// Vue
var vue = new Vue({
	el:'.myPositionList',
	data:{
		title:null,
		totalCount:null,
		currPage:1,
		paperList:null,
		paper:{
			courseId:null,
			schoolId:null,
			areaId:null,
			name:null,
			type:null,
			year:null
		}
	},
	methods: {
		// 编辑试卷
		updatePaperFn:function(uid , name ,event){
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
		},
		// 删除试卷
		deletePaperFn:function(uid){
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
								vue.reload();
								layer.closeAll()
							});
						}else{
							layer.alert(r.message);
						}
					}
				});
			});
		},
		// 添加子卷
		addPaperQuestionInfoFn:function(uid){
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
		},
		addPaperFn:function(){
			// 加载科目
			vue.getMenu();
			
			layui.use('form', function(){
				var form = layui.form; 
			  	var html = '';
				html += '<form class="layui-form" action="" style="margin-top: 20px;">';
				html += '<div class="layui-form-item">';
				html += '<label class="layui-form-label">试卷标题</label>';
				html += '<div class="layui-input-block">';
				html += '<input type="text" style="width:400px;" name="paperName" lay-verify="title" autocomplete="off" placeholder="请输入试卷标题" class="layui-input" value="">';
				html += '<input type="hidden" name="addPaperUid" value="">';
				html += '</div>';     
				html += '</div>'; 
				
				html += '<div class="layui-form-item">';
				html += '<label class="layui-form-label">联动选择框</label>';
			    html += '<div class="layui-input-inline">';
			    html += '<select name="paperAreaId">';
			    html += '<option value="">请选择省</option>';
			    html += '<option value="1" selected="">北京</option>';   
			    html += '<option value="2">上海</option>';    
			    html += '<option value="3">重庆</option>';   			    
			    html += '<option value="4">天津</option>';   
			    html += '<option value="5">山东</option>';    
			    html += '<option value="6">河南</option>';   			    
			    html += '<option value="7">湖北</option>';   
			    html += '<option value="8">江苏</option>';    
			    html += '<option value="9">浙江</option>';   			    
			    html += '<option value="10">山西</option>';   
			    html += '<option value="11">福建</option>';    
			    html += '<option value="12">安徽</option>';   			    
			    html += '<option value="13">吉林</option>';   
			    html += '<option value="14">内蒙古</option>';    
			    html += '<option value="15">宁夏</option>';   
			    html += '<option value="16">新疆</option>';    
			    html += '<option value="17">广西</option>';   
			    html += '<option value="18">辽宁</option>';    
			    html += '<option value="19">黑龙江</option>';      
			    html += '<option value="20">陕西</option>';     
			    html += '<option value="21">江西</option>';      
			    html += '<option value="22">广东</option>';      
			    html += '<option value="23">湖南</option>';      
			    html += '<option value="24">海南</option>';      
			    html += '<option value="25">云南</option>';     
			    html += '<option value="26">四川</option>';    
			    html += '<option value="27">青海</option>';    
			    html += '<option value="28">甘肃</option>';    
			    html += '<option value="29">河北</option>';    
			    html += '<option value="30">西藏</option>';   
			    html += '<option value="31">贵州</option>';
			    html += '</select>';    
			    html += '</div>';
			    html += '</div>'; 
			    
			    html += '<div class="layui-form-item">';
				html += '<label class="layui-form-label">学校</label>';
			    html += '<div class="layui-input-inline">';
			    html += '<select name="paperSchoolId">';
			    html += '<option value="" selected="">请选择学校</option>';
			    html += '<option value="1">北京大学附属中学</option>';   
			    html += '<option value="2">北京市第一零一中学</option>';    
			    html += '<option value="3">北京市第四中学</option>';   			    
			    html += '<option value="4">北京市八一学校</option>';   
			    html += '<option value="5">北京师范大学第二附属中学</option>';    
			    html += '<option value="6">东北师范大学附属中学</option>';   			    
			    html += '<option value="7">上海中学</option>';   
			    html += '<option value="8">衡水中学</option>';    
			    html += '</select>';    
			    html += '</div>';
			    html += '</div>'; 
			    
				html += '<div class="layui-form-item">';
				html += '<label class="layui-form-label">科目</label>';
				html += '<div class="layui-input-block">';
				html += '<input type="text" style="width:400px;" name="paperCourseName" readonly onclick="menuTree()" lay-verify="title" autocomplete="off" placeholder="请选择科目" class="layui-input" value="">';
				html += '<input type="hidden" name="addPaperUid" value="">';
				html += '</div>';     
				html += '</div>'; 
				
				html += '<div class="layui-form-item">';
				html += '<label class="layui-form-label">试卷类型</label>';
				html += '<div class="layui-input-block">';
				html += '<input type="radio" name="paperType" value="0" title="真题试卷" checked="">';
				html += '<input type="radio" name="paperType" value="1" title="模拟试卷">';
				html += '<input type="radio" name="paperType" value="2" title="押题预测">';
				html += '<input type="radio" name="paperType" value="3" title="名校精品">';
				html += '</div>';
				html += '</div>';
				
				html += '<div class="layui-form-item">';
				html += '<label class="layui-form-label">年份</label>';
				html += '<div class="layui-input-block">';
				html += '<input type="text" style="width:400px;" name="paperYear" lay-verify="title" autocomplete="off" placeholder="请输入年份" class="layui-input" value="">';
				html += '<input type="hidden" name="addPaperUid" value="">';
				html += '</div>';     
				html += '</div>'; 

				html += '<div class="layui-form-item" style="margin-top: 40px;">';
				html += '<div class="layui-input-block">';
			    html += '<button type="button" class="layui-btn" onclick="saveMainPaper();">立即提交</button>';
			    html += '<button type="reset" class="layui-btn layui-btn-primary" onclick="layer.closeAll()">关闭</button>';
			    html += '</div>';
			    html += '</div>';
				html += '</form>';
				layer.open({
					  title: "添加子卷",
					  type: 1,
					  skin: 'layui-layer-rim', //加上边框
					  area: ['760px', '550px'], //宽高
					  content: html
					});
				form.render();
			});
		},
		// 编辑子卷
		updatePaperQuestionInfoFn:function(uid ,name ,type , title , cuid){
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
			    html += '<button type="button" class="layui-btn" onclick="updatePaperQuestionInfo();">立即提交</button>';
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
		},
		// 删除子卷
		deletePaperQuestionInfoFn:function(uid , cuid){
			layer.confirm('您确认要删除？删除后无法恢复！', {
				btn: ['确认','取消'] //按钮
			}, function(){
				$.ajax({
					type : "get", 
					url : mainHttp + "member/paperList/deletePaperQuestionInfoBtn.html",
					data:{paperId:uid , cuid : cuid},
					async : true,
					dataType : "json",
					success : function(data) {// 返回数据根据结果进行相应的处理,无论请求成功还是失败，都会走这个方法的
						if(data.code === 0){
							layer.alert('操作成功', function(index){
								vue.reload();
								layer.closeAll()
							});
						}else{
							layer.alert(r.message);
						}
					}
				});
			});
		},
		reload: function (event) {
			$.ajax({
				type : "get", 
				url : mainHttp + "/member/paper/list.html",
				data:{name:$("input[name='keySearch']").val(),page:vue.currPage,limit:2},
				async : true,
				dataType : "json",
				success : function(data) {
					vue.paperList = data.list;
					vue.totalCount = data.totalCount;
				}
			});
		},
		getMenu: function(menuId){
			$.ajax({
				type : "get", 
				url : mainHttp + "/subject/getCourseTree.html",
				async : true,
				dataType : "json",
				success : function(r) {
					ztree = $.fn.zTree.init($("#menuTree"), setting, r.result);
				}
			});
		},
	}
});

vue.reload();

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
					vue.reload();
					layer.closeAll()
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
					vue.reload();
					layer.closeAll()
				});
			}else{
				alert(r.message);
			}
		}
	});
	return false;
}

function updatePaperQuestionInfo(){
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
					vue.reload();
					layer.closeAll()
				});
			}else{
				alert(r.message);
			}
		}
	});
	return false;
}

// 保存试卷
function saveMainPaper(){
	vue.paper.name = $("input[name='paperName']").val();
	vue.paper.year = $("input[name='paperYear']").val();
	vue.paper.type = $("input[name='paperType']:checked").val();
	vue.paper.areaId = $("select[name='paperAreaId']").val();
	vue.paper.schoolId = $("select[name='paperSchoolId']").val();
	if(vue.paper.name == ""){
		layer.alert("请输入试卷名称");
		return false;
	}
	if(vue.paper.courseId == ""){
		layer.alert("请选择科目");
		return false;
	}
	$.ajax({
		type : "get", 
		url : mainHttp + "member/paperList/addPaper.html",
		data:vue.paper,
		async : true,
		dataType : "json",
		success : function(data) {// 返回数据根据结果进行相应的处理,无论请求成功还是失败，都会走这个方法的
			if(data.code === 0){
				layer.alert('操作成功', function(index){
					vue.reload();
					layer.closeAll()
				});
			}else{
				alert(r.message);
			}
		}
	});
}

var setting = {
	data: {
		simpleData: {
			enable: true,
			idKey: "uid",
			pIdKey: "parentId",
			rootPId: -1
		},
		key: {
			url:"nourl"
		}
	}
};
var ztree;
function menuTree(){
	layer.open({
		type: 1,
		offset: '200px',
		skin: 'layui-layer-molv',
		title: "选择菜单",
		area: ['400px', '600px'],
		shade: 0,
		shadeClose: false,
		content: jQuery("#menuLayer"),
		btn: ['确定', '取消'],
		btn1: function (index) {
			var node = ztree.getSelectedNodes();
			vue.paper.courseId = node[0].uid;
			$("input[name='paperCourseName']").val(node[0].name);
			layer.close(index);
        }
	});
}