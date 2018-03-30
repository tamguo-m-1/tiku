$(function () {
    $("#jqGrid").jqGrid({
        url: mainHttp + 'admin/course/list.html',
        datatype: "json",
        colModel: [			
			{ label: '科目ID', name: 'uid', width: 40, key: true },
			{ label: '考试ID', name: 'subjectId', width: 60 },
			{ label: '科目名称', name: 'name', width: 60 },
			{ label: '排序', name: 'orders', width: 60 },
			{ label: '题目数量', name: 'questionNum', width: 60 },
			{ label: '知识点数量', name: 'pointNum', width: 100 },
			{ label: '图标', name: 'icon', width: 50}                   
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
			idKey: "uid",
			pIdKey: "parentId",
			rootPId: -1
		},		
		key: {
			url:"nourl"
		}
	},
	view: {
		addHoverDom: addHoverDom,
		removeHoverDom: removeHoverDom,
		selectedMulti: false
	},
	edit: {
		enable: true
	}
};
var newCount = 1;
function addHoverDom(treeId, treeNode) {
	var sObj = $("#" + treeNode.tId + "_span");
	if (treeNode.editNameFlag || $("#addBtn_"+treeNode.tId).length>0) return;
	var addStr = "<span class='button add' id='addBtn_" + treeNode.tId
		+ "' title='add node' onfocus='this.blur();'></span>";
	sObj.after(addStr);
	var btn = $("#addBtn_"+treeNode.tId);
	if (btn) btn.bind("click", function(){
		var zTree = $.fn.zTree.getZTreeObj("menuTree");
		zTree.addNodes(treeNode, {id:(100 + newCount), pId:treeNode.id, name:"new node" + (newCount++)});
		return false;
	});
};
function removeHoverDom(treeId, treeNode) {
	$("#addBtn_"+treeNode.tId).unbind().remove();
};  
var ztree;
var vm = new Vue({
	el:'#rrapp',
	data:{
		showList: true,
		title: null,
		course:{
			name:null,
			subjectId:null,
			pointNum:null,
			questionNum:null,
			icon:null,
			orders:0,
			chapterList:[]
		}
	},
	methods: {
		getMenu: function(menuId){
			//加载菜单树
			$.get(mainHttp + "admin/course/getChapterTree/"+vm.course.uid+".html", function(r){
				ztree = $.fn.zTree.init($("#menuTree"), setting, r.result);
			})
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.course = {uid:null,name:null,subjectId:null,pointNum:null,questionNum:null,icon:null,orders:0};
			vm.getMenu();
		},
		update: function (event) {
			var courseId = getSelectedRow();
			if(courseId == null){
				return ;
			}
			$.ajax({
				type : "get", 
				url : mainHttp + "admin/course/info/"+courseId+".html",
				async : false,
				dataType : "json",
				success : function(data) {
					vm.showList = false;
	                vm.title = "修改";
	                vm.course = data.result;
				}
			});
			vm.getMenu();
		},
		del: function (event) {
			var courseIds = getSelectedRows();
			if(courseIds == null){
				return ;
			}
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: mainHttp + "admin/course/delete.html",
				    data: JSON.stringify(courseIds),
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
			});
		},
		saveOrUpdate: function (event) {
			var url = vm.course.uid == null ? mainHttp + "admin/course/save.html" : mainHttp + "admin/course/update.html";
			// 获取章节
			var node = ztree.getNodes();
		    var nodes = ztree.transformToArray(node);
		    Vue.set(vm.course, 'chapterList', nodes);
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.course),
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
		menuTree: function(){
			layer.open({
				type: 1,
				offset: '50px',
				skin: 'layui-layer-molv',
				title: "选择菜单",
				area: ['300px', '450px'],
				shade: 0,
				shadeClose: false,
				content: jQuery("#menuLayer"),
				btn: ['确定', '取消'],
				btn1: function (index) {
					var node = ztree.getSelectedNodes();
					//选择上级菜单
					vm.menu.parentId = node[0].uid;
					vm.menu.parentName = node[0].name;
					
					layer.close(index);
	            }
			});
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page
            }).trigger("reloadGrid");
		}
	}
});