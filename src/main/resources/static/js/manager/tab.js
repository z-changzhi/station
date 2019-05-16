$(function() {
	/* 查询变电站-返回为列表 */
	var bdzDeviceUrl = "/station/region/bdz/list";
	// 采集端回显数据
	var collectorDataUrl = "/station/region/bdz/collectorData";
	// 采集端保存数据
	var collectorSaveUrl = "/station/region/bdz/collectorSave";
	// 变电站回显数据
	var bdzDataUrl = "/station/region/bdz/bdzData";
	// 变电站保存数据
	var bdzSaveUrl = "/station/region/bdz/bdzSave";

	function getlist(bdz) {
		// console.log(bdz);
		var getting = {
			url: bdzDeviceUrl,
			type: "get",
			dataType: "json",
			success: function(data) {
				if(data.success) {
					getChartList(data.bdzlist, bdz);
					deviceList(data.bdzlist, bdz);
					getCollector(bdz);
					getBdzData(bdz);

				}
			}
		};
		$.ajax(getting);
		window.setInterval(function() {
			$.ajax(getting)
		}, 15000); //ajax 轮询
	}

	// 将设备数据 设置到html上
	// 将设备数据 设置到html上
	// 将设备数据 设置到html上
	function deviceList(data, bdz) {
		var tempAreaHtml = '';
		for(var bdzid in data) { // 遍历变电站 
			var b = data[bdzid];

			if(b.id + "" === bdz + "" || bdz === "*") {
				$(".layui-card-header").html(b.name);
				var html = '';
				var html_chushiji = '';
				var html_kongtiao = '';

				b.devices.map(function(item, index) { // 遍历设备
					if(item.typeId === 1) { // 发现它是除湿机

						html_chushiji +=
							'<div class="layui-col-xs6  layui-col-md2"><div class="tab-device-chushi">' +
							//getType(item.typeId) +
							'</div></div><div class="layui-col-xs6  layui-col-md2"><div class="tab-device-yellow">' +
							getStatus(item.status) +
							'</div></div><div class="layui-col-xs6  layui-col-md2"><div class="tab-device-green">' +
							getNewDetailList(item.detailList).tempeEnv +
							'</div></div><div class="layui-col-xs6  layui-col-md2"><div class="tab-device-blue">' +
							getNewDetailList(item.detailList).humidEnv +
							'</div></div><div class="layui-col-xs6  layui-col-md2"><div id="tab-device-start"><button style="vertical-align: middle" class="layui-btn layui-btn-normal" onclick="member_start(this,' +
							item.id +
							')">开机</button></div></div><div class="layui-col-xs6  layui-col-md2"><div id="tab-device-stop"><button style="vertical-align: middle" class="layui-btn layui-btn-danger" onclick="member_stop(this,' +
							item.id +
							')">关机</button></div></div>';

					}
					if(item.typeId === 2) { // 发现它是空调
						html_kongtiao +=
							'<div class="layui-col-xs6  layui-col-md2"><div class="tab-device-kongtiao">' +
							//getType(item.typeId) +
							'</div></div><div class="layui-col-xs6  layui-col-md2"><div class="tab-device-yellow">' +
							getStatus(item.status) +
							'</div></div><div class="layui-col-xs6  layui-col-md2"><div class="tab-device-green">' +
							//item.detailList[0].tempeEnv +
							getNewDetailList(item.detailList).tempeEnv +
							'</div></div><div class="layui-col-xs6  layui-col-md2"><div class="tab-device-blue">' +
							//item.detailList[0].humidEnv +
							getNewDetailList(item.detailList).humidEnv +
							'</div></div><div class="layui-col-xs6  layui-col-md2"><div id="tab-device-start"><button style="vertical-align: middle" class="layui-btn layui-btn-normal" onclick="member_start(this,' +
							item.id +
							')">开机</button></div></div><div class="layui-col-xs6  layui-col-md2"><div id="tab-device-stop"><button style="vertical-align: middle" class="layui-btn layui-btn-danger" onclick="member_stop(this,' +
							item.id +
							')">关机</button></div></div>';
					}
				});
				$('#chushiji').html(html_chushiji);
				$('#kongtiao').html(html_kongtiao);
			} else {
				tempAreaHtml += '<option data-id="' +
					b.id + '" >' +
					b.name + '</option>';
			}
		}
		$('#bdzChange').html(tempAreaHtml);
	}

	// 获取最新的设备详情
	function getNewDetailList(data) {

		var newReport = new Object();;
		/*newReport["tempeEnv"] = 0;
		newReport["humidEnv"] = 0;*/
		if(data.length!==0){
		data.map(function(item, index) {
			newReport.tempeEnv = item.tempeEnv;
			newReport.humidEnv = item.humidEnv;
		});
		}else{
			newReport.tempeEnv = "无数据";
			newReport.humidEnv = "无数据"
		}
		return newReport;
	}
	// 设置在图表上
	// 设置在图表上
	// 设置在图表上
	function getChartList(data, bdzId) {
		var series = [];
		let detailSetTempe = [];
		let detailSetHumid = [];
		let detailTempeEnv = [];
		let detailHumidEnv = [];
		let LocaleString = [];
		var date = new Date();

		for(var bdzid in data) {
			var b = data[bdzid];
			if(b.id + "" === bdzId + "") { // 找到该变电站
				for(var devicesid in b.devices) { // 遍历所有设备
					var d = b.devices[devicesid]
					d.detailList.map(function(item, index) { // 遍历所有的详情
						date.setTime(item.reportTime);
						detailSetTempe.push(item.setTempe);
						detailSetHumid.push(item.setHumid);
						detailTempeEnv.push(item.tempeEnv);
						detailHumidEnv.push(item.humidEnv);
						LocaleString.push(date.toLocaleString());

					});
				}
			}
		}
		series.push({
			name: '设置温度', // 设置名字
			data: detailSetTempe
		});
		series.push({
			name: '设置湿度', // 设置名字
			data: detailSetHumid
		});
		series.push({
			name: '环境温度', // 设置名字
			data: detailTempeEnv
		});
		series.push({
			name: '环境湿度', // 设置名字
			data: detailHumidEnv
		});
		//console.log(series);
		var chart = Highcharts.chart('container', {
			title: {
				text: '十天内设备温度情况'
			},
			subtitle: {
				text: '数据来源：'
			},
			xAxis: {
				categories: LocaleString
			},
			yAxis: {
				title: {
					text: '温度 (°C)'
				}
			},
			legend: {
				layout: 'vertical',
				align: 'right',
				verticalAlign: 'middle'
			},
			plotOptions: {
				series: {
					dataLabels: {
						// 开启数据标签
						enabled: true
					},
				}
			},
			series: series,
			series,
		});
	}

	// 设置变电站数据回显
	// 设置变电站数据回显
	// 设置变电站数据回显
	function getBdzData(bdzId) {
		$.ajax({
			url: bdzDataUrl,
			type: "post",
			data: {
				"stationId": bdzId
			},
			dataType: "json",
			cache: false,
			success: function(data) {
				if(data.success) {
					var tempRegion = '';
					var tempProvince = '';
					$('#bdzData-id').attr({
						"value": bdzId
					});
					$('#bdzData-name').attr({
						"value": data.bdzlist.name
					});
					$('#bdzData-address').attr({
						"value": data.bdzlist.address
					});

					if(data.bdzlist.region === "南昌市") {
						tempRegion += '<option value="南昌市" selected>南昌市</option>';
					} else {
						tempRegion += '<option value="南昌市" >南昌市</option>';
					}
					if(data.bdzlist.region === "抚州市") {
						tempRegion += '<option value="抚州市" selected>抚州市</option>';
					} else {
						tempRegion += '<option value="抚州市" >抚州市</option>';
					}
					$('#bdzData-region').html(tempRegion);

					if(data.bdzlist.province === "江西省") {
						tempProvince += '<option value="江西省" selected>江西省</option>';
					} else {
						tempProvince += '<option value="江西省" >江西省</option>';
					}
					if(data.bdzlist.province === "山西省") {
						tempProvince += '<option value="山西省" selected>山西省</option>';
					} else {
						tempProvince += '<option value="山西省" >山西省</option>';
					}
					$('#bdzData-province').html(tempProvince);

					/*  //经度纬度设置
 					$('#bdzData-lon').attr({
						"value": data.bdzlist.lon
					});
					$('#bdzData-lat').attr({
						"value": data.bdzlist.lat
					});*/
				}
			},
			error: function(data, error) {
				alert(error);
			}
		});
	}

	// 变电站数据编辑保存
	// 变电站数据编辑保存
	// 变电站数据编辑保存
	$("#bdz-save").click(function(data) {
		var bdzDataSave = {};
		bdzDataSave.id = $('#bdzData-id').val();
		bdzDataSave.name = $('#bdzData-name').val();
		bdzDataSave.address = $('#bdzData-address').val();
		bdzDataSave.region = $('#bdzData-region').val();
		bdzDataSave.province = $('#bdzData-province').val();

		//bdzDataSave.lon = $('#bdzData-lon').val();
		//bdzDataSave.lat = $('#bdzData-lat').val();

		$.ajax({
			url: bdzSaveUrl,
			type: 'POST',
			data: {
				"id": bdzDataSave.id,
				"name": bdzDataSave.name,
				"address": bdzDataSave.address,
				"region": bdzDataSave.region,
				"province": bdzDataSave.province
				//"lon":bdzDataSave.lon,
				//"lat":bdzDataSave.lat
			},
			dataType: "json",
			cache: false,
			success: function(data) {
				if(data.success) {
					layer.alert("编辑成功", {
						icon: 6
					});
				} else {
					layer.alert("编辑失败", {
						icon: 2
					});
				}
			},
			error: function(data, error) {
				layer.alert(data.responseJSON.status, {
					icon: 2
				});
			}
		});
	});

	// 设置采集端回显
	// 设置采集端回显
	// 设置采集端回显
	function getCollector(bdzId) {
		$.ajax({
			url: collectorDataUrl,
			type: "post",
			data: {
				"stationId": bdzId
			},
			dataType: "json",
			cache: false,
			success: function(data) {
				if(data.success) {
					var tempHtml = '';
					$('#collectror-id').attr({
						"value": bdzId
					});
					$('#collectror-ip').attr({
						"value": _int2iP(data.bdzlist.ip)
					});
					if(data.bdzlist.status === 0) {
						tempHtml += '<option value="0" selected>关机</option>';
					} else {
						tempHtml += '<option value="0" >关机</option>';
					}

					if(data.bdzlist.status === 1) {
						tempHtml += '<option value="1" selected>开机</option>';
					} else {
						tempHtml += '<option value="1" >开机</option>';
					}
					console.log(tempHtml);
					$('#collectror-status').html(tempHtml);
				}
			},
			error: function(data, error) {
				alert("设置采集端回显错误");
			}
		});
	}

	// 采集端数据编辑保存
	// 采集端数据编辑保存
	// 采集端数据编辑保存
	$("#collectror-save").click(function(data) {
		var collectorSave = {};
		collectorSave.stationId = $('#collectror-id').val();
		collectorSave.ip = $('#collectror-ip').val();
		collectorSave.status = $('#collectror-status').val();
		$.ajax({
			url: collectorSaveUrl,
			type: 'POST',
			data: {
				"stationId": collectorSave.stationId,
				"ip": _ip2int(collectorSave.ip),
				"status": collectorSave.status
			},
			dataType: "json",
			cache: false,
			success: function(data) {
				if(data.success) {
					layer.alert("增加成功", {
						icon: 6
					});
				} else {
					layer.alert("添加失败", {
						icon: 2
					});
				}
			},
			error: function(data, error) {
				layer.alert(data.responseJSON.status, {
					icon: 2
				});
			}
		});
	});

	//IP转成整型
	function _ip2int(ip) {
		var num = 0;
		ip = ip.split(".");
		num = Number(ip[0]) * 256 * 256 * 256 + Number(ip[1]) * 256 * 256 + Number(ip[2]) * 256 + Number(ip[3]);
		num = num >>> 0;
		return num;
	}
	//整型解析为IP地址
	function _int2iP(num) {
		var str;
		var tt = new Array();
		tt[0] = (num >>> 24) >>> 0;
		tt[1] = ((num << 8) >>> 24) >>> 0;
		tt[2] = (num << 16) >>> 24;
		tt[3] = (num << 24) >>> 24;
		str = String(tt[0]) + "." + String(tt[1]) + "." + String(tt[2]) + "." + String(tt[3]);
		return str;
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
	var bdzId = getQueryString('bdzId');
	var bdz = bdzId ? bdzId : '*';

	getlist(bdz);

});