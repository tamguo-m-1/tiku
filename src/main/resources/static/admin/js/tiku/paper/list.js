$(function () {
    $("#jqGrid").jqGrid({
        url: mainHttp + 'admin/paper/list.html',
        datatype: "json",
        colModel: [			
			{ label: 'ID', name: 'uid', width: 25, key: true , hidden:true},
			{ label: '科目', name: 'courseName', width: 25 },
			{ label: '学校', name: 'schoolName', width: 25 },
			{ label: '地区', name: 'areaName', width: 25 },
			{ label: '书卷标题', name: 'name', width: 120 },
			{ label: '类型', name: 'type', width: 50,formatter: function(value, options, row){
				if(value === "0"){
					return '<span>真题试卷</span>';
				}else if(value === "1"){
					return '<span>模拟试卷</span>';
				}else if(value === "2"){
					return '<span>押题预测</span>';
				}else if(value === "3"){
					return '<span>名校精品</span>';
				}
			}},
			{ label: '年份', name: 'year', width: 50},
			{ label: '下载数量', name: 'downHits', width: 50}, 
			{ label: '点击数量', name: 'openHits', width: 50}  
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
		subjectList:{},
		courseList:{},
		paper:{
			courseId:null,
			schoolId:null,
			areaId:null,
			name:null,
			questionInfo:null,
			type:null,
			year:null,
			downHits:null,
			openHits:null
		}
	},
	methods: {
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.paper = {courseId:null,schoolId:null,areaId:null,name:null,questionInfo:null,type:null,year:null,downHits:null,openHits:null};
			
			axios.all([vm.getSubjectList()]).then(axios.spread(function (sResponse) {
				vm.subjectList = sResponse.data.result;
            }));
		},
		update: function (event) {
			var paperId = getSelectedRow();
			if(paperId == null){
				return ;
			}
			vm.showList = false;
            vm.title = "修改";
			axios.all([vm.getSubjectList() , vm.getPaper(paperId)]).then(axios.spread(function (sResponse,pResponse) {
				vm.subjectList = sResponse.data.result;
				vm.paper = pResponse.data.result;
				
				// 获取科目
				axios.all([vm.getCouseList() , vm.getPaper(paperId)]).then(axios.spread(function (cResponse , pResponse) {
					vm.courseList = cResponse.data.result;
					vm.paper = pResponse.data.result;
	            }));
            }));
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
		},
		getSubjectList:function(){
			return axios.get(mainHttp + "admin/subject/getSubject.html");
		},
		getCouseList: function(){
			return axios.get(mainHttp + "admin/course/findBySubjectId.html?subjectId="+vm.paper.subjectId);
		},
		changeSubject:function(){
			axios.all([this.getCouseList()]).then(axios.spread(function (cResponse) {
				vm.courseList = cResponse.data.result;
            }));
		},
		getAreaList:function(){
			return axios.get(mainHttp + "admin/area/getArea.html");
		},
		getPaper:function(paperId){
			return axios.get(mainHttp + "admin/paper/info/"+paperId+".html");
		}
	},
	watch:{
		  // 数据修改时触发
	      subjectList: function() {
	        this.$nextTick(function(){
		        $('#subjectId').selectpicker('refresh');
	        })
	      },
	      courseList: function() {
	        this.$nextTick(function(){
		        $('#courseId').selectpicker('refresh');
	        })
	      }
	}
});