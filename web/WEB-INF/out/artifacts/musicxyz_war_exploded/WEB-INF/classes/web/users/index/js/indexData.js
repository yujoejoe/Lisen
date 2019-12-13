$(document).ready(function () {
    indexData();

    function indexData() {

        $.get(
            "/mvGet",
            function (result) {
                var data = JSON.parse(result);
                console.log(data);
                //热门歌单部分   暂时放了mv图片\名字\播放量 需改！需改！需改！需改！需改！需改！需改！需改！
                for (var i = 0; i < 12; i++) {
                    $(".hotSongList_img")[i].src=data.result[i+5].img;
                    $(".hotSongList_song")[i].append(data.result[i+5].title);
                    $(".hotSongList_playNum")[i].append(data.result[i+5].play);
                }
                //精彩推荐部分   暂时放了mv图片 需改！需改！需改！需改！需改！需改！需改！需改！
                for (var k = 0; k < 6; k++) {
                    $(".recommend_img")[k].src=data.result[k].img;
                }
                //MV部分
                for (var j = 0; j < 8; j++) {
                    $(".mv_img")[j].src=data.result[j].img;
                    $(".mv_song")[j].append(data.result[j].title);
                    $(".mv_singerName")[j].append(data.result[j].singer);
                    $(".mv_playNum")[j].append(data.result[j].play);
                }
            }
        );

        $.get(
            "/timeDown/get",
            function (result) {
                var data = JSON.parse(result);
                console.log(data);
                //新歌首发部分
                for (var j = 0; j < 12; j++) {
                    $(".newSongs_img")[j].src=data.result[j].img;
                    $(".newSongs_song")[j].append(data.result[j].song);
                    $(".newSongs_singerName")[j].append(data.result[j].singer);
                }
                //排行榜新歌部分
                for (var i = 0; i < 5; i++) {
                    $(".NewSong_songName")[i].append(data.result[i].song);
                    $(".NewSong_singerName")[i].append(data.result[i].singer);
                }
            }
        );

        //排行榜热歌部分
        $.get(
            "/SongHits/get",
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



        /*--------------------- 页面对接start ---------------------*/

        //排行榜新歌播放
        $("#play_NewSong").click(function () {
            var  singerGet = $(".NewSong_singerName").html();
            var  songGet = $(".NewSong_songName").html();
            console.log(singerGet);
            console.log(songGet);
            var singer = encodeURI(encodeURI(singerGet));
            var song = encodeURI(encodeURI(songGet));
            window.location.href = "../users/playMusic/playMusic.html?song=" + song + "&singer=" + singer;
        });

        //排行榜新歌播放
        $("#play_HotSong").click(function () {
            var  singerGet = $(".HotSong_singerName").html();
            var  songGet = $(".HotSong_songName").html();
            console.log(singerGet);
            console.log(songGet);
            var singer = encodeURI(encodeURI(singerGet));
            var song = encodeURI(encodeURI(songGet));
            window.location.href = "../users/playMusic/playMusic.html?song=" + song + "&singer=" + singer;
        });

        //排行榜日韩歌曲播放
        $("#play_JapanKorea").click(function () {
            var  singerGet = $(".JapanKorea_singerName").html();
            var  songGet = $(".JapanKorea_songName").html();
            console.log(singerGet);
            console.log(songGet);
            var singer = encodeURI(encodeURI(singerGet));
            var song = encodeURI(encodeURI(songGet));
            window.location.href = "../users/playMusic/playMusic.html?song=" + song + "&singer=" + singer;
        });


        //排行榜欧美歌曲播放
        $("#play_EuropeAmerica").click(function () {
            var  singerGet = $(".EuropeAmerica_singerName").html();
            var  songGet = $(".EuropeAmerica_songName").html();
            console.log(singerGet);
            console.log(songGet);
            var singer = encodeURI(encodeURI(singerGet));
            var song = encodeURI(encodeURI(songGet));
            window.location.href = "../users/playMusic/playMusic.html?song=" + song + "&singer=" + singer;
        });


        /*-------------------页面对接end-------------------*/

    }

});