// Vue
var vue = new Vue({
	el:'.newsRight',
	data:{
		
	},
	methods: {
		updateMember:function(){
			var member = {};
			member.username = $("input[name='username']").val();
			member.email = $("input[name='email']").val();
			member.mobile = $("input[name='mobile']").val();
			$.ajax({
				type: "POST",
			    url: mainHttp + "member/account/update.html",
			    data: member,
			    dataType:"json",
			    success: function(r){
			    	if(r.code === 0){
			    		layer.alert('操作成功', function(index){
			    			window.location.reload();
						});
					}else{
						layer.alert(r.message);
					}
				}
			});
		}
	}
});