/**
 * 判断登录状态
 */

$(document).ready(function(){
    var username = getCookie("username");
    if(username === undefined){
        // alert("状态：未登录")
    }else {
        document.getElementById("btn_register").innerHTML=username;
        document.getElementById("btn_login").innerHTML="注销";
        alert("状态：已登录")
    }
});

/**
 * 获取cookie的值
 * @param cookieName  cookie的name
 * @returns {string}  cookie的值
 */
function getCookie(cookieName)
{
    var allcookies = document.cookie;
    var cookie_pos = allcookies.indexOf(cookieName);   //索引的长度

    // 如果找到了索引，就代表cookie存在，
    // 反之，就说明不存在。
    if (cookie_pos !== -1)
    {
        // 把cookie_pos放在值的开始，只要给值加1即可。
        cookie_pos += cookieName.length + 1;
        var cookie_end = allcookies.indexOf(";", cookie_pos);
        if (cookie_end === -1)
        {
            cookie_end = allcookies.length;
        }
        var value = unescape(allcookies.substring(cookie_pos, cookie_end));//得到cookie的值
    }
    return value;
}
