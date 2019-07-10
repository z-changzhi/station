// 获取变电站 + 设备状态的url
var bdzDeviceUrl = "/station/region/bdz/list";

var series = [{
		// 地图数据列
		type: 'map',
		mapData: Highcharts.maps["cn/china"],
		joinBy: 'hc-key',
		name: '随机数据',
		states: {
			hover: {
				color: '#a4edba'
			}
		},
		dataLabels: {
			enabled: true,
			format: '{point.properties.name}'
		},
		minSize: 4,
		maxSize: '12%',
		showInLegend: false
	}],
	lastLevel = null;

// 这里用 JSONP 跨域访问
$.getJSON(bdzDeviceUrl, function(data) {

	series.push({
		name: "关机", // 设置名字
		visible: series.length < 4,
		data: [],
	});

	series.push({
		name: "正常", // 设置名字
		visible: series.length < 4,
		data: []
	});
	series.push({
		name: "异常", // 设置名字
		visible: series.length < 4,
		data: []
	});

	// 统计设备 状态的代码
	// 统计设备 状态的代码
	// 统计设备 状态的代码
	var total_abnormal = 0;// 计算 整个省的正常设备
	var total_normal = 0;
	var total_shutdown = 0;
	for(var bdz in data.bdzlist) {
		var b = data.bdzlist[bdz];
		var abnormal = 0;
		var normal = 0;
		var shutdown = 0;
		b["shutDown"] = 0;
		b["abNormal"] = 0;
		b["Normal"] = 0;
		for(var itemdevices in b.devices) {
			var d = b.devices[itemdevices];
			//console.log(d);
			if(d.status === 2) { //判断设备有没有异常
				abnormal++;
				console.log(d);
				var txt1 =  '<tr><td>'+ d.id+'</td>'+
							    '<td>'+ d.positionId+'</td>'+
								'<td>'+ getType(d.typeId)+'</td>'+
								'<td>'+ getStatus(d.status)+'</td></tr>';
				$("#abnormal-list").append(txt1);
			}
			if(d.status === 1) { //判断设备有没有 正常
				normal++;
			}
			if(d.status === 0) { //判断设备有没有 关机
				shutdown++;
				
			}

		}

		if(shutdown !== 0) { //不等于0 表示 有
			b.shutDown = shutdown;
			b.abNormal = abnormal;
			b.Normal = normal;
			series[1].data.push({
				name: b.name,
				properties: b,
				x: b.x,
				y: -b.y,
				color: 'black',
			});
		} else if(abnormal !== 0) { // 不等于0 表示有
			b.abNormal = abnormal;
			b.Normal = normal;
			series[3].data.push({
				name: b.name,
				properties: b,
				x: b.x,
				y: -b.y,
				color: 'red',
			});
		} else if(normal !== 0) { // 上面都不满足 显示正常
			b.Normal = normal;
			series[2].data.push({
				name: b.name,
				properties: b,
				x: b.x,
				y: -b.y,
				color: 'green',
			});
		}
		total_abnormal+=abnormal;
		total_normal+=normal;
		total_shutdown+=shutdown;
		
	}
	$('#abnormal-device').html(total_abnormal);
	$('#normal-device').html(total_normal);
	$('#shutdown-device').html(total_shutdown);
	// 显示左下角角标
	// 显示左下角角标
	// 显示左下角角标
	for(var i = 1; i < series.length; i++) {
		series[i].name += '（' + series[i].data.length + ' 个）';
	}

	//触发事件
	var tab = {
		tabAdd: function(title, url, id) {
			//新增一个Tab项
			parent.element.tabAdd('xbs_tab', {
				title: title,
				content: '<iframe tab-id="' + id + '" frameborder="0" src="' + url + '" scrolling="yes" class="x-iframe"></iframe>',
				id: id
			})
		},
		tabDelete: function(othis) {
			//删除指定Tab项
			parent.element.tabDelete('xbs_tab', '44'); //删除：“商品管理”

			othis.addClass('layui-btn-disabled');
		},
		tabChange: function(id) {
			//切换到指定Tab项
			parent.element.tabChange('xbs_tab', id); //切换到：用户管理
		}
	};

	var map = new Highcharts.Map('container', {
		chart: {
			type: 'mappoint'
		},
		title: {
			text: '变电站分布'
		},
		subtitle: {
			text: '变电站分布：<a href="#">江西省</a>'
		},
		mapNavigation: {
			enabled: true,
			buttonOptions: {
				verticalAlign: 'bottom'
			}
		},
		legend: {
			layout: 'vertical',
			align: 'left',
			floating: true,
			x: 30
		},
		tooltip: {
			useHTML: true,
			headerFormat: '<span><a >{point.key}</a></span><table>',
			pointFormat:
				//'<tr><td>设备状态</td><td>{point.properties.name}</td></tr>' +
				'<tr><td><font  color="green">&nbsp;&nbsp;</font> 所属市区</td><td>{point.properties.region}{point.properties.address}</td></tr>' +
				'<tr><td><font  color="green">●</font> 正常设备</td><td>({point.properties.Normal})个</td></tr>' +
				'<tr><td><font  color="red">●</font> 异常设备</td><td>({point.properties.abNormal})个</td></tr>' +
				'<tr><td><font  color="black">●</font> 关机设备</td><td>({point.properties.shutDown})个</td></tr>',
			footerFormat: '</table>',

		},
		plotOptions: {
			series: {
				dataLabels: {
					enabled: false
				},
				marker: {
					radius: 3
				},
				cursor: 'pointer',
				events: {
					click: function(e) {
						//上面是当前页跳转，如果是要跳出新页面，那就用
						//location.href = "/demo2/html/tab.html?bdzId=" + e.point.properties.id; 
						//console.log(e.point);
						//parent.open('/demo2/html/tab.html?bdzId'+e.point.properties.id);
						var url = "/station/html/tab.html?bdzId=" + e.point.properties.id;
						var title = e.point.properties.name;
						//var index = e.point.properties.name; //防止打开重复的标签
						//index() 方法返回指定元素相对于其他指定元素的 index 位置。
						console.log(e.point.properties.id);
						var index = parseInt(e.point.properties.id);
						for(var i = 0; i < $('.x-iframe',parent.document).length; i++) {
							if(parseInt($('.x-iframe',parent.document).eq(i).attr('tab-id')) == index ) {// 发现我现在的id 弹出的id
								//console.log(parseInt($('.x-iframe',parent.document).eq(i).attr('tab-id')));
								tab.tabChange(index);
								return;
							}
						};
						tab.tabAdd(title, url, index);
						tab.tabChange(index);
					}
				}
			}
		},
		series: series
	});

});

function getStatus(name) {
	var type = "";
	switch(name) {
		case 0:
			type = "关机";
			break;
		case 1:
			type = "正常";
			break;
		case 2:
			type = "异常";
			break;
	}
	return type;
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
