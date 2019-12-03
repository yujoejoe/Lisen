/**
 * 用户登录
 */
$(document).ready(function(){
    $("#btn_login").click(function(){
        var username = $("#username").val();
        var password = $("#password").val();
        $.get(
            "/loginAction/login",
            {"username":username,"password":password},
            function(result){
                var data = JSON.parse(result);
                if(data.success){
                    if(data.msg==="查询成功"){
                        alert("哩森音乐欢迎你");
                        window.location.href="/index.html";
                    }else{
                        alert("账号或密码错误!");
                    }
                }
            }
        );

    });
});



/**
 * 用户注销
 */
$(document).ready(function(){
    if (document.getElementById("btn_login").innerHTML==="注销")
    {
        $("#btn_login").click(function(){
            $.get(
                "/loginAction/exit",
                function(result){
                    var data = JSON.parse(result);
                    if(data.success){
                        window.location.href = "/index.html";
                    }
                }
            );
        });
    }
});
