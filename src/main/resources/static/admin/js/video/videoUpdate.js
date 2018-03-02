$(function () {
	$('#tag').tagsInput({
		'defaultText':'请输入', //默认文字
		'minChars' : 1, //每个标签的小最字符
		'maxChars' : 20 //每个标签的最大字符，如果不设置或者为0，就是无限大
	});
	
	var ue = UE.getEditor('editor',{
			initialFrameWidth :920,//设置编辑器宽度
			initialFrameHeight:250,//设置编辑器高度
			scaleEnabled:true
	});
});

var setting = {
	data: {
		simpleData: {
			enable: true,
			idKey: "menuId",
			pIdKey: "menuParent",
			rootPId: -1
		},
		key: {
			url:"nourl",
			name: "menuNm"
		}
	},
	check:{
		enable:true,
		nocheckInherit:true
	}
};



var ztree;
var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"修改",
		video:{}
	},
	methods: {
		getVideo: function(vid){
            $.get("../video/info/"+vid, function(r){
            	vm.video = r.result;
                //勾选角色所拥有的菜单
            	$('#tag').importTags(r.result.tag);
            	$('#pics').val(r.result.pics);
            	var editor = UE.getEditor('editor');
            	editor.ready(function(){
            		if(r.result.description != '' && r.result.description != null && r.result.description.length > 0){
            			editor.setContent(r.result.description);//赋值给UEditor 
            		}else{
            			editor.setContent('');
            		}
    			});
            	
        		
    			var menuIds = vm.video.menuIdList;
    			for(var i=0; i<menuIds.length; i++) {
    				var node = ztree.getNodeByParam("menuId", menuIds[i]);
    				ztree.checkNode(node, true, false);
    			}
    		});
		},
		saveOrUpdate: function (event) {
			//获取选择的菜单
			var nodes = ztree.getCheckedNodes(true);
			var menuIdList = new Array();
			for(var i=0; i<nodes.length; i++) {
				menuIdList.push(nodes[i].menuId);
			}
			vm.video.menuIdList = menuIdList;
			vm.video.tag=$('#tag').val();
			vm.video.pics = $('#pics').val();
			vm.video.description=UE.getEditor('editor').getContent();
			var url = vm.video.vid == null ? "../video/save" : "../video/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.video),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.reload();
							
						});
					}else{
						alert(r.message);
					}
				}
			});
		},
		
		getMenuTree: function() {
			//加载菜单树
			$.get("../video/ztreeMenu", function(r){
				ztree = $.fn.zTree.init($("#menuTree"), setting, r.result);
				//展开所有节点
				ztree.expandAll(false);
				var queryParame = location.search;
				queryParame = queryParame.split('&')[0];
				var parame = queryParame.replace("?vid=", "")
				vm.getVideo(parame);
			});
	    },
	    reload: function (event) {
	    	window.location.href ='../admin/video/video.html';
		}
	},
	created:function () {
	    this.getMenuTree();
	}
});