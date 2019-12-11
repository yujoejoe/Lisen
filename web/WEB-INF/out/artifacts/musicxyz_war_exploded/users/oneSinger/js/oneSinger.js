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
        // console.log("aaa");
        var value = $(this).html();
        console.log(value);
        var search = encodeURI(encodeURI(value));
        window.location.href="../album/album.html?search="+search;
    });

    //点击歌手图片传参
    $(".txt_name_pic").click(function () {
        // console.log("aaa");
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

// 调用方法
    alert(decodeURI(GetQueryString("search")));


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
                $("#single_option").append(data.count);   //单曲数目
                $("#singleNum").append(data.count);       //单曲数目

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
                $("#special_option").append(data.count);
                $("#txt_album_name").append(data.count);
                if(result!=null){
                    for (var j = 0; j <data.result.length; j++) {

                        $(".txt_name_pic")[j].src = data.result[j].img;
                        console.log("aa");
                        $(".txt_name")[j].append(data.result[j].name);
                        $($(".show_hides")[j]).show();
                    }
                }
            }
        );






    }
});






