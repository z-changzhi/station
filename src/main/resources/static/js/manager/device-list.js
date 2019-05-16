layui.use('laydate', function() {
	var laydate = layui.laydate;

	//执行一个laydate实例
	laydate.render({
		elem: '#start' //指定元素
	});

	//执行一个laydate实例
	laydate.render({
		elem: '#end' //指定元素
	});
});
// 设备台账 - 变电站管理
// 设备台账 - 变电站管理
// 设备台账 - 变电站管理

var getReportUrl = "/station/report/list";
//var getDevicelistUrl = "/station/device/list";
var getDeviceUrl = "/station/device/listByRegion";
get_device(1);
get_devices("-1");

layui.use(['form', 'layer'], function() {
	$ = layui.jquery;
	var form = layui.form,
		layer = layui.layer;
	form.on('submit(add)', function(data) {
		$.ajax({
			url: getDeviceUrl,
			type: 'get',
			data: {
				//"province": data.field.province,
				"stationId": data.field.stationId
			},
			dataType: "json",
			cache: false,
			success: function(data) {
				if(data.success) {
					layer.msg('查询成功!', {
						icon: 1,
						time: 1000
					});
					setErgodicHtml(data.bdzlist);
				} else {
					layer.msg('查询失败!', {
						icon: 1,
						time: 1000
					});
				}
			}
		});
		return false;
	});
});

function get_devices(stationId) {
	$.ajax({
		url: getDeviceUrl,
		type: 'get',
		data: {
			//"province": data.field.province,
			"stationId": stationId
		},
		dataType: "json",
		cache: false,
		success: function(data) {
			if(data.success) {
				layer.msg('查询成功!', {
					icon: 1,
					time: 1000
				});
				setErgodicHtml(data.bdzlist);
			} else {
				layer.msg('查询失败!', {
					icon: 1,
					time: 1000
				});
			}
		}
	});
}

function setErgodicHtml(data) {
	var html = "";
	data.map(function(item, index) {
		html +=
			'<tr onclick="get_device(' +
			item.id + ')"><td class="id">' +
			item.id + '</td><td>' +
			getType(item.typeId) + '</td><td>' +
			item.stationId + '</td><td>' +
			item.roomId + '</td><td>' +
			item.positionId + '</td><td class="td-status">' +
			getStatus(item.status) + '<td class = "td-manage" ><a title = "编辑"class = "layui-btn layui-btn-xs layui-btn-normal" onclick = "x_admin_show(\'编辑\',\'member-edit.html\',600,400)" href = "javascript:;"> 编辑 </a>' +
			'<a title="删除" class="layui-btn layui-btn-xs layui-btn-danger" onclick="member_del(this,\'要删除的id\')" href="javascript:;">删除</a></td></tr>';
	});
	console.log(html)
	$('#device-list').html(html);
}

/* 获取设备id */
/* 获取设备id */
/* 获取设备id */
function get_device(id) {
	console.log(id);

	$.ajax({
		url: getReportUrl,
		type: 'get',
		data: {
			"deviceId": id
		},
		dataType: "json",
		cache: false,
		success: function(data) {
			if(data.success) {

				setReportHtml(data.bdzlist);
			}
		},
		error: function(data, error) {
			layer.alert("获取详情失败", {
				icon: 2
			});
		}
	});

}
var date = new Date();

function setReportHtml(data) {
	//console.log(data.bdzlist);
	data.map(function(item, index) {
		date.setTime(item.reportTime);
		$('#device-report-id').html(item.id+"");
		$('#device-report-deviceId').html(item.deviceId+"");
		$('#device-report-reportTime').html(date.toLocaleString()+"");
		$('#device-report-setPower').html(item.setPower+"");
		$('#device-report-setTimer').html(item.setTimer+"");
		$('#device-report-setTempe').html(item.setTempe+"");
		$('#device-report-setHumid').html(item.setHumid+"");
		$('#device-report-tempeEnv').html(item.tempeEnv+"");
		$('#device-report-humidEnv').html(item.humidEnv+"");
	});

}

/*用户-停用*/
function member_stop(obj, id) {
	layer.confirm('确认要停用吗？', function(index) {

		if($(obj).attr('title') == '启用') {

			//发异步把用户状态进行更改
			$(obj).attr('title', '停用')
			$(obj).find('i').html('&#xe62f;');

			$(obj).parents("tr").find(".td-status").find('span').addClass('layui-btn-disabled').html('已停用');
			layer.msg('已停用!', {
				icon: 5,
				time: 1000
			});

		} else {
			$(obj).attr('title', '启用')
			$(obj).find('i').html('&#xe601;');

			$(obj).parents("tr").find(".td-status").find('span').removeClass('layui-btn-disabled').html('已启用');
			layer.msg('已启用!', {
				icon: 5,
				time: 1000
			});
		}

	});
}

/*用户-删除*/
function member_del(obj, id) {
	console.log(obj);
	layer.confirm('确认要删除吗？', function(index) {
		//发异步删除数据
		$(obj).parents("tr").remove();
		layer.msg('已删除!', {
			icon: 1,
			time: 1000
		});
	});
}

function delAll(argument) {

	var data = tableCheck.getData();

	layer.confirm('确认要删除吗？' + data, function(index) {
		//捉到所有被选中的，发异步进行删除
		layer.msg('删除成功', {
			icon: 1
		});
		$(".layui-form-checked").not('.header').parents('tr').remove();
	});
}




	// 获取设备类型
	function getType(name) {
		var type = "";
		switch(name) {
			case 1:
				type = "除湿机";
				break;
			case 2:
				type = "空调";
				break;
			default:
				type = "类型错误";
				break;
		}
		return type;
	}

	// 获取设备状态
	function getStatus(name) {
		var type = "";
		switch(name) {
			case 0:
				type = "关机";
				break;
			case 1:
				type = "开机";
				break;
			case 2:
				type = "异常";
				break;
		}
		return type;
	}