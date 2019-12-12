

onload=function change() {
    var song = document.getElementById("a");
    var songShow = document.getElementById("timeDown")
    var songList = document.getElementById("b");
    var songListShow = document.getElementById("song_list");
    var album = document.getElementById("c");
    var albumShow = document.getElementById("album");
    var mv = document.getElementById("d");
    var mvShow = document.getElementById("mv");

     song.onclick=function () {
         songShow.style.display="block";
         songListShow.style.display="none";
         albumShow.style.display="none";
         mvShow.style.display="none";
     }
     songList.onclick=function () {
         songListShow.style.display="block";
         songShow.style.display="none";
         albumShow.style.display="none";
         mvShow.style.display="none";
     }
     album.onclick=function () {
         albumShow.style.display="block";
         mvShow.style.display="none";
         songShow.style.display="none";
         songListShow.style.display="none";
     }
     mv.onclick=function () {
         mvShow.style.display="block";
         songShow.style.display="none";
         songListShow.style.display="none";
         albumShow.style.display="none";
     }
};

$(document).ready(function () {

//接受url传来的参数
    function GetQueryString(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if (r != null) return unescape(r[2]);
        return null;
    }

// 调用方法
//     alert(decodeURI(GetQueryString("user")));
    var  search  = decodeURI(GetQueryString("user"));
    $.get(
        "/userGet",
        {"search":search},
        function (result) {
            var  data = JSON.parse(result);
            console.log(data);
            if(result!=null){
                $(".img")[0].src = data.result[0].img;
                $(".userName")[0].append(data.result[0].name)
            }
        }
    );




});





