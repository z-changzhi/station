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

//var getRegionMenuUrl = "/station/report/list";
// 请求下拉列表   遇到一个回显错误的BUG
var getRegionMenuUrl = '/station/region/list';
// 获取:设备列表 用于:表格展示
//var getDevicelistUrl = "/station/device/list";

// get:变电站列表 用于 设备台账-变电站列表数据显示
// 参数: 省份 市
// 返回: 变电站json
var getStationUrl = "/station/station/listByRegion";

// 获取:设备列表 用于:表格展示
var getDeviceUrl = "/station/device/list";

// 默认 显示第一个设备的信息
get_device(1);
// 默认 显示 南昌市的所有变电站
get_station("*");

// 获取所有的市区 并回显在 下拉菜单上

//监听提交
//监听提交
//监听提交
layui.use(['form', 'layer'], function() {
	$ = layui.jquery;
	var form = layui.form,
		layer = layui.layer;
	form.on('submit(add)', function(data) {
		$.ajax({
			url: getStationUrl,
			type: 'post',
			data: {
				//"province": data.field.province,
				"region": data.field.region
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
	
	
	getRegionMenu("江西省");

	function getRegionMenu(province) {
		$.ajax({
			url: getRegionMenuUrl,
			type: 'get',
			cache: false,
			success: function(data) {
				if(data.success) {
					// 将其 append 在下拉菜单上  #region
					var html = '<option value="*">查询所有</option>';
					data.bdzlist.map(function(item, index) {
						html +=
							'<option value="' + item.region +
							'">' + item.region + '</option>';
					});
					console.log(html);
					$('#station-region').html(html);
					/*console.log($('#region'));*/
					form.render('select');
				}
			},
			error: function(data, error) {
				layer.alert("获取详情失败", {
					icon: 2
				});
			}
		});
	}
});
// 默认显示 第一个设备

function get_station(data) {
	$.ajax({
		url: getStationUrl,
		type: 'post',
		data: {
			//"province": data.field.province,
			"region": data
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
			item.name + '</td><td>' +
			item.address + '</td><td>' +
			item.region + '</td><td class="td-status">' +
			item.province + '<td class = "td-manage" ><a title = "编辑"class = "layui-btn layui-btn-xs layui-btn-normal" onclick = "x_admin_show(\'编辑\',\'member-edit.html\',600,400)" href = "javascript:;"> 编辑 </a>' +
			'<a title="删除" class="layui-btn layui-btn-xs layui-btn-danger" onclick="member_del(this,\'要删除的id\')" href="javascript:;">删除</a></td></tr>';
	});
	console.log(html)
	$('#station-list').html(html);
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

/* 获取设备id */
function get_device(id) {
	//	console.log(id);

	$.ajax({
		url: getDeviceUrl,
		type: 'get',
		data: {
			"stationId": id
		},
		dataType: "json",
		cache: false,
		success: function(data) {
			if(data.success) {
				//				console.log(data);
				setDeviceHtml(data.bdzlist);
			}
		},
		error: function(data, error) {
			layer.alert("获取设备列表失败", {
				icon: 2
			});
		}
	});

}

function setDeviceHtml(data) {
	//console.log(data.bdzlist);

	var html = "<tr><td>设备编号</td><td>设备类型</td><td>设备状态</td></tr>";
	data.map(function(item, index) {
		html += '<tr><td>' + item.id + '</td>' +
			'<td>' + getType(item.typeId) + '</td>' +
			'<td>' + getStatus(item.status) + '</td>' +
			'</tr>';
	});
	$('#device-Device-list').html(html);
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