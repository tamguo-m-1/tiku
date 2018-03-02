$(function () {
    $("#jqGrid").jqGrid({
        url: '../video/newsVideoList',
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
        rowNum: 8,
		rowList : [8,30,50],
        rownumbers: true, 
        rownumWidth: 25, 
        autowidth:true,
        multiselect: false,
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
		title:null
	},
	methods: {
		create: function () {
			$.get("../video/refreshNewsVideo", function(r){
				if(r.code == 0){
					alert('操作成功', function(index){
						$("#jqGrid").trigger("reloadGrid");
					});
				}else{
					alert(r.message);
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