$(function () {
    $("#jqGrid").jqGrid({
        url: '../payment/getPaymentConfigList.html',
        datatype: "json",
        colModel: [			
			{ label: '插件名称', name: 'name', width: 45, key: true },
			{ label: '插件ID', name: 'id', width: 45, key: true },
			{ label: '插件版本', name: 'version', width: 45 },
			{ label: '插件作者', name: 'author', width: 45 },
			{ label: '是否启用', name: 'isEnabled', width: 45 },
			{ label: 'settingUrl', name: 'settingUrl', width: 45 }
		],
		viewrecords: true,
        height: 385,
        rowNum: 10,
		rowList : [10,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: true,
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
                page:page
            }).trigger("reloadGrid");
		},
		settings:function(){
			var grid = $("#jqGrid");
		    var rowKey = grid.getGridParam("selrow");
		    if(!rowKey){
		    	alert("请选择一条记录");
		    	return ;
		    }
		    
		    var selectedIDs = grid.getGridParam("selarrrow");
		    if(selectedIDs.length > 1){
		    	alert("只能选择一条记录");
		    	return ;
		    }
		    var rowData = $("#jqGrid").jqGrid('getRowData',selectedIDs[0]);
		    window.location.href = rowData.settingUrl;
		}
	}
});