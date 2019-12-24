$(document).ready(function () {
    indexData();

    function indexData() {

        //新歌首发部分
        $.get(
            "/album/get",
            { "field":"date",
                "order":"desc" },
            function (result) {
                var data = JSON.parse(result);
                for (var j = 0; j < 20; j++) {
                    $(".newSongs_img")[j].src=data.result[j+13].img;
                    $(".newSongs_song")[j].append(data.result[j+13].name);
                    $(".newSongs_singerName")[j].append(data.result[j+13].singer);
                }
            }
        );

        //热门歌单部分
        $.get(
            "/SongListGet",
            {"order":"1"},
            function (result) {
                var data = JSON.parse(result);
                console.log(data);
                for (var i = 0; i < 20; i++) {
                    $(".hotSongList_img")[i].src=data.result[i].img;
                    $(".hotSongList_song")[i].append(data.result[i].name);
                    $(".hotSongList_playNum")[i].append('播放量：' + (data.result[i].play/10000).toFixed(1)+' 万');
                }
                for (var j = 0; j < 20; j++) {
                    // 绑定点击事件设置cookie
                    var songList = document.querySelector("#hotSong .slider-wrapper").querySelectorAll(" .img");
                    // console.log(songList[j]);
                    songList[j].setAttribute("data-id", data.result[j].id);
                    songList[j].onclick = setCookieList;
                }
            }
        );

        //MV部分
        $.get(
            "/mvGet",
            {"order":"0"},
            function (result) {
                var data = JSON.parse(result);
                for (var j = 0; j < 8; j++) {
                    $(".mv_img")[j].src=data.result[j].img;
                    $(".mv_song")[j].append(data.result[j].title);
                    $(".mv_singerName")[j].append(data.result[j].singer);
                    $(".mv_playNum")[j].append((data.result[j].play/10000).toFixed(1) + '万');
                }
                // 绑定点击mv图片事件设置cookie
                for (var i = 0; i < 8; i++) {
                    var play = $("#mv .img");
                    play[i].setAttribute("data-id", data.result[i].id);
                    play[i].onclick = setCookie;
                }
                // 绑定点击mv名事件设置cookie
                for (var k = 0; k < 8; k++) {
                    var mv_song = $(".mv_song");
                    mv_song[k].setAttribute("data-id", data.result[k].id);
                    mv_song[k].onclick = setCookie;
                    mv_song[k].href = "/users/mv/playvideo.html";
                }
            }
        );

        //排行榜新歌部分
        $.get(
            "/timeDown/get",
            { "field":"date",
               "order":"desc" },
            function (result) {
                var data = JSON.parse(result);
                console.log(data);
                for (var i = 0; i < 5; i++) {
                    $(".NewSong_songName")[i].append(data.result[i].song);
                    $(".NewSong_singerName")[i].append(data.result[i].singer);
                }
            }
        );

        //排行榜热歌部分
        $.get(
            "/SongHits/get",
            { "hits":"hits",
                "order2":"desc" },
            function (result) {
                var data = JSON.parse(result);
                console.log(data);
                for (var j = 0; j < 5; j++) {
                    $(".HotSong_songName")[j].append(data.result[j].song);
                    $(".HotSong_singerName")[j].append(data.result[j].singer);
                }
            }
        );

        //排行榜日韩部分
        $.get(
            "/Japan/get",
            {"areaId1":"3",
            "areaId2":"4"},
            function (result) {
                var data = JSON.parse(result);
                console.log(data);
                for (var j = 0; j < 5; j++) {
                    $(".JapanKorea_songName")[j].append(data.result[j].song);
                    $(".JapanKorea_singerName")[j].append(data.result[j].singer);
                }
            }
        );

        //排行榜欧美歌曲部分
        $.get(
            "/EuropeAmerica/get",
            {"areaId":"2"},
            function (result) {
                var data = JSON.parse(result);
                console.log(data);
                for (var j = 0; j < 5; j++) {
                    $(".EuropeAmerica_songName")[j].append(data.result[j].song);
                    $(".EuropeAmerica_singerName")[j].append(data.result[j].singer);
                }
            }
        );





        /*--------------------- 页面跳转对接start ---------------------*/


 //新歌首发

        //点击图片传参
        $(".newSongs_img").click(function () {
            var value = $(this).attr("src");
            console.log(value);
            var search = encodeURI(encodeURI(value));
            window.location.href="../users/album/album.html?search="+search;
        });



 //排行榜

        //排行榜新歌播放
        $("#play_NewSong").click(function () {
            window.location.href = "../users/playMusic/playMusic.html?filed=date&order=desc";
        });

        //排行榜热歌播放
        $("#play_HotSong").click(function () {
            window.location.href = "../users/playMusic/playMusic.html?hits=hits&order2=desc&limit=20";
        });

        //排行榜日韩歌曲播放
        $("#play_JapanKorea").click(function () {
            window.location.href = "../users/playMusic/playMusic.html?areaId1=3&areaId2=4";
        });

        //排行榜欧美歌曲播放
        $("#play_EuropeAmerica").click(function () {
            window.location.href = "../users/playMusic/playMusic.html?areaId=2";
        });


        /*-------------------页面对接end-------------------*/

    }


});


function setCookie() {
    document.cookie = "mId=" + this.getAttribute('data-id') + ";path=/";
    console.log(document.cookie);
}

function setCookieList() {
    var cName = "slId";
    document.cookie = cName + "=" + this.getAttribute('data-id') + ";path=/";
    console.log(document.cookie);
}