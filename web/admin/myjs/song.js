/**
 * Created by user on 2019/12/22.
 */

(function () {
  layui.use(['form', 'table', 'laypage'], function () {
	let table = layui.table;
	let laypage = layui.laypage;
	let form = layui.form;
	let $ = layui.jquery;
	let index = layer.load(2);
	let result = null;
	let rows = 10;

	// 渲染表格
	let songInfo = table.render({
	  elem: '#song'
	  , height: 480
	  , id: "songList"
	  , url: "/admin/songSelect"
//		, where: {
//		  'page': 1
//		  , 'limit': rows
//		}
	  , page: {
		layout: ['limit', 'count', 'prev', 'page', 'next', 'skip'] //自定义分页布局
		, curr: 1							// 当前页
		, groups: 3 					//只显示 3 个连续页码
		, first: '首页' 				//首页
		, prev: '上一页'
		, next: '下一页'
		, last: '尾页' 				//尾页
		, limit: 10
		, limits: [10, 20, 30]
	  }
//		, page: true
	  , cols: [[
		{type: "checkbox", width: 50, fixed: "left"},
		{field: "id", title: "ID", width: 80, sort: true},
		{field: "song", title: "歌曲名", minWidth: 150, edit: 'text'},
		{field: "singer", title: "歌手名", minWidth: 80, sort: true},
		{field: "album", title: "专辑名", minWidth: 150, sort: true},
		{field: "style", title: "歌曲风格", minWidth: 150, sort: true},
		{field: "duration", title: "歌曲时长", minWidth: 150, edit: 'text'},
		{field: "format", title: "歌曲格式", minWidth: 150, edit: 'text'},
		{field: "date", title: "发布日期", minWidth: 150, sort: true, edit: 'text'},
		{field: "status", title: "状态", minWidth: 100, templet: "#status"}
	  ]]
	  , response: {
		statusName: 'success'
		, statusCode: true
		, countName: 'counts'
		, dataName: 'result'
	  }
	  , done: function (res, curr, count) {
		result = res.result;
//		  console.log(res);
//		  console.log(curr);
//		  console.log(count);
		// 分页
//		  laypage.render({
//			elem: 'pages'
//			, layout: ['count', 'prev', 'next', 'skip', 'page']
//			, count: count
//			, limit: rows
//			, limits: [10, 20 ,30]
//			, curr: page
//			, groups: 2
//			, jump: function (obj, first) {
//			  if (!first) {
//				getData(obj.curr);
//			  }
//			}
//		  });

		layer.close(index);   // 关闭加载图片
	  }
	});

	// 监听状态开关
	form.on('switch(status)', function (data) {

//		console.log(data);
	  let text = data.othis[0].innerText;				// 获取文本信息
	  let msg = '确认要' + text + '该歌曲吗？';			// 提示信息
	  layer.confirm(msg, {icon: 3, title: '提示'}
		, function (index) {
		  data.elem.value = data.value === '1' ? 0 : 1;			// 令当前元素value值取反
		  let tr = data.elem.parentNode.parentNode.parentNode;
		  let id = tr.childNodes[1].childNodes[0].innerText;
//		    console.log("id: " + tr);
//		    console.log("value = " + data.elem.value);
		  // 发送异步请求执行状态变更
		  $.ajax({
			type: 'post'
			, url: '/admin/songUpdate'
			, data: {"id": id, "status": data.elem.value}
			, success: function (res) {
			  let data = JSON.parse(res);
			  if (data.success) {
//				console.log(data);
				let msgText = text === '上架' ? '已上架' : '已下架';
				layer.msg(msgText, {icon: 1, time: 1000}, function () {
				  songInfo.reload();				// 重新渲染表格
				});
			  }
			  else {
				layer.msg('服务器异常！', {icon: 2, time: 1000});
			  }
			}
		  });
		  layer.close(index);
		}   			// 点击确定按钮后触发事件
		, function (index) {
		  if (text === '上架') {
			data.othis[0].classList.remove("layui-form-onswitch");
			$(data.othis[0]).find('em')[0].innerText = '下架';			// 修改文本内容
			data.elem.checked = false;								// 修改状态
		  }
		  else if (text === '下架') {
			data.othis[0].classList.add("layui-form-onswitch");
			$(data.othis[0]).find('em')[0].innerText = '上架';
			data.elem.checked = true;
		  }
//			console.log('取消操作');
//		 	console.log(data);
		  layer.close(index);
		});			// 点击取消按钮后触发事件

	});

	// 监听单元格编辑事件
	table.on('edit(song)', function (obj) {
	  console.log(obj);
	  let msg = alterSong(obj) ? '修改成功' : '修改失败';
	  layer.msg(msg, {time: 1000}, function () {
		songInfo.reload();
	  })
	});

	// 重载表格
	$('#search').click(function () {
	  let songname = $('#songname').val();
	  if (songname !== undefined && songname !== "") {
		songInfo.reload({
		  where: {
			songname: songname
		  }
		  , page: {
			curr: 1
		  }
//			, parseData: function (res) {
//			  return {
//				"code": res.success, 			//解析接口状态
//				"msg": res.msg, 					//解析提示文本
//				"count": res.result.length, 			//解析数据长度
//				"data": res.result 				//解析数据列表
//			  }
//			}
		});
	  }
	  return false;				// 关闭button自带事件
	});			// 查询指定内容


  });
})();

function alterSong(obj) {
  let success = false;
  let id = obj.data.id;
  let field = obj.field;
  let value = obj.value;
  let data = {"id": id};				// 把参数封装成对象id为固定参数
  data[field] = value;				// field为变化的键, value为对应的值
  $.ajax({
	type: 'post'
	, url: '/admin/songUpdate'
	, async: false
	, data: data
	, success: function (res) {
	  let data = JSON.parse(res);
	  success = data.success;
	}
  });
  return success;
}

function del(argument) {

  let data = layui.table.checkStatus('songList').data;

  console.log(data);

  if (data.length === 0) {
	layer.msg("请选择要删除的数据", {
	  icon: 2
	});
  }
  else {
	let songId = [];
	for (let i = 0; i < data.length; ++i) {
	  songId.push(data[i].id);
	}
	layer.confirm('确认要删除这' + songId.length + '条数据吗？'
	  , {icon: 0, title: '提示'}
	  , function (index) {
		// 发送异步请求，执行删除操作
		$.ajax({
		  type: "post"
		  , url: "/admin/songDelete"
		  , traditional: true					// 设置traditional: true才能传数组
		  , data: {"songId": songId}
		  , success: function (res) {
			let data = JSON.parse(res);
			if (data.success) {
			  layer.msg(data.msg, {icon: 1, time: 1000}, function () {
				location.reload();
			  });
			}
			else {
			  layer.msg(data.msg, {icon: 2, time: 1000});
			}
		  }
		});
		layer.close(index);
	  });
  }
}