$(function () {
    $("#jqGrid").jqGrid({
        url: '../admin/memberPopularize/queryPage',
        datatype: "json",
        colModel: [			
            { label: 'ID', name: 'uid', width: 45, key: true },
			{ label: '会员用户名', name: 'memberUserName', width: 45},
			{ label: '会员等级', name: 'groupName', width: 45 },
			{ label: '申请时间', name: 'createTime', width: 45},
			{ label: '状态', name: 'status', width: 45 ,formatter: function(value, options, row){
				if(value == 0){
					return '<span class="label label-info">申请中</span>';
				}else if(value == 1){
					return '<span class="label label-success">审核通过</span>';
				}else if(value == 2){
					return '<span class="label label-danger">审核拒绝</span>';
				}}}
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
			status: null
		},
		showList: true,
		title:null,
		popularize:{
			memberUserName:""
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		reload:function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData:{'memberUserName': vm.q.memberUserName},
                page:page
            }).trigger("reloadGrid");
		},
		audit:function(event) {
			var uid = getSelectedRow();
			if(uid == null){
				return ;
			}
			vm.showList = false;
			vm.title = '会员推广审核';
			vm.getMemberPopularize(uid);
		},
		getMemberPopularize: function(uid){
			$.get("../admin/memberPopularize/info/"+uid, function(r){
				vm.popularize = r.result;
			});
		},
		auditSubmit: function (event) {
			var url = "../admin/memberPopularize/audit.html";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.popularize),
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
		}
	}
});