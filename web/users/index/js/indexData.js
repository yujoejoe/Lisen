$(document).ready(function () {
    indexData();

    function indexData() {

        //新歌首发部分
        $.get(
            "/song/get",
            function (result) {
                var data = JSON.parse(result);
                console.log(data);
                for (var j = 0; j < 14; j++) {
                    $(".newSongs_img")[j].src=data.result[j].img;
                    $(".newSongs_name")[j].append(data.result[j].name);
                    $(".newSongs_singerName")[j].append(data.result[j].singer);
                }
            }
        );

        //热门歌单部分   暂时放了mv图片\名字\播放量 需改！需改！需改！需改！需改！需改！需改！需改！
        $.get(
            "/mvGet",
            function (result) {
                var data = JSON.parse(result);
                console.log(data);
                for (var j = 0; j < 12; j++) {
                    $(".hotSongList_img")[j].src=data.result[j+5].img;
                    $(".hotSongList_name")[j].append(data.result[j+5].title);
                    $(".hotSongList_playNum")[j].append(data.result[j+5].play);
                }
            }
        );

        //MV部分
        $.get(
            "/mvGet",
            function (result) {
                var data = JSON.parse(result);
                console.log(data);
                for (var j = 0; j < 8; j++) {
                    $(".mv_img")[j].src=data.result[j].img;
                    $(".mv_name")[j].append(data.result[j].title);
                    $(".mv_singerName")[j].append(data.result[j].singer);
                    $(".mv_playNum")[j].append(data.result[j].play);
                }
            }
        );

        //排行榜部分
        $.get(
            "/song/get",
            function (result) {
                var data = JSON.parse(result);
                console.log(data);
                for (var j = 0; j < 20; j++) {
                    $(".PopularityIndex_songName")[j].append(data.result[j].name);
                    $(".PopularityIndex_singerName")[j].append(data.result[j].singer);
                }
            }
        );

    }

});