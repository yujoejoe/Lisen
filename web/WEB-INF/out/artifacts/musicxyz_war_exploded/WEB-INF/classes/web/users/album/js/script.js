$(document).ready(function () {

    AlbumData();

});

// $(document).ready(function () {
// $("#txt_login").click(function AlbumData() {
// // var search = $(".txt_album_name").val();
//
//     // var search = $("#test").val();
//     $.get(
//         "/album/get",
//         {"search":"search"},
//         function (result) {
//
//             var data = JSON.parse(result);
//             console.log(data);
//             $(".txt_album_img")[0].src = data.result[0].img;
//
//             $(".txt_album_name")[0].append(data.result[0].name);
//             $(".txt_album_singer")[0].append(data.result[0].singer);
//             $(".txt_album_genre")[0].append(data.result[0].genre);
//             $(".txt_album_area")[0].append(data.result[0].area);
//             // $(".txt_album_date")[0].innerHTML = data.result[0].date;
//             $(".txt_album_date")[0].append(data.result[0].date);
//             $(".txt_album_company")[0].append(data.result[0].company);
//         }
//     );
// })
// });

//接受url传来的参数

function GetQueryString(name)
{
    var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r!=null) return  unescape(r[2]); return null;
}

// 调用方法
// alert(decodeURI(GetQueryString("search")));

var  search =decodeURI(GetQueryString("search"));
function AlbumData() {
// var search = $(".txt_album_name").val();
    // var search = $("#test").val();

    $.get(
        "/album/get",
        {"search": search},
        function (result) {
            var data = JSON.parse(result);
            console.log(data);
            $(".txt_album_img")[0].src = data.result[0].img;
            $(".txt_album_name")[0].append(data.result[0].name);
            $(".txt_album_singer")[0].append(data.result[0].singer);
            $(".txt_album_genre")[0].append(data.result[0].genre);
            $(".txt_album_area")[0].append(data.result[0].language);
            $(".txt_album_date")[0].append(data.result[0].date);
            $(".txt_album_company")[0].append(data.result[0].company);
            // $($(".txt_album_genre")).show();
        }
    );
}


var  search =decodeURI(GetQueryString("search"));

$(document).ready(function () {
    $.get(
        "/album/song/get",
        {"search":search},
        function (result) {
            var  date = JSON.parse(result);
            console.log(date);
            if(result.length!=null){
                for (var i = 0; i <=date.result.length; i++) {
                    $(".album_singer")[i].innerHTML=date.result[i].singer;
                    $(".album_name")[i].innerHTML = date.result[i].song;
                    $($(".show_hide")[i]).show();
                }
            }
        }
    );

//单曲播放
    $(".show_hide").click(function () {
        // console.log("aaa");
        var songs = $(this).find("li").eq(1).html();
        var singers = $(this).find("li").eq(2).html();
        console.log(songs);
        console.log(singers);
        var song = encodeURI(encodeURI(songs));
        var singer = encodeURI(encodeURI(singers));
        window.location.href = "../playMusic/playMusic.html?song=" + song + "&singer=" + singer;
    });

//全部播放
    $("#play_all").click(function () {
        var  search = $(".txt_album_name").html();
        console.log(search);
        var album = encodeURI(encodeURI(search));
        window.location.href = "../playMusic/playMusic.html?album="+album;
    })


});


















// $(document).ready(function () {
//     var a = $("#album_name").html();
//     console.log(a);
//
//
//
// });

// var jqObj = $("#show_hide");
// jqObj.hide();
// var jq = $(".album_name");
// if(!jq.text().trim()){ //trim()方法是去空格，$.trim()函数删除提供字符串的所有换行符,空格（包括非中断空格），开始和结束tab。如果这些空白字符在字符串中间发生时，它们将被保留。
//     jqObj.hide();
// }

// var  album_name = $("#album_names").html();
// if(album_name!=0){
//     // console.log(aaa);
//     $("#album_names").hide();
//
// }
// $(document).ready(function () {
// //     // $("#album_names").click(function () {
// //     //     $("#show_hide").hide();
// //     //     console.log("aaa");
// //     // })
// //
// //     for (var i = 0; i < $(".album_name").length; i++) {
// //
// //         // var show_hide = $($(".show_hide")[i]).find("li").eq(1).html();
// //         // var show_hide = $($(".show_hide")[i]).html();
// //         var show_hide = $($(".album_name")[i]).parents("ul").html();
// //         var
// //         if()
// //         $($(".album_name")[i]).parents("ul").hide();
// //         console.log(show_hide);
// //         // if(show_hide==''){
// //         //     // console.log(aa);
// //         //     // console.log(show_hide);
// //         //     $($(".show_hide")[i]).hide();
// //         // }
// //
// //     }
// //
// // })
// //
// // var str = $(".div1").find("p").eq(0).html();

