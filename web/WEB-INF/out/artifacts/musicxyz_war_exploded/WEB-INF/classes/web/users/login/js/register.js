/**
 * Created by user on 2019/12/10.
 */

/**
 * 用户注册
 */
$(document).ready(function(){
  $("#btn_register").click(function(){
	if ($("#agree").is(':checked')) {
	  var name = $("#name").val();
	  var pswd = $("#pswd").val();
	  var email = $("#email").val();
	  var phone = $("#phone").val();
	  var sex = $("input[name='sex']:checked").val();
	  $.get(
		"/loginAction/register",
		{
		  "name":name,
		  "pswd":pswd,
		  "email":email,
		  "sex":sex,
		  "phone":phone
		},
		function(result){
		  // console.log(result);
		  var data = JSON.parse(result);
		  console.log(data);
		  if(data.success === false){
			alert(data.msg);
		  }else {
			window.location.href="/index.html";
		  }
		}
	  );
	}else {
	  alert("请勾选同意有关服务条款")
	}
  });
});