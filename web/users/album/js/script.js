

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
           $(".txt_album_name")[0].innerHTML = data.result[0].name;
           $(".")
    }
);
}