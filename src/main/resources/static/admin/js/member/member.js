$(function () {
    $("#jqGrid").jqGrid({
        url: '../member/queryPage',
        datatype: "json",
        colModel: [			
			{ label: '用户名', name: 'userName', width: 45, key: true },
			{ label: '会员等级', name: 'groupName', width: 45 },
			{ label: '邮箱', name: 'email', width: 45 },
			{ label: 'QQ', name: 'oicq', width: 45 },
			{ label: '金币', name: 'coin', width: 45 },
			{ label: '性别', name: 'gender', width: 45, formatter: function(value, options, row){return value === 0 ? '男' : '女';}},
			{ label: '注册时间', name: 'regTime', width: 45, formatter:'date', formatoptions: { srcformat: 'u', newformat: 'Y-m-d H:i:s' }},			
			{ label: '最后登录时间', name: 'lastLoginTime', width: 45, formatter:'date', formatoptions: { srcformat: 'u', newformat: 'Y-m-d H:i:s' }},		
			{ label: '最后登录IP', name: 'lastLoginIp' , width: 45},	
			{ label: '状态', name: 'status', width: 45 ,formatter: function(value, options, row){return value === 0 ? '<span class="label label-success">正常</span>' : '<span class="label label-danger">限制登录</span>';}}
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
		title:null
	},
	methods: {
		query: function () {
			vm.reload();
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData:{'userName': vm.q.userName},
                page:page
            }).trigger("reloadGrid");
		}
	}
});