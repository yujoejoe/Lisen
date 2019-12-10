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
        var  infoGet = decodeURI(GetQueryString("search"));

        $.get(
            "/info/get",
            {"search": infoGet},
            function (result) {
                var data = JSON.parse(result);
                console.log(data);
                $(".txt_one_img")[0].src= data.result[0].img;
                $(".txt_one_name")[0].append(data.result[0].name);
                $(".txt_one_alias")[0].append(data.result[0].alias);
                $(".txt_one_area")[0].append(data.result[0].area);
                $(".txt_one_birthPlace")[0].append(data.result[0].birthPlace);
                $(".txt_one_birthday")[0].append(data.result[0].birthday);
            }
        );



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


        $.get(
            "/album/get",
            {search:singleGet},
            function (result) {
                var data = JSON.parse(result);
                console.log(data);
                $("#special_option").append(data.count);
            }



        )


    }
});






