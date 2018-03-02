$(function () {
    $("#jqGrid").jqGrid({
        url: '../sysMenuInf/list',
        datatype: "json",
        colModel: [			
			{ label: '目录ID', name: 'menuId', width: 40, key: true },
			{ label: '目录名称', name: 'menuNm', width: 60 },
			{ label: '目录排序', name: 'menuSort', width: 60 },
			{ label: '创建人', name: 'rowCrtUsr', width: 60 },
			{ label: '修改人', name: 'recUpdUsr', width: 60 }
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
			name:"menuNm"
		}
	}
};
var ztree;
var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			menuNm: null
		},
		showList: true,
		title: null,
		menuInf:{
			menuParentName:null,
			menuParent:0,
			type:1,
			orderNum:0
		}
	},
	methods: {
		query : function(){
			vm.reload();
		},
		getMenu: function(menuId){
			//加载菜单树
			$.get("../sysMenuInf/select", function(r){
				ztree = $.fn.zTree.init($("#menuTree"), setting, r.result);
				console.log(vm.menuInf.menuParent);
				var node = ztree.getNodeByParam("menuId", vm.menuInf.menuParent);
				ztree.selectNode(node);
				vm.menuInf.menuParentName = node.name;
			})
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.menuInf = {menuParentName:null,menuParent:0,menuSort:0};
			vm.getMenu();
		},
		update: function (event) {
			var menuId = getSelectedRow();
			if(menuId == null){
				return ;
			}
			
			$.get("../sysMenuInf/info/"+menuId, function(r){
				vm.showList = false;
                vm.title = "修改";
                vm.menuInf = r.result;
            });
			
			vm.getMenu();
		},
		del: function (event) {
			var menuIds = getSelectedRows();
			if(menuIds == null){
				return ;
			}
			
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: "../sysMenuInf/delete",
				    data: JSON.stringify(menuIds),
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
			var url = vm.menuInf.menuId == null ? "../sysMenuInf/save" : "../sysMenuInf/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.menuInf),
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
					vm.menuInf.menuParent = node[0].menuId;
					vm.menuInf.menuParentName = node[0].menuNm;
					
					layer.close(index);
	            }
			});
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                page:page,
                postData:{'menuNm': vm.q.menuNm},
            }).trigger("reloadGrid");
		}
	}
});