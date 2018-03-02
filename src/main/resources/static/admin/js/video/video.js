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
	
	
    $("#jqGrid").jqGrid({
        url: '../video/list',
        datatype: "json",
        colModel: [			
			{ label: '电影编号', name: 'vid', width: 45, key: true },
			{ label: '电影名称', name: 'videoName', width: 75 },
			{ label: '标签', name: 'tag', width: 90 },
			{ label: '简介', name: 'introduction', width: 100, formatter: function(value, options, row){
				var intro = '<span>'+value+'</span>';
				if(value.length > 20){
					intro = '<span title="'+value+'">'+value.substring(0,20) +'...</span>'
				}
				return intro;
			}},
			{ label: '状态', name: 'status', width: 80, formatter: function(value, options, row){
				return value == 0 ? 
					'<span class="label label-success">正常</span>' : 
					'<span class="label label-danger">下架</span>';
			}},
			{ label: '创建时间', name: 'createTime', width: 80,formatter: 'date', formatoptions: { srcformat: 'u', newformat: 'Y-m-d H:i:s' }}             
        ],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
        pager: "#jqGridPager",
        jsonReader : {
            root: "list",
            page: "currPage",
            total: "totalPage",
            records: "totalCount"
        },
        prmNames : {
            page:"page", 
            rows:"limit", 
            order: "order"
        },
        gridComplete:function(){
        	//隐藏grid底部滚动条
        	$("#jqGrid").closest(".ui-jqgrid-bdiv").css({ "overflow-x" : "hidden" }); 
        }
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
		q:{
			videoName: null
		},
		showList: true,
		title:null,
		video:{status:0}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.video = {};
			vm.video = {status:0};
			vm.getMenuTree(null);
			$('#tag').val('');
			UE.getEditor('editor').setContent('');
			
		},
		update: function () {
			var vid = getSelectedRow();
			if(vid == null){
				return ;
			}
			$.get("../video/info/"+vid, function(r){
            	window.location.href ='../video/videoUpdate.html?vid='+vid+'&pics='+encodeURIComponent(r.result.pics);
    		});
			
//			var vid = getSelectedRow();
//			if(vid == null){
//				return ;
//			}
//			
//			vm.showList = false;
//            vm.title = "修改";
//            vm.getMenuTree(vid);
		},
		del: function () {
			var vids = getSelectedRows();
			if(vids == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../video/delete",
				    data: JSON.stringify(vids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功', function(index){
								$("#jqGrid").trigger("reloadGrid");
							});
						}else{
							alert(r.message);
						}
					}
				});
			});
		},
		recommend: function () {
			var vids = getSelectedRows();
			if(vids == null){
				return ;
			}
			
			confirm('确定要推荐选中的记录到首页？', function(){
				$.ajax({
					type: "POST",
				    url: "../video/recommend",
				    data: JSON.stringify(vids),
				    success: function(r){
						if(r.code == 0){
							alert('操作成功');
						}else{
							alert(r.message);
						}
					}
				});
			});
		},
		getVideo: function(vid){
            $.get("../video/info/"+vid, function(r){
            	vm.video = r.result;
                //勾选角色所拥有的菜单
            	$('#tag').importTags(r.result.tag);
            	vm.video.pics = $('#pics').val();
        		if(r.result.description != '' && r.result.description != null && r.result.description.length > 0){
        			UE.getEditor('editor').setContent(r.result.description);//赋值给UEditor 
        		}else{
        			UE.getEditor('editor').setContent('');
        		}
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
		
		getMenuTree: function(vid) {
			//加载菜单树
			$.get("../video/ztreeMenu", function(r){
				ztree = $.fn.zTree.init($("#menuTree"), setting, r.result);
				//展开所有节点
				ztree.expandAll(false);
				if(vid != null){
					vm.getVideo(vid);
				}
			});
	    },
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData:{'videoName': vm.q.videoName},
                page:page
            }).trigger("reloadGrid");
		}
	}
});