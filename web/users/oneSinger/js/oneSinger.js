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
    $(".txt_name").click(function () {
        // console.log("aaa");
        var value = $(this).html();
        console.log(value);
        var search = encodeURI(encodeURI(value));

        window.location.href="../album/album.html?search="+search;
    })
});


$(document).ready(function () {
    $(".txt_name_pic").click(function () {
        // console.log("aaa");
        var value = $(this).attr("src");
        console.log(value);
        var search = encodeURI(encodeURI(value));

        window.location.href="../album/album.html?search="+search;
    })
});