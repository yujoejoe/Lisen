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
                    $(".hotSongList_name")[i].append(data.result[i+5].title);
                    $(".hotSongList_playNum")[i].append(data.result[i+5].play);
                }
                //热门歌单部分   暂时放了mv图片\名字\播放量 需改！需改！需改！需改！需改！需改！需改！需改！
                for (var k = 0; k < 6; k++) {
                    $(".recommend_img")[k].src=data.result[k].img;
                }
                //MV部分
                for (var j = 0; j < 8; j++) {
                    $(".mv_img")[j].src=data.result[j].img;
                    $(".mv_name")[j].append(data.result[j].title);
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
                    $(".newSongs_name")[j].append(data.result[j].name);
                    $(".newSongs_singerName")[j].append(data.result[j].singer);
                }
                //排行榜部分
                for (var i = 0; i < 10; i++) {
                    $(".PopularityIndex_songName")[i].append(data.result[i].name);
                    $(".PopularityIndex_singerName")[i].append(data.result[i].singer);
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
                    $(".JapanKorea_songName")[j].append(data.result[j].name);
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
                    $(".EuropeAmerica_songName")[j].append(data.result[j].name);
                    $(".EuropeAmerica_singerName")[j].append(data.result[j].singer);
                }
            }
        );

    }

});