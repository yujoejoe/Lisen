/**
 * 判断登录状态
 */

$(document).ready(function(){
    var username = getCookie("username");
    if(username !== undefined){
        document.getElementById("btn_register").innerHTML=username;
        document.getElementById("btn_login").innerHTML="注销";
    }
});

/**
 * 获取cookie的值
 * @returns {string}  cookie的值
 */
function getCookie(cookieName)
{
    if(document.cookie.length > 0) //document.cookie是返回包含所有cookie的字符串
    {
        var cookie_pos = document.cookie.indexOf(cookieName + "=");  //取得这个cookie名在整个cookie中的位置
        // 如果找到了索引，就代表cookie存在，反之，就说明不存在。
        if (cookie_pos !== -1)
        {
            // 把cookie_pos放在值的开始，只要给值加1即可。
            cookie_pos += cookieName.length + 1;
            var cookie_end = document.cookie.indexOf(";", cookie_pos);
            if (cookie_end === -1)
            {
                cookie_end = document.cookie.length;
            }
            var value = unescape(document.cookie.substring(cookie_pos, cookie_end));//得到cookie的值
        }
    }
    return value;
}
