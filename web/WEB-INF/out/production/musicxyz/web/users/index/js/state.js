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
	  console.log(result);
	  var data = JSON.parse(result);
	  console.log(data);
	  if(data.success === false){
		$(notLog).css('display', 'block');
		$(profile).css('display', 'none')
	  }else{
		$(notLog).css('display', 'none');
		$(profile).css('display', 'block');
	  }
	}
  });

    //接受url传来的参数
    function GetQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    }

// 调用方法
//     alert(decodeURI(GetQueryString("search")));
var  search  = decodeURI(GetQueryString("search"));
    $.get(
        "/userGet",
        {"search":search},
        function (result) {
            var data = JSON.parse(result);
            console.log(data);
            if (result != null) {
                // $(".login_img")[0].src = data.result[0].img;
                if (data.result[0].img !== "") {
                    $("#login_img").attr("src", data.result[0].img);
                }
            }

            $(".logged").click(function () {
                var users = data.result[0].name;
                console.log(users);
               var  user =  encodeURI(encodeURI(users));
                window.location.href = "/users/myMusic/myMusic.html?user="+user;
            })
        }
    )
}));
