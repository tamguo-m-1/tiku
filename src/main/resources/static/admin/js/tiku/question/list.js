$(function () {
    $("#jqGrid").jqGrid({
        url: mainHttp + 'admin/question/list.html',
        datatype: "json",
        colModel: [			
			{ label: '题目类型', name: 'questionType', width: 25, key: true },
			{ label: '考试ID', name: 'subjectId', width: 25 },
			{ label: '章节ID', name: 'chapterId', width: 25 },
			{ label: '试卷ID', name: 'paperId', width: 25 },
			{ label: '题内容', name: 'content', width: 120 },
			{ label: '答案', name: 'answer', width: 100 },
			{ label: '解析', name: 'analysis', width: 50},
			{ label: '易错点', name: 'fallibility', width: 50},
			{ label: '考察知识点', name: 'reviewPoint', width: 50}, 
			{ label: '年份', name: 'year', width: 50}, 
			{ label: '分数', name: 'score', width: 50}  
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
		title: null,
		paper:{
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.question = {courseId:null,schoolId:null,areaId:null,name:null,questionInfo:null,type:null,year:null,downHits:null,openHits:null};
		},
		update: function (event) {
			var paperId = getSelectedRow();
			if(paperId == null){
				return ;
			}
			$.ajax({
				type : "get", 
				url : mainHttp + "admin/paper/info/"+paperId+".html",
				async : false,
				dataType : "json",
				success : function(data) {
					vm.showList = false;
	                vm.title = "修改";
	                vm.paper = data.result;
				}
			});
		},
		del: function (event) {
			var paperIds = getSelectedRows();
			if(paperIds == null){
				return ;
			}
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: mainHttp + "admin/paper/delete.html",
				    data: JSON.stringify(paperIds),
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
			var url = vm.paper.uid == null ? mainHttp + "admin/paper/save.html" : mainHttp + "admin/paper/update.html";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.paper),
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
				postData:{'name': vm.q.name},
                page:page
            }).trigger("reloadGrid");
		}
	}
});