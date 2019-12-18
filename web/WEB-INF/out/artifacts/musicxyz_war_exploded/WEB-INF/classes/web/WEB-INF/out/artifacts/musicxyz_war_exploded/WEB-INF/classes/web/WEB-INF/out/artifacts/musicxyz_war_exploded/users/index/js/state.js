/**
 * Created by user on 2019/12/11.
 */

$(document).ready((function(){
  // 登陆状态
  var profile = document.querySelector('.has-login');    // 头像
  var notLog = $('.not-login');
  var exit = document.querySelector('.has-login .exit');
// 发送请求获取数据
  $.ajax({
	type: "post",
	url: "/userGet",
	data: {},
	success: function(result){
	  // console.log(result);
	  var data = JSON.parse(result);
	  // console.log(data);

	  if(data.success === false){
		$(notLog).css('display', 'block');
		$(profile).css('display', 'none')
	  }else{
		$(notLog).css('display', 'none');
		$(profile).css('display', 'block');
		if (data.result[0].img !== "") {
		  console.log(data.result[0].img);
		  $(profile).find('img')[0].setAttribute("src", data.result[0].img);
		}

		$(".logged").click(function () {
		  var users = data.result[0].name;
		  // console.log(users);
		  var  user =  encodeURI(encodeURI(users));
		  window.location.href = "/users/myMusic/myMusic.html?user="+user;
		})
	  }
	}
  });

  exit.onclick = function(){
	$.ajax({
	  type: "get",
	  url: "/loginAction/exit",
	  async: false,
	  success: function (result) {
		var data = JSON.parse(result);
		if(data.success){
		  console.log("exit!");
		  location.reload();
		}else{
		  console.log("exit error!");
		}
	  }
	});
  };
}));
