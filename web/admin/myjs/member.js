/**
 * Created by user on 2019/12/22.
 */

getData(1);
// 获取指定页数据
function getData(page) {
  let rows = 5;				// 每页行数

  layui.use(['form', 'table', 'laypage'], function () {
	let laydate = layui.laydate;
	let form = layui.form;
	let laypage = layui.laypage;
	let table = layui.table;
	let result = null;					// 保存表格中的所有数据
	let index = layer.load(2);

	// 渲染表格
	let userInfo = table.render({
	  elem: "#userInfo"
	  , title: "普通用户"
	  , id: "userList"
	  , url: "/admin/userSelect"
	  , where: {
		type: 0
		, 'page': page
		, 'limit': rows
	  }
	  , loading: false
	  , height: 320
	  , cols: [[
		{field: "checkbox", type: "checkbox", width: 48, fixed: "left"},
		{field: "id", title: "ID", width: 80, sort: true, event: "selectRow"},
		{field: "name", title: "用户名", width: 150, event: "selectRow"},
		{field: "sex", title: "性别", width: 80, sort: true, templet: "#sex", event: "selectRow"},
		{field: "email", title: "邮箱", minWidth: 180, event: "selectRow"},
		{field: "phone", title: "手机", minWidth: 180, event: "selectRow"},
		{field: "status", title: "状态", minWidth: 100, templet: "#status"},
		{title: "操作", width: 200, templet: "#operation"}
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
		// 分页
		laypage.render({
		  elem: 'pages'
		  , layout: ['count', 'prev', 'next', 'skip', 'page']
		  , count: count
		  , limit: rows
		  , curr: page
		  , groups: 2
		  , jump: function (obj, first) {
			if (!first) {
			  getData(obj.curr);
			}
		  }
		});

		layer.close(index);
	  }
	});

	// 监听操作事件
	table.on('tool(info)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
	  let data = obj.data //获得当前行数据
		, layEvent = obj.event; //获得 lay-event 对应的值
//		console.log(obj);
	  if (layEvent === 'detail') {
		let status = data.status === 0 ? '已启用' : '已禁用';
		let msg = "ID: " + data.id + "<br>"
		  + "用户名: " + data.name + "<br>"
		  + "性别: " + data.sex + "<br>"
		  + "邮箱: " + data.email + "<br>"
		  + "手机: " + data.phone + "<br>"
		  + "当前状态：" + status + "<br>";
		layer.open({
		  title: '用户信息'
		  , skin: 'info-class'
		  , content: msg
		});
	  }				// 查看
	  else if (layEvent === 'del') {								// 删除操作
		layer.prompt({
			title: "敏感操作，请验证密码！"
			// , skin: 'check-skin'
			, formType: 1
		  }
		  , function (value, index, elem) {
			// console.log(value);
			let password = 'delete';						// 应该从后台获取！！！
			if (value === password) {
			  layer.confirm('确定要永久删除该数据吗？删除后将无法恢复！'
				, {icon: 0, title: '提示', skin: 'warn-skin'}
				, function (index) {
				  if (delData(data.id)) {
					obj.del(); //删除对应行（tr）的DOM结构
					layer.msg('删除成功！', {icon: 1, time: 1500}, function () {
					  userInfo.reload();
					})
				  } else {
					layer.msg('删除失败，服务器异常！', {icon: 2, time: 1500})
				  }
				  layer.close(index);
				});
			} else {
			  layer.msg('密码错误！', {time: 1000});
			}
			layer.close(index);
		  });
	  }				// 删除
	  else if (layEvent === 'edit') {								// 编辑操作
		let request = "id=" + data.id + '&'
		  + "name=" + data.name + '&'
		  + "sex=" + data.sex + '&'
		  + "email=" + data.email + '&'
		  + "phone=" + data.phone;
		xadmin.open('编辑', './member-edit.html?' + request, 600, 400, false);
	  }				// 编辑
	  else if (layEvent === 'selectRow') {
		selectRow(obj);					// 此处存在一个bug：无法触发全选
	  }
	});

	// 监听单击行事件
	function selectRow(obj) {
	  let input = $(obj.tr[1]).find('input')[0];
	  input.value = obj.data.id;						// 设置复选框的value值
//		console.log("value = " + input.value);
	  input.checked = input.checked !== true;			// 设置复选框状态
	  console.log(input.checked);
	  let checked = input.checked;
	  obj['checked'] = checked;
	  if (checked) {								// 根据复选框是否选中，修改样式
		$(obj.tr[1]).find('div')[1].classList.add("layui-form-checked");
	  } else {
		$(obj.tr[1]).find('div')[1].classList.remove("layui-form-checked");
	  }
	  obj.tr.addClass('layui-table-click').siblings().removeClass('layui-table-click');  // 设置当前行样式
	}

	// 监听状态开关
	form.on('switch(status)', function (data) {

//		console.log(data);
	  let text = data.othis[0].innerText;				// 获取文本信息
	  let msg = '确认要' + text + '该用户吗？';			// 提示信息
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
			, url: '/admin/userUpdate'
			, data: {"id": id, "status": data.elem.value}
			, success: function (res) {
			  let data = JSON.parse(res);
			  if (data.success) {
//				console.log(data);
				let msgText = text === '启用' ? '已启用' : '已禁用';
				layer.msg(msgText, {icon: 1, time: 1000}, function () {
				  userInfo.reload();				// 重新渲染表格
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
		  if (text === '启用') {
			data.othis[0].classList.remove("layui-form-onswitch");
			$(data.othis[0]).find('em')[0].innerText = '禁用';			// 修改文本内容
			data.elem.checked = false;								// 修改状态
		  } else if (text === '禁用') {
			data.othis[0].classList.add("layui-form-onswitch");
			$(data.othis[0]).find('em')[0].innerText = '启用';
			data.elem.checked = true;
		  }
//			console.log('取消操作');
//		 	console.log(data);
		  layer.close(index);
		});			// 点击取消按钮后触发事件

	});

	// 监听复选框事件
	table.on('checkbox(info)', function (obj) {
	  //当前元素
//		console.log(obj);
	  if (obj.type === 'one') {
		let input = $(obj.tr[1]).find('input')[0];
		input.value = obj.data.id;						// 设置复选框的value值
//		  console.log("value = " + input.value);
//		  console.log(input.checked);
	  } else if (obj.type === 'all') {
		// 获取左侧固定位置的所有复选框（除了表头部分）
		let allCheckbox = $('.layui-table-fixed .layui-table-body input[name=layTableCheckbox]');
//		  console.log(allCheckbox);
//		  console.log(result);
		for (let i = 0; i < allCheckbox.length; ++i) {			// 此处存在严重bug，排序后result的id无法与表格对应
		  allCheckbox[i].value = result[i].id;
//		    console.log(allCheckbox[i].value);
		}
	  }
	});

	// 搜索
	form.on('submit(search)', function (data) {
	  let username = data.field.username;
	  if (username === "") {
		layer.msg('请输入用户名！', {icon: 5, time: 1000});
	  } else {
		userInfo.reload({
		  page: {curr: 1}
		  , where: {
			'username': username
			, 'type': 0
			, 'limit': rows
		  }
		});
	  }
	  return false;
	});

	$('#delAll').click(function () {
	  if (delAll()) {
		userInfo.reload();
	  }
	})
  });
}
// 删除选中行数据
function delData(id) {
  let success = false;
  let url = "/admin/userDelete";
  $.ajax({
	type: "post"
	, url: url
	, async: false
	, traditional: true
	, data: {"id": id}
	, success: function (res) {
	  let data = JSON.parse(res);
	  console.log(data);
	  success = data.success;
	}
  });
  return success;
}

function delAll() {
  let ids = [];
  let success = false;
  // 获取选中的id

  let checkbox = $('.layui-table-fixed-l tbody input');
  console.log(checkbox);
  $(checkbox).each(function (index, el) {
	if ($(this).prop('checked')) {
	  ids.push($(this).val())
	}
  });

  if (ids.length === 0) {
	layer.msg('请先选择要删除的数据！', {time: 1000});
  } else {

	layer.prompt({
		title: "敏感操作，请验证密码！"
		// , skin: 'check-skin'
		, formType: 1
	  }
	  , function (value, index, elem) {
		// console.log(value);
		let password = 'delete';						// 应该从后台获取！！！
		if (value === password) {
		  layer.confirm('确定要永久删除这' + ids.length + '数据吗？删除后将无法恢复！'
			, {icon: 0, title: '提示', skin: 'warn-skin'}
			, function (index) {
			  //捉到所有被选中的，发异步进行删除
			  success = delData(ids);
			  if (success) {
				layer.msg('删除成功！', {icon: 1, time: 1000});
			  } else {
				layer.msg('删除失败！', {icon: 2, time: 1000});
			  }
			  layer.close(index);
			});
		} else {
		  layer.msg('密码错误！', {time: 1000});
		}
		layer.close(index);
	  }
	);


  }
  return success;
}