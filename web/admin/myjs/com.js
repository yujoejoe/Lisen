/**
 * Created by user on 2019/12/20.
 */

function getAdmin(){
  let data;
  let url = "/admin/userState";
  $.ajax({
	type: "get"
	, url: url
	, async: false
	, success: function(res){
	  data = JSON.parse(res);
	  console.log(data);
	}
  });
  return data;
}

// 获取url的参数
function getRequest() {
  let request = location.search.substr(1);		// 获取 ？号后面的参数
  let req = request.split('&');					// 分离参数
//	console.log(req);
  let data = {};									// 把参数变成键值对形式的对象
  req.forEach(function (ele) {
	data[decodeURI(ele.split('=')[0])] = decodeURI(ele.split('=')[1]);
  });
  return data;									// 返回参数对象
}