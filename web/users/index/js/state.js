/**
 * Created by user on 2019/12/11.
 */

$(document).ready((function(){
  // 登陆状态
  var profile = $('.logged');    // 头像
  var notLog = $('.not-login');
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
		  $("#login_img").attr("src", data.result[0].img);
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

}));
