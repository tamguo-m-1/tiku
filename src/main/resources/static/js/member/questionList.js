layui.use(['laypage', 'layer'], function(){
	  var laypage = layui.laypage,layer = layui.layer;
	  laypage.render({elem: 'pagination',count: vm.totalCount , limit: 2 , layout: ['count', 'prev', 'page', 'next', 'skip'],
		  jump: function(obj){
			  vm.currPage = obj.curr;
			  vm.queryQuestionList();
		  }
	  });
});
var vm = new Vue({
	el:'#container',
	data:{
		totalCount:null,
		currPage:1,
		questionTypes:[{id:'1',name:'单选题'},{id:'2',name:'多选项'},{id:'3',name:'判断题'},{id:'4',name:'填空题'},{id:'5',name:'简答题'}],
		q:{
			questionType:null,
			reviewPoint:null,
			uid:null
		},
		questionList:null
	},
	methods: {
		queryQuestionList:function(){
			$.ajax({
				type: "POST",
			    url: mainHttp + "member/queryQuestionList.html",
			    data: {questionType:vm.q.questionType,reviewPoint:vm.q.reviewPoint,uid:vm.q.reviewPoint,
			    	page:vm.currPage,limit:2},
			    dataType:"json",
			    success: function(r){
			    	vm.questionList = r.list;
			    	vm.totalCount = r.totalCount;
				}
			});
		}
	}
});
vm.queryQuestionList();
