$(document).ready(function () {
    newSongData();

    function newSongData() {
        //信息部分
        $.get(
            "/song/get",
            function (result) {
                var data = JSON.parse(result);
                console.log(data);
                for (var j = 0; j < 16; j++) {
                    $(".newSongs_img")[j].src=data.result[j].img;
                    $(".newSongs_name")[j].append(data.result[j].name);
                    $(".newSongs_singerName")[j].append(data.result[j].singer);
                }
            }
        );
    }

});