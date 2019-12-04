/**
 * 用户登录
 */
$(document).ready(function(){
    $("#btn_register").click(function(){
        var username = $("#name").val();
        var password = $("#password").val();
        var email = $("#email").val();
        var phone = $("#phone").val();
        $.get(
            "/loginAction/register",
            {
                "username":username,
                "password":password,
                "email":email,
                "phone":phone
            },
            function(result){
                var data = JSON.parse(result);
                if(data.success){
                    if(data.msg==="注册成功"){
                        alert("成功注册哩森");
                        window.location.href="/index.html";
                    }else{
                        alert("注册失败!");
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
