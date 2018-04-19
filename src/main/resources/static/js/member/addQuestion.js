// Vue
var vue = new Vue({
	el:'#container',
	data:{
		questionTypes:[{id:'1',name:'单选题'},{id:'2',name:'多选项'},{id:'3',name:'判断题'},{id:'4',name:'填空题'},{id:'5',name:'简答题'}],
		answerSingleSeleList:[{id:1,value:''},{id:2,value:''},{id:3,value:''},{id:4,value:''}],
		answerMultiSeleList:[{id:1,value:''},{id:2,value:''},{id:3,value:''},{id:4,value:''}],
		singleSele:null,
		multiSele:null,
		singleSeleIndex:5,
		multiSeleIndex:5,
		question:{
			questionType:1,
			year:null,
			score:null,
			reviewPoint:null,
			content:null,
			answer:null,
			analysis:null
		}
	},
	methods: {
		changeQuestionType:function(type){
			vue.question.questionType = type;
		},
		// 添加选项
		addSingleSeleList:function(){
			vue.answerSingleSeleList.push({id:vue.singleSeleIndex,value:""});
			vue.singleSeleIndex++;
		},
		deleSingleSeleList:function(index,id){
			vue.answerSingleSeleList.splice(index,1);
			if(vue.singleSele == id){
				singleSele = null;
			}
		},
		// 添加项目
		addMultiSelect:function(){
			vue.answerMultiSeleList.push({id:vue.multiSeleIndex,value:""});
			vue.multiSeleIndex++;
		},
		deleMultiSeleList:function(index,id){
			vue.answerMultiSeleList.splice(index,1);
			if(vue.multiSele == id){
				multiSele = null;
			}
		},
		// 保存题目
		submitQuestion:function(){
			vue.question.content = tiganUE.getContent();
			vue.question.answer = daanjiexiUE.getContent();
			vue.question.analysis = daanjiexiUE.getContent();
			vue.question.fallibility = daanjiexiUE.getContent();
			$.ajax({
				type: "POST",
			    url: mainHttp + "member/submitQuestion",
			    data: vue.question,
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
	},
	mounted:function(){
		
	}
});