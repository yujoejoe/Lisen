$(document).ready(function () {
    indexData();

    function indexData() {

        /*//精彩推荐部分   暂时放了mv图片 需改！需改！需改！需改！需改！需改！需改！需改！
        $.get(
            "/mvGet",
            function (result) {
                var data = JSON.parse(result);
                console.log(data);
                for (var k = 0; k < 6; k++) {
                    $(".recommend_img")[k].src=data.result[k].img;
                }
            }
        );*/

        //热门歌单部分
        $.get(
            "/SongListGet",
            function (result) {
                var data = JSON.parse(result);
                console.log(data);
                for (var i = 0; i < 20; i++) {
                    $(".hotSongList_img")[i].src=data.result[i%data.result.length].img;
                    $(".hotSongList_song")[i].append(data.result[i%data.result.length].name);
                    $(".hotSongList_playNum")[i].append(data.result[i%data.result.length].play);
                }
            }
        );

        //MV部分
        $.get(
            "/mvGet",
            function (result) {
                var data = JSON.parse(result);
                for (var j = 0; j < 8; j++) {
                    $(".mv_img")[j].src=data.result[j].img;
                    $(".mv_song")[j].append(data.result[j].title);
                    $(".mv_singerName")[j].append(data.result[j].singer);
                    $(".mv_playNum")[j].append((data.result[j].play/10000).toFixed(1) + '万');
                }
                // 绑定点击事件设置cookie
                for (var i = 0; i < 8; i++) {
                    var play = $(".play-icon");
                    play[i].setAttribute("data-id", i+1);
                    play[i].onclick = setCookie;
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
            window.location.href = "../users/playMusic/playMusic.html";
        });

        //排行榜欧美歌曲播放
        $("#play_EuropeAmerica").click(function () {
            window.location.href = "../users/playMusic/playMusic.html";
        });


        /*-------------------页面对接end-------------------*/

    }


});


function setCookie() {
    document.cookie = "mId=" + this.getAttribute('data-id') + ";path=/";
    console.log(document.cookie);
}