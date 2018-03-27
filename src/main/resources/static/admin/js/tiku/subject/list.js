$(function () {
    $("#jqGrid").jqGrid({
        url: mainHttp + 'admin/subject/list.html',
        datatype: "json",
        colModel: [		
            { label: '考试ID', name: 'uid', width: 45, key: true },       	
			{ label: '考试名称', name: 'name', width: 45},
			{ label: '主科目', name: 'courseName', width: 45 }
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
			name: null
		},
		showList: true,
		title:null,
		subject:{
			courseList:[]
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		reload: function (event) {
			vm.showList = true;
			var page = $("#jqGrid").jqGrid('getGridParam','page');
			$("#jqGrid").jqGrid('setGridParam',{ 
                postData:{'name': vm.q.name},
                page:page
            }).trigger("reloadGrid");
		},
		add: function(event){
			vm.showList = false;
			vm.title = "新增";
			vm.subject = {};
		},
		edit:function(event){
			var subjectId = getSelectedRow();
			if(subjectId == null){
				return ;
			}
			
			vm.showList = false;
            vm.title = "修改";
			
			vm.getSubject(subjectId);
		},
		del:function(event){
			var subjectIds = getSelectedRows();
			if(subjectIds == null){
				return ;
			}
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: mainHttp + "admin/subject/delete.html",
				    data: JSON.stringify(subjectIds),
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
		saveOrUpdate:function(event){
			var url = vm.subject.uid == null ? mainHttp + "admin/subject/save.html" : mainHttp + "admin/subject/update.html";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.subject),
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
		getSubject:function(subjectId){
			$.ajax({
				type : "get", 
				url : mainHttp + "admin/subject/find/"+subjectId+".html",
				async : true,
				dataType : "json",
				success : function(data) {// 返回数据根据结果进行相应的处理,无论请求成功还是失败，都会走这个方法的
					vm.subject = data.result;
				}
			});
		}
	}
});