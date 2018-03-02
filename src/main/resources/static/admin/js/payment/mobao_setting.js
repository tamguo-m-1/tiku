var setting = {
	data: {
		simpleData: {
			enable: true,
			idKey: "menuId",
			pIdKey: "menuParent",
			rootPId: -1
		},
		key: {
			url:"nourl",
			name: "menuNm"
		}
	},
	check:{
		enable:true,
		nocheckInherit:true
	}
};

var vm = new Vue({
	el:'#rrapp',
	data:{
		title:"设置",
		settings:{
			attribute:{}
		}
	},
	mounted: function () {
		// 获取信息
	    var url = "getMobaoPayment.html?pluginId=mobaopayPaymentPlugin";
		$.ajax({
			type: "POST",
		    url: url,
		    success: function(r){
		    	if(r.code === 0){
		    		var result = {pluginId:"mobaopayPaymentPlugin",siteUrl:r.result.siteUrl,orders:r.result.order,isEnabled:r.result.isEnabled,author:r.result.author,version:r.result.version,attribute:
		    			{platformId:$.parseJSON(r.result.pluginConfig.attributes).platformId,merchantAcc:$.parseJSON(r.result.pluginConfig.attributes).merchantAcc,
		    			key:$.parseJSON(r.result.pluginConfig.attributes).key,fee:$.parseJSON(r.result.pluginConfig.attributes).fee,feeType:$.parseJSON(r.result.pluginConfig.attributes).feeType}};
		    		vm.settings = result;
				}else{
					alert(r.message);
				}
			}
		});
	},
	methods: {
		saveOrUpdate: function (event) {
			var url = "updateMobaoPayment.html";
			$.ajax({
				type: "POST",
			    url: url,
			    data: JSON.stringify(vm.settings),
			    success: function(r){
			    	if(r.code === 0){
						alert('操作成功', function(index){
							vm.back();
						});
					}else{
						alert(r.message);
					}
				}
			});
		},
		back : function(){
			history.go(-1);
		}
	}
});