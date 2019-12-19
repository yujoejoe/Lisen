

// onload=function change() {
//     var song = document.getElementById("a");
//     var songShow = document.getElementById("song")
//     var songList = document.getElementById("b");
//     var songListShow = document.getElementById("song_list");
//     var album = document.getElementById("c");
//     var albumShow = document.getElementById("album");
//     var mv = document.getElementById("d");
//     var mvShow = document.getElementById("mv");
//
//      song.onclick=function () {
//          songShow.style.display="block";
//          songListShow.style.display="none";
//          albumShow.style.display="none";
//          mvShow.style.display="none";
//      }
//      songList.onclick=function () {
//          songListShow.style.display="block";
//          songShow.style.display="none";
//          albumShow.style.display="none";
//          mvShow.style.display="none";
//      }
//      album.onclick=function () {
//          albumShow.style.display="block";
//          mvShow.style.display="none";
//          songShow.style.display="none";
//          songListShow.style.display="none";
//      }
//      mv.onclick=function () {
//          mvShow.style.display="block";
//          songShow.style.display="none";
//          songListShow.style.display="none";
//          albumShow.style.display="none";
//      }
// };

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
            if (result != null) {
                if (data.result[0].img!=="") {
                    $(".img")[0].src = data.result[0].img;
                }
                $(".userName")[0].append(data.result[0].name)
            }

        }
    );


//     $.get(
//     "/collection/get",
//     {"search":search},
//     function (result) {
//         var  data = JSON.parse(result);
//         console.log(data);
//
//             if(result!=null) {
//
//
//                 for (var i = 0; i < data.result.length; i++) {
//                     // 歌曲
//                     $(".single_song")[i].append(data.result[i].song);
//                     $(".single_singer")[i].append(data.result[i].singer);
//                     $(".single_duration")[i].append(data.result[i].duration);
//                     $($(".single_header")[i]).show();
//                     // 专辑
//                     $(".txt_img")[i].src = data.result[i].albumListImg;
//                     $(".txt_name")[i].append(data.result[i].albumListName);
//                     $($(".show_hide")[i]).show();
//                     // MV
//                     $(".txt_imgM")[i].src = data.result[i].mvImg;
//                     $(".txt_nameM")[i].append(data.result[i].mvName);
//                     $($(".show_hideM")[i]).show();
//                 }
//             }
//
//     }
// );
    // 歌曲
    $.get(
        "/collections/get",
        {"search": search},
        function (result) {
            var data = JSON.parse(result);
            console.log(data);
            $("#song").append(data.counts);
            for (var i = 0; i < data.result.length; i++) {

                $(".single_song")[i].append(data.result[i].song);
                $(".single_singer")[i].append(data.result[i].singer);
                $(".single_duration")[i].append(data.result[i].duration);
                $($(".single_header")[i]).show();
            }
        }
    );


    //歌单
    $.get(
      "/collection/list/get",
        {"search":search},
        function (result) {
          var data = JSON.parse(result);
          console.log(data);
            $("#songList").append(data.counts);
            for (var i = 0; i < data.result.length; i++) {
                $(".txt_imgS")[i].src = data.result[i].songListImg;
                $(".txt_nameS")[i].append(data.result[i].songListName);
                $($(".show_hideS")[i]).show();
            }

        }
    );



    // 专辑
    $.get(
        "/collection/album/get",
        {"search": search},
        function (result) {
            var data = JSON.parse(result);
            console.log(data);
            $("#album").append(data.counts);
            for (var i = 0; i < data.result.length; i++) {

                $(".txt_img")[i].src = data.result[i].albumListImg;
                $(".txt_name")[i].append(data.result[i].albumListName);
                $($(".show_hide")[i]).show();
            }
        }
    );

    // MV
    $.get(
        "/collection/mv/get",
        {"search": search},
        function (result) {
            var data = JSON.parse(result);
            console.log(data);
            $("#mv").append(data.counts);

            for (var i = 0; i < data.result.length; i++) {

                $(".txt_imgM")[i].src = data.result[i].mvImg;
                $(".txt_nameM")[i].append(data.result[i].mvName);
                $($(".show_hideM")[i]).show();
            }
        }
    );



    $("#mv").css("background","#31c27c");

    $("#song").click(function () {
        $("#song").css("background","#31c27c");
        $("#songList").css("background","none");
        $("#album").css("background","none");
        $("#mv").css("background","none");
        $("#single").show();
        $("#list_list").hide();
        $("#list_mv").hide();
        $("#list_song").hide();
    });

    $("#songList").click(function () {
        $("#song").css("background","none");
        $("#songList").css("background","#31c27c");
        $("#album").css("background","none");
        $("#mv").css("background","none");
        $("#single").hide();
        $("#list_list").hide();
        $("#list_mv").hide()
        $("#list_song").show()

    })
    $("#album").click(function () {
        $("#song").css("background","none");
        $("#songList").css("background","none");
        $("#album").css("background","#31c27c");
        $("#mv").css("background","none");
        $("#single").hide();
        $("#list_list").show();
        $("#list_mv").hide();
        $("#list_song").hide();
    });

    $("#mv").click(function () {
        $("#song").css("background","none");
        $("#songList").css("background","none");
        $("#album").css("background","none");
        $("#mv").css("background","#31c27c");
        $("#single").hide();
        $("#list_list").hide();
        $("#list_mv").show();
        $("#list_song").hide();
    });


});





