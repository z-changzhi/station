$(function() {
	//加载弹出层
	layui.use(['form', 'element'],
		function() {
			layer = layui.layer;
			element = layui.element;
		});

	//触发事件
	var tab = {
		tabAdd: function(title, url, id) {
			//新增一个Tab项
			element.tabAdd('xbs_tab', {
				title: title,
				content: '<iframe tab-id="' + id + '" frameborder="0" src="' + url + '" scrolling="yes" class="x-iframe"></iframe>',
				id: id
			})
		},
		tabDelete: function(othis) {
			//删除指定Tab项
			element.tabDelete('xbs_tab', '44'); //删除：“商品管理”

			othis.addClass('layui-btn-disabled');
		},
		tabChange: function(id) {
			//切换到指定Tab项
			element.tabChange('xbs_tab', id); //切换到：用户管理
		}
	};

	tableCheck = {
		init: function() {
			$(".layui-form-checkbox").click(function(event) {
				if($(this).hasClass('layui-form-checked')) {
					$(this).removeClass('layui-form-checked');
					if($(this).hasClass('header')) {
						$(".layui-form-checkbox").removeClass('layui-form-checked');
					}
				} else {
					$(this).addClass('layui-form-checked');
					if($(this).hasClass('header')) {
						$(".layui-form-checkbox").addClass('layui-form-checked');
					}
				}

			});
		},
		getData: function() {
			var obj = $(".layui-form-checked").not('.header');
			var arr = [];
			obj.each(function(index, el) {
				arr.push(obj.eq(index).attr('data-id'));
			});
			return arr;
		}
	}

	//开启表格多选
	tableCheck.init();

	$('.container .left_open i').click(function(event) {
		if($('.left-nav').css('left') == '0px') {
			$('.left-nav').animate({
				left: '-221px'
			}, 100);
			$('.page-content').animate({
				left: '0px'
			}, 100);
			$('.page-content-bg').hide();
		} else {
			$('.left-nav').animate({
				left: '0px'
			}, 100);
			$('.page-content').animate({
				left: '221px'
			}, 100);
			if($(window).width() < 768) {
				$('.page-content-bg').show();
			}
		}

	});

	$('.page-content-bg').click(function(event) {
		$('.left-nav').animate({
			left: '-221px'
		}, 100);
		$('.page-content').animate({
			left: '0px'
		}, 100);
		$(this).hide();
	});

	$('.layui-tab-close').click(function(event) {
		$('.layui-tab-title li').eq(0).find('i').remove();
	});

	$("tbody.x-cate tr[fid!='0']").hide();
	// 栏目多级显示效果
	$('.x-show').click(function() {
		if($(this).attr('status') == 'true') {
			$(this).html('&#xe625;');
			$(this).attr('status', 'false');
			cateId = $(this).parents('tr').attr('cate-id');
			$("tbody tr[fid=" + cateId + "]").show();
		} else {
			cateIds = [];
			$(this).html('&#xe623;');
			$(this).attr('status', 'true');
			cateId = $(this).parents('tr').attr('cate-id');
			getCateId(cateId);
			for(var i in cateIds) {
				$("tbody tr[cate-id=" + cateIds[i] + "]").hide().find('.x-show').html('&#xe623;').attr('status', 'true');
			}
		}
	})

	//左侧菜单效果
	// $('#content').bind("click",function(event){
	//点击菜单显示效果	
	$(document).ready(function() {
		/*$('.left-nav #nav li .sub-menu li ').click(function(){
       		console.log("111");
		   $(this).addClass('menu-current').siblings().removeClass('menu-current');
		   });*/
		$('body').on("click", "left-nav #nav li .sub-menu li ", function() {
			$(this).addClass('menu-current').siblings().removeClass('menu-current');
		});
	});

	//$('.left-nav #nav li').click(aaa);

	$('body').on('click', '.left-nav #nav li', aaa);

	function aaa(event) {
		if($(this).children('.sub-menu').length) {
			if($(this).hasClass('open')) { //检查第一个 <p> 元素是否包含 "open" 类：
				$(this).removeClass('open'); //移除所有 <p> 的 "open" 类：
				$(this).find('.nav_right').html('&#xe6a7;'); //所有段落中的后代 .nav_right 元素，并将其颜色设置为&#xe6a7;：
				$(this).children('.sub-menu').stop().slideUp(); // 通过使用滑动效果，隐藏被选元素，如果元素已显示出来的话。
				$(this).siblings().children('.sub-menu').slideUp(); //siblings() 获得匹配集合中每个元素的同胞，通过选择器进行筛选是可选的。

			} else {
				$(this).addClass('open'); ////添加 的 "open" 类：
				$(this).children('a').find('.nav_right').html('&#xe6a6;');
				$(this).children('.sub-menu').stop().slideDown();
				$(this).siblings().children('.sub-menu').stop().slideUp();
				$(this).siblings().find('.nav_right').html('&#xe6a7;');
				$(this).siblings().removeClass('open');
			}
		} else {

			var url = $(this).children('a').attr('_href');
			var title = $(this).find('cite').html();
			var urlindex = url.lastIndexOf("\=");
			// 截取设备设备id做 选项卡索引
			var index = parseInt(url.substring(urlindex + 1, url.length));
			
			console.log("iddex:"+index);
			for(var i = 0; i < $('.x-iframe').length; i++) {
				if(parseInt($('.x-iframe').eq(i).attr('tab-id')) == index) {// 判断 选项卡 是否存在
					//console.log("parseInt"+parseInt($('.x-iframe').eq(i).attr('tab-id')));
					tab.tabChange(index);
					event.stopPropagation();
					return;
				}
			};

			tab.tabAdd(title, url, index);
			tab.tabChange(index);
		}

		event.stopPropagation(); //阻止 click 事件冒泡到父元素：

	}

	$('.container #tabTarget li').click(bbb); // 右上角栏目被点击
	function bbb(event) { // tabAdd
		var url = $(this).children('a').attr('_href');
		var title = $(this).find('cite').html();
		var index = $('.container #tabTarget li').index($(this))-40 //防止打开重复的标签
		//index() 方法返回指定元素相对于其他指定元素的 index 位置。
		for(var i = 0; i < $('.x-iframe').length; i++) {
			if($('.x-iframe').eq(i).attr('tab-id') == index + 1) {
				tab.tabChange(index + 1);
				event.stopPropagation();
				return;
			}
		};
		tab.tabAdd(title, url, index + 1);
		tab.tabChange(index + 1);
		event.stopPropagation(); //阻止 click 事件冒泡到父元素：
	}
})
var cateIds = [];

function getCateId(cateId) {

	$("tbody tr[fid=" + cateId + "]").each(function(index, el) {
		id = $(el).attr('cate-id');
		cateIds.push(id);
		getCateId(id);
	});
}

/*弹出层*/
/*
    参数解释：
    title   标题
    url     请求的url
    id      需要操作的数据id
    w       弹出层宽度（缺省调默认值）
    h       弹出层高度（缺省调默认值）
*/
function x_admin_show(title, url, w, h) {
	if(title == null || title == '') {
		title = false;
	};
	if(url == null || url == '') {
		url = "404.html";
	};
	if(w == null || w == '') {
		w = ($(window).width() * 0.9);
	};
	if(h == null || h == '') {
		h = ($(window).height() - 50);
	};
	layer.open({
		type: 2,
		area: [w + 'px', h + 'px'],
		fix: false, //不固定
		maxmin: true,
		shadeClose: true,
		shade: 0.4,
		title: title,
		content: url
	});
}

function s_admin_show(title, url, w, h) {
	if(title == null || title == '') {
		title = false;
	};
	if(url == null || url == '') {
		url = "404.html";
	};
	if(w == null || w == '') {
		w = ($(window).width() * 0.5);
	};
	if(h == null || h == '') {
		h = ($(window).height() * 0.5);
	};
	layer.open({
		type: 2,
		area: [w + 'px', h + 'px'],
		fix: false, //不固定
		maxmin: true,
		shadeClose: true,
		shade: 0.4,
		title: title,
		content: url
	});
}
/*关闭弹出框口*/
function x_admin_close() {
	var index = parent.layer.getFrameIndex(window.name);
	parent.layer.close(index);
}