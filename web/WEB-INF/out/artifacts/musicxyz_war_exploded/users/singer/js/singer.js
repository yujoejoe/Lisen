onload=function change() {
    var all_option = document.getElementById("all_option");
//     var china_option = document.getElementById("china_option");
//     var euramerica_option = document.getElementById("euramerica_option");
//     var korea_option = document.getElementById("korea_option");
//     var japan_option = document.getElementById("japan_option");
//     var else_option = document.getElementById("else_option");
//
    var all = document.getElementById("all");
//     var china = document.getElementById("china");
//     var euramerica = document.getElementById("euramerica");
//     var korea = document.getElementById("korea");
//     var japan = document.getElementById("japan");
//     var es = document.getElementById("es");
//
    all_option.onclick=function () {
        all.style.display="block";
        china.style.display="none";
        euramerica.style.display="none";
        korea.style.display="none";
        japan.style.display="none";
        es.style.display="none";
        if (all.style.display==="block") {
            all_option.style.backgroundColor="#84fac1";
            china_option.style.backgroundColor="#f6f6f6";
            euramerica_option.style.backgroundColor="#f6f6f6";
            korea_option.style.backgroundColor="#f6f6f6";
            japan_option.style.backgroundColor="#f6f6f6";
            else_option.style.backgroundColor="#f6f6f6";
        }
    };
//
//     china_option.onclick=function () {
//         all.style.display="none";
//         china.style.display="block";
//         euramerica.style.display="none";
//         korea.style.display="none";
//         japan.style.display="none";
//         es.style.display="none";
//         if (china.style.display==="block") {
//             all_option.style.backgroundColor="#f6f6f6";
//             china_option.style.backgroundColor="#84fac1";
//             euramerica_option.style.backgroundColor="#f6f6f6";
//             korea_option.style.backgroundColor="#f6f6f6";
//             japan_option.style.backgroundColor="#f6f6f6";
//             else_option.style.backgroundColor="#f6f6f6";
//         }
//     };
//
//     euramerica_option.onclick=function () {
//         all.style.display="none";
//         china.style.display="none";
//         euramerica.style.display="block";
//         korea.style.display="none";
//         japan.style.display="none";
//         es.style.display="none";
//         if (euramerica.style.display==="block") {
//             all_option.style.backgroundColor="#f6f6f6";
//             china_option.style.backgroundColor="#f6f6f6";
//             euramerica_option.style.backgroundColor="#84fac1";
//             korea_option.style.backgroundColor="#f6f6f6";
//             japan_option.style.backgroundColor="#f6f6f6";
//             else_option.style.backgroundColor="#f6f6f6";
//         }
//     };
//
//     korea_option.onclick=function () {
//         all.style.display="none";
//         china.style.display="none";
//         euramerica.style.display="none";
//         korea.style.display="block";
//         japan.style.display="none";
//         es.style.display="none";
//         if (korea.style.display==="block") {
//             all_option.style.backgroundColor="#f6f6f6";
//             china_option.style.backgroundColor="#f6f6f6";
//             euramerica_option.style.backgroundColor="#f6f6f6";
//             korea_option.style.backgroundColor="#84fac1";
//             japan_option.style.backgroundColor="#f6f6f6";
//             else_option.style.backgroundColor="#f6f6f6";
//         }
//     };
//
//     japan_option.onclick=function () {
//         all.style.display="none";
//         china.style.display="none";
//         euramerica.style.display="none";
//         korea.style.display="none";
//         japan.style.display="block";
//         es.style.display="none";
//         if (japan.style.display==="block") {
//             all_option.style.backgroundColor="#f6f6f6";
//             china_option.style.backgroundColor="#f6f6f6";
//             euramerica_option.style.backgroundColor="#f6f6f6";
//             korea_option.style.backgroundColor="#f6f6f6";
//             japan_option.style.backgroundColor="#84fac1";
//             else_option.style.backgroundColor="#f6f6f6";
//         }
//     };
//
//     else_option.onclick=function () {
//         all.style.display="none";
//         china.style.display="none";
//         euramerica.style.display="none";
//         korea.style.display="none";
//         japan.style.display="none";
//         es.style.display="block";
//         if (es.style.display==="block") {
//             all_option.style.backgroundColor="#f6f6f6";
//             china_option.style.backgroundColor="#f6f6f6";
//             euramerica_option.style.backgroundColor="#f6f6f6";
//             korea_option.style.backgroundColor="#f6f6f6";
//             japan_option.style.backgroundColor="#f6f6f6";
//             else_option.style.backgroundColor="#84fac1";
//         }
//     };

    /*var show = document.getElementsByClassName("alphabetShow");
    show.onclick = function show() {
        for (var i=0; i<show.length; i++){
            var alphabet1 = document.getElementById(""+i);
            alphabet1.style.backgroundColor="#84fac1";
            alphabet1.style.display="block";
            for (var j=0; j<show.length; j++) {
                var alphabet2 = document.getElementById(""+j);
                if (i !== j) {
                    alphabet2.style.backgroundColor="#f6f6f6";
                    alphabet2.style.display="none";
                }
            }
        }
    }*/


};

$(document).ready(function () {
    //歌手名传参
    $(".txt_name").click(function () {
        // console.log("aaa");
       var value = $(this).html();
        console.log(value);
        var search = encodeURI(encodeURI(value));
        window.location.href="../oneSinger/oneSinger.html?search="+search;
    });

    //歌手图片传参
    $(".txt_img").click(function () {
        // // console.log("aaa");
        //  value = $(this).attr("src");
        var value = $(this).next("a").html();
        console.log(value);
        var search = encodeURI(encodeURI(value));
        window.location.href="../oneSinger/oneSinger.html?search="+search;

    })
});

//全部歌手获取
$(document).ready(function () {
    $.get(
        "/info/get",
        // {"page":6,"size":3},
        function (result) {
            var data = JSON.parse(result);
            console.log(data);
            $(".page_firstShow").css("background-color","#31c27c");
            if (result != null && data.result.length < 16) {
                for (var i = 0; i < data.result.length; i++) {
                    $(".txt_singer_img")[i].src = data.result[i].img;
                    $(".txt_name")[i].innerHTML = data.result[i].name;
                    $($(".show_hide")[i]).show();
                }

            } else if (result != null && data.result.length > 15) {

                console.log("aaa");
                for (var j= 0; j < 15; j++) {
                    $(".txt_singer_img")[j].src = data.result[j].img;
                    $(".txt_name")[j].innerHTML = data.result[j].name;
                    $($(".show_hide")[j]).show();
                }
                $($(".page_option")[2]).click(function () {

                    $(this).css("background-color","#31c27c");
                    $(".page_firstShow").css("background-color","");
                        for (var k=0; k <data.result.length ; k++) {
                        $(".txt_singer_img")[k].src = data.result[k+15].img;
                        $(".txt_name")[k].innerHTML= data.result[k+15].name;
                        $($(".show_hide")[k]).show();
                    }
                })

            }
        }
    );
});


$(document).ready(function () {

    $(".nation").click(function () {
        $(".nation").css("background","none");
        $(this).css("background","#84fac1");
        
        var  search = $(this).html();
        console.log(search);
        $.get(
            "/info/get",
            {"search":search},
            function (result) {
                var  data = JSON.parse(result);
                console.log(data);
                if(result!=null){
                    $(".show_hide").hide();
                    for (var i = 0; i <data.result.length; i++) {
                        $(".txt_singer_img")[i].src = data.result[i].img;
                        $(".txt_name")[i].innerHTML = data.result[i].name;
                        $($(".show_hide")[i]).show();
                    }
                }
            }
        )

    })


});


