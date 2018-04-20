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
			vue.question.answer = daanUE.getContent();
			vue.question.analysis = daanjiexiUE.getContent();
			$.ajax({
				type: "POST",
			    url: mainHttp + "member/submitQuestion",
			    data: vue.question,
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
						//	vm.reload();
						});
					}else{
						alert(r.message);
					}
				}
			});
		}
	}
});

window.tiganUE =  UE.getEditor('tigan',{
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
    initialFrameHeight:130,
});

window.daanUE =  UE.getEditor('daan',{
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
})

window.daanjiexiUE =  UE.getEditor('daanjiexi',{
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
})