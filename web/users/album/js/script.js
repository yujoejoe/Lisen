

$(document).ready(function () {

AlbumData();

});
    
function AlbumData() {
// var search = $(".txt_album_name").val();
$.get(
    "/album/get",
    {"search":"打上"},
    function (result) {

        var data = JSON.parse(result);
        console.log(data);
           $(".txt_album_img")[0].src = data.result[0].img;
           $(".txt_album_name")[0].innerHTML = data.result[0].name;
           $(".txt_album_singer")[0].append(data.result[0].singer);
           $(".txt_album_genre")[0].append(data.result[0].genre);
           $(".txt_album_area")[0].append(data.result[0].area);
           // $(".txt_album_date")[0].innerHTML = data.result[0].date;
           $(".txt_album_date")[0].append(data.result[0].date);
           $(".txt_album_company")[0].append(data.result[0].company);
    }
);
}