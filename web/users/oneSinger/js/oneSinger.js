onload=function change() {
    var single_option = document.getElementById("single_option");
    var special_option = document.getElementById("special_option");
    var mv_option = document.getElementById("mv_option");

    var single_show = document.getElementById("single_show");
    var special_show = document.getElementById("special_show");
    var mv_show = document.getElementById("mv_show");

    single_option.onclick=function () {
        single_show.style.display="block";
        special_show.style.display="none";
        mv_show.style.display="none";
    };

    special_option.onclick=function () {
        single_show.style.display="none";
        special_show.style.display="block";
        mv_show.style.display="none";
    };

    mv_option.onclick=function () {
        single_show.style.display="none";
        special_show.style.display="none";
        mv_show.style.display="block";
    };

};

$(document).ready(function () {
    AlbumDataS();

    //点击歌手姓名传参
    $(".txt_name").click(function () {
        var value = $(this).html();
        console.log(value);
        var search = encodeURI(encodeURI(value));
        window.location.href="../album/album.html?search="+search;
    });

    //点击歌手图片传参
    $(".txt_name_pic").click(function () {
        var value = $(this).attr("src");
        console.log(value);
        var search = encodeURI(encodeURI(value));
        window.location.href="../album/album.html?search="+search;
    });


    //接受url传来的参数
    function GetQueryString(name)
    {
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if(r!=null) return  unescape(r[2]); return null;
    }


    function AlbumDataS() {
        //信息部分
        var  infoGet = decodeURI(GetQueryString("search"));
        $.get(
            "/info/get",
            {"search": infoGet},
            function (result) {
                var data = JSON.parse(result);
                console.log(data);
                $("#fans").append(data.result[0].fans);
                $(".txt_one_img")[0].src= data.result[0].img;
                $(".txt_one_name")[0].append(data.result[0].name);
                $(".txt_one_alias")[0].append(data.result[0].alias);
                $(".txt_one_area")[0].append(data.result[0].area);
                $(".txt_one_birthPlace")[0].append(data.result[0].birthPlace);
                $(".txt_one_birthday")[0].append(data.result[0].birthday);

            }
        );
//全部播放
        $("#play_all").click(function () {
            var  search = $(".txt_one_name").html();
            console.log(search);
            var oneSinger = encodeURI(encodeURI(search));
            window.location.href = "../playMusic/playMusic.html?oneSinger="+oneSinger;

        });


//单曲播放
        $(".show_hide").click(function () {
            // console.log("aaa");
            var songs = $(this).find("li").eq(1).html();
            var singers = $(".txt_one_name").html();
            console.log(songs);
            console.log(singers);
            var song = encodeURI(encodeURI(songs));
            var singer = encodeURI(encodeURI(singers));
            window.location.href = "../playMusic/playMusic.html?song=" + song + "&singer=" + singer;
        });


//单曲列表
        var singleGet = decodeURI(GetQueryString("search"));
        $.get(
            "/single/get",
            {"search":singleGet},
            function (result) {
                var data = JSON.parse(result);
                console.log(data);
                $("#single_option").append(data.counts);   //单曲数目
                $("#singleNum").append(data.counts);       //单曲数目

                for (var i = 0; i <data.result.length; i++) {
                    $(".single_name")[i].append(data.result[i].song);
                    $(".single_special")[i].append(data.result[i].album);
                    $(".single_time")[i].append(data.result[i].duration);
                    $($(".show_hide")[i]).show();
                }
            }
        );

//专辑列表
        $.get(
            "/album/get",
            {search:singleGet},
            function (result) {
                var data = JSON.parse(result);
                console.log(data);
                $("#special_option").append(data.counts);
                $("#txt_album_name").append(data.counts);
                if(result!=null){
                    for (var j = 0; j <data.result.length; j++) {
                        $(".txt_name_pic")[j].src = data.result[j].img;
                        $(".txt_name")[j].append(data.result[j].name);
                        $($(".show_hides")[j]).show();
                    }
                }
            }
        );
// mv
$.get(
    "/mvGet",
    {"singer":singleGet},
    function (result) {
        var  data = JSON.parse(result);
        console.log(data);
        $("#mv_option").append(data.counts);
        $("#mv_counts").append(data.counts);
        if (result!==null){
            for (var j = 0; j <data.result.length ; j++) {
                $(".txt_mv_pic")[j].src = data.result[j].img;
                $(".txt_mv_name")[j].append(data.result[j].title);
                $($(".show_hide_mv")[j]).show();

            }
            // 绑定点击mv图片事件设置cookie
            for (var i = 0; i < data.result.length; i++) {
                var mv_pic = $(".txt_mv_pic");
                mv_pic[i].setAttribute("data-id", data.result[i].id);
                mv_pic[i].onclick = setCookie;
                var txt_mv = $(".txt_mv");
                txt_mv[i].href = "/users/mv/playvideo.html";
            }
            // 绑定点击mv名事件设置cookie
            for (var k = 0; k < data.result.length; k++) {
                var mv_name = $(".txt_mv_name");
                mv_name[k].setAttribute("data-id", data.result[k].id);
                mv_name[k].onclick = setCookie;
                mv_name[k].href = "/users/mv/playvideo.html";
            }
        }


    }
)


    }
});


function setCookie() {
    document.cookie = "mId=" + this.getAttribute('data-id') + ";path=/";
    console.log(document.cookie);
}