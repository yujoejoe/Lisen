/**
 * Created by user on 2019/12/24.
 */

//	console.log(getCookie("adminType"));
var type = getCookie("adminType");


layui.use(['table', 'form', 'laytpl'], function () {
  let table = layui.table;
  let laytpl = layui.laytpl;
  let index = layer.load(2);

  // 打开添加管理员页面
  if (type === "2") {
	let addBtn = document.querySelector('#add');
	addBtn.classList.remove("layui-btn-disabled");
	addBtn.addEventListener('click', function () {
	  xadmin.open('添加管理员', './admin-add.html', 600, 420);
	})
  }

  // 渲染表格
  let adminInfo = table.render({
	elem: '#admin'
	, cols: [[
	  // {field: "checkbox", type: "checkbox", width: 48, fixed: "left"}
	  {field: "id", title: "ID", width: 80, sort: true}
	  , {field: "name", title: "登录名", width: 120}
	  , {field: "pswd", title: "密码", type: "password", width: 150}
	  , {field: "phone", title: "手机", width: 150}
	  , {field: "email", title: "邮箱", width: 150}
	  , {field: "role", title: "角色", width: 100, templet: "#role"}
	  , {field: "status", title: "审核状态", width: 120, templet: "#status"}
	  , {field: "operation", title: "操作", minWidth: 80, fixed: "right", templet: "#operation"}
	]]
	, url: "/admin/userSelect"
	, where: {type: 1}
	, response: {
	  statusName: 'success'
	  , statusCode: true
	  , countName: 'counts'
	  , dataName: 'result'
	}
	, done: function (res, curr, count) {
	  layer.close(index);
	}
  });

  table.on('tool(admin)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
	let data = obj.data; //获得当前行数据
	let layEvent = obj.event; //获得 lay-event 对应的值
	console.log(obj);
	if (layEvent === 'detail') {
	  let status = data.status === 0 ? '已审核' : '未审核';
	  let role = data.type === 1 ? '管理员' : '超级管理员';
	  let msg = "<span>ID</span><pre>:  " + data.id + "</pre><br>"
		+ "<span>登录名</span><pre>:  " + data.name + "</pre><br>"
		+ "<span>密码</span><pre>:  " + data.pswd + "</pre><br>"
		+ "<span>邮箱</span><pre>:  " + data.email + "</pre><br>"
		+ "<span>手机</span><pre>:  " + data.phone + "</pre><br>"
		+ "<span>角色</span><pre>:  " + role + "</pre><br>"
		+ "<span>审核状态</span><pre>:  " + status + "</pre><br>";
	  layer.open({
		title: '管理员信息'
		, skin: 'info-skin'
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
					adminInfo.reload();
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
		+ "pswd=" + data.pswd + '&'
		+ "email=" + data.email + '&'
		+ "phone=" + data.phone + '&'
		+ "role=" + data.type;
	  xadmin.open('编辑', './admin-edit.html?' + request, 600, 420, false);
	}				// 编辑
  });

//	laytpl.

});

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
