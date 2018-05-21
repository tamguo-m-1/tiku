$(function () {
    $("#jqGrid").jqGrid({
        url: mainHttp + 'admin/question/list.html',
        datatype: "json",
        colModel: [			
            { label: 'ID', name: 'uid', width: 25, key: true },
			{ label: '题目类型', name: 'questionType', width: 25,formatter: function(value, options, row){
				if(value === "1"){
					return '<span>单选题</span>';
				}else if(value === "2"){
					return '<span>多选题</span>';
				}else if(value === "3"){
					return '<span>判断题</span>';
				}else if(value === "4"){
					return '<span>填空题</span>';
				}else if(value === "5"){
					return '<span>简答题</span>';
				}
			}},
			{ label: '科目', name: 'courseName', width: 25 },
			{ label: '章节', name: 'chapterName', width: 25 },
			{ label: '试卷', name: 'paperId', width: 25 },
			{ label: '题内容', name: 'content', width: 120 , hidden:true},
			{ label: '答案', name: 'answer', width: 100  , hidden:true},
			{ label: '解析', name: 'analysis', width: 50 , hidden:true},
			{ label: '考察知识点', name: 'reviewPoint', width: 50 , hidden:true}, 
			{ label: '年份', name: 'year', width: 50, hidden:true}, 
			{ label: '分数', name: 'score', width: 50 , hidden:true}  
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
		question:{
		}
	},
	methods: {
		getSubjectList:function(){
			return axios.get(mainHttp + "admin/subject/getSubject.html");
		},
		getCouseList: function(){
			return axios.get(mainHttp + "admin/course/findBySubjectId.html?subjectId="+vm.question.subjectId);
		},
		changeSubject:function(){
			axios.all([this.getCouseList()]).then(axios.spread(function (cResponse) {
				vm.courseList = cResponse.data.result;
            }));
		},
		query: function () {
			vm.reload();
		},
		add: function(){
			vm.showList = false;
			vm.title = "新增";
			vm.question = {courseId:null,schoolId:null,areaId:null,name:null,questionInfo:null,type:null,year:null,downHits:null,openHits:null};
			
			axios.all([vm.getSubjectList()]).then(axios.spread(function (sResponse) {
				vm.subjectList = sResponse.data.result;
            }));
			vm.reloadUeditor();
		},
		update: function (event) {
			var questionId = getSelectedRow();
			if(questionId == null){
				return ;
			}
			$.ajax({
				type : "get", 
				url : mainHttp + "admin/question/info/"+questionId+".html",
				async : false,
				dataType : "json",
				success : function(data) {
					vm.showList = false;
	                vm.title = "修改";
	                vm.question = data.result;
	                
	                vm.reloadUeditor();
				}
			});
		},
		del: function (event) {
			var questionIds = getSelectedRows();
			if(questionIds == null){
				return ;
			}
			confirm('确定要删除选中的记录？', function(){
				$.ajax({
					type: "POST",
				    url: mainHttp + "admin/question/delete.html",
				    data: JSON.stringify(questionIds),
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
		// 加载富文本框
		reloadUeditor:function(event){
			window.tiganUE =  UE.getEditor('tiganEditor',{
                //这里可以选择自己需要的工具按钮名称,此处仅选择如下五个
                toolbars: [
                    [ 'source','undo', 'redo','fontfamily', 'fontsize','|', 'forecolor', 'backcolor','pasteplain', '|',
                        'bold', 'italic', 'underline', 'fontborder','|','justifyleft', 'justifycenter', 'justifyright', 'justifyjustify','|', 'strikethrough', 'superscript', 'subscript', 'removeformat','|', 'insertorderedlist', 'insertunorderedlist','lineheight', '|',
                        'link', 'unlink','|',
                        'simpleupload','imagefloat', 'emotion', 'insertvideo', 'insertaudio', 'attachment','insertframe',
                        'horizontal', '|',
                        'inserttable', 'deletetable', 'insertparagraphbeforetable', 'insertrow', 'deleterow', 'insertcol', 'deletecol','|','insertcode',]
                ],
                //关闭字数统计
                wordCount:false,
                autoFloatEnabled:false,
                //关闭elementPath
                elementPathEnabled:false,
                //默认的编辑区域高度
                initialFrameHeight:120,
           });
           window.daanUE =  UE.getEditor('daanEditor',{
                //这里可以选择自己需要的工具按钮名称,此处仅选择如下五个
                toolbars: [
                    ['fontfamily', 'fontsize','|', 'forecolor', 'backcolor','pasteplain', '|',  'bold', 'italic', 'underline', 'fontborder','|', 'link', 'unlink','|', 'simpleupload','imagefloat', 'insertcode',]
                ],
                //focus时自动清空初始化时的内容
                autoClearinitialContent:true,
                //关闭字数统计
                wordCount:false,
                //关闭elementPath
                elementPathEnabled:false,
                autoFloatEnabled:false,
                //默认的编辑区域高度
                initialFrameHeight:80,

            });
            window.jiexiUE =  UE.getEditor('jiexiEditor',{
                //这里可以选择自己需要的工具按钮名称,此处仅选择如下五个
                toolbars: [
                    ['fontfamily', 'fontsize','|', 'forecolor', 'backcolor','pasteplain', '|',  'bold', 'italic', 'underline', 'fontborder','|', 'link', 'unlink','|', 'simpleupload','imagefloat', 'insertcode',]
                ],
                //focus时自动清空初始化时的内容
                autoClearinitialContent:true,
                //关闭字数统计
                wordCount:false,
                //关闭elementPath
                elementPathEnabled:false,
                autoFloatEnabled:false,
                //默认的编辑区域高度
                initialFrameHeight:80,
             });
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