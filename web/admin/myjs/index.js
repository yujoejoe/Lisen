/**
 * Created by user on 2019/12/22.
 */

(function () {
  let data = getAdmin();
  if (data !== undefined && data.success && data.result[0].type >= 1) {
	$('.admin').html(data.result[0].name);
	document.cookie = "adminType=" + data.result[0].type + ";path=/admin/";
  }
  else {
	layui.use('layer', function () {
	  let layer = layui.layer;
	  layer.msg('请先登录！', {icon: 0, title: '提示', time: 1000}, function () {
		location.href = './login.html';
	  });
	});
  }

  // 获取当前时间
  setInterval(function () {
	let date = new Date();
	$('#date').html(date.toLocaleDateString() + " " + date.toLocaleTimeString());
  }, 1000);

})();

// 退出
function exit() {
  $.get("/loginAction/exit", function (res) {
	location.href = "/";
  });
}
