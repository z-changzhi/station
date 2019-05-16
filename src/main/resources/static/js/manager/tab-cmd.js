// 发送 开机/关机 命令的代码
var bdzId = getQueryString('bdzId');

// 设置开关机的 url
var setPowerUrl = "/station/control/setpower";

function member_start(obj, id) {
	layer.confirm('确认要开机吗？', function(index) {
		layer.msg('请等待', {
			icon: 4,
			time: 1000
		});
		//发异步开机命令
		$.ajax({
			url: setPowerUrl,
			type: "post",
			data: {
				"bdz_id": bdzId,
				"device_id": id,
				"control_type_id": 1,
				"control_code": 1
			},
			dataType: "json",
			async: true, // 异步开始 不然请求不到会卡死
			cache: false,
			success: function(data) {
				if(data.success) {
					layer.msg('已开机!', {
						icon: 1,
						time: 1000
					});
				}
			},
			error: function(data, error) {
				layer.msg(error, {
					icon: 2,
					time: 1000
				});
			}
		});
	});
}

function member_stop(obj, id) {
	layer.confirm('确认要关机吗？', function(index) {
		layer.msg('请等待', {
			icon: 4,
			time: 1000
		});
		$.ajax({
			url: setPowerUrl,
			type: "post",
			data: {
				"bdz_id": bdzId,
				"device_id": id,
				"control_type_id": 1,
				"control_code": 0
			},
			dataType: "json",
			cache: false,
			success: function(data) {
				if(data.success) {
					layer.msg('已关机!', {
						icon: 1,
						time: 1000
					});
				}
			},
			error: function(data, error) {
				layer.msg(error, {
					icon: 2,
					time: 1000
				});
			}
		});
	});
}