$(function () {
    $("#jqGrid").jqGrid({
        url: '../memberGroup/queryPage',
        datatype: "json",
        colModel: [			
            { label: '等级ID', name: 'uid', width: 45, key: true },
			{ label: '等级名称', name: 'groupName', width: 45},
			{ label: '赠送色币', name: 'presentCoin', width: 45 },
			{ label: '所需金钱', name: 'needMoney', width: 45 },
			{ label: '过期天数', name: 'expireDay', width: 45 },
			{ label: '状态', name: 'status', width: 45 ,formatter: function(value, options, row){return value === 0 ? '<span class="label label-success">正常</span>' : '<span class="label label-danger">停用</span>';}},
			{ label: '类型', name: 'type', width: 45 ,formatter: function(value, options, row){return value === 0 ? '<span class="label label-success">普通</span>' : '<span class="label label-danger">收费</span>';}}
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
var vm = new Vue({
	el:'#rrapp',
	data:{
		q:{
			userName: null
		},
		showList: true,
		title:null,
		memberGroup:{
			status:0,
			type:0
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			memberGroup:{status:0};
		},
		update: function () {
			var uid = getSelectedRow();
			if(uid == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
            
            vm.getMemberGroup(uid);
		},
		saveOrUpdate: function (event) {
			var url = vm.memberGroup.uid == null ? "../memberGroup/save" : "../memberGroup/update";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.memberGroup),
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
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData:{'groupName': vm.q.groupName},
                page:page
            }).trigger("reloadGrid");
		},
		getMemberGroup: function(uid){
			$.get("../memberGroup/info/"+uid, function(r){
				vm.memberGroup = r.result;
			});
		}
	}
});