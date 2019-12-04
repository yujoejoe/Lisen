/**
 * 判断登录状态
 */


/*$(document).ready(function(){

    var login = $("#btn_login").val();
    var register = $("#btn_register").val();
    $.get(
        "/loginAction/login",
        {"username":username,"password":password},
        function(result){
            var data = JSON.parse(result);
            if(data.success){
                if(data.msg==="查询成功"){
                    /!*alert("哩森音乐欢迎你");
                    window.location.href="/index.html";*!/
                    document.getElementById("btn_register").innerHTML=username;
                    document.getElementById("btn_login").innerHTML="注销";
                    register=username;
                    login="注销";
                    alert(username);
                }
            }
        }
    );



    // judgeLoginState();

});*/

/*function judgeLoginState(){

    var login = $("#btn_login").val();
    var register = $("#btn_register").val();
    $.get(
        "/loginAction/login",
        {"username":username,"password":password},
        function(result){
            var data = JSON.parse(result);
            if(data.success){
                if(data.msg==="查询成功"){
                    /!*alert("哩森音乐欢迎你");
                    window.location.href="/index.html";*!/
                    document.getElementById("btn_register").innerHTML=username;
                    document.getElementById("btn_login").innerHTML="注销";
                    register=username;
                    login="注销";
                    alert(username);
                }
            }
        }
    );


    /!*var username = "<%=se.getAttribute('name')%>";
    alert(username);
    if(username!=="null"){
        document.getElementById("btn_register").innerHTML=username;
        document.getElementById("btn_login").innerHTML="注销";
        window.location.reload();
    }*!/
}*/
