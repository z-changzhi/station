$(function() {
	// 获取 市 - 变电站列表 用于前端侧边栏的展示
	var getRegionUrl='/station/region/list';
	function getlist(e) {
		$.ajax({
			url: getRegionUrl,
			type: "get",
			dataType: "json",
			success: function(data) {
				if(data.success) {
					queryList(data.bdzlist);
				}
			}
		});
	}
	function queryList(data) {
		var html = '';
		data.map(function(item, index) {
			var html2 = devicesList(item.bdz); //下拉菜单 遍历 变电站的
			html +=
				'<li><a href="javascript:;"><i class="iconfont">&#xe6a7;</i><cite>' +
				item.region +
				'</cite><i class="iconfont nav_right">&#xe6a7;</i></a>' +
				'<ul class="sub-menu" >' +
				html2 +
				'</ul></li>';
		});
		$('#query-list-bdz-div').html(html);
	}
	
	function devicesList(data) {//遍历变电站
		var html = '';
		data.map(function(item, index) {
			html += '<li><a _href="html/tab.html?bdzId=' + item.id + '"><cite>' + item.name + '</cite></a></li>';
		});
		return html;
	}
	getlist();
});