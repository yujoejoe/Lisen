onload=function change() {
    var zg = document.getElementById("zg");
    var om = document.getElementById("om");
    var hg = document.getElementById("hg");
    var rb = document.getElementById("rb");
    var qt = document.getElementById("qt");
    var china = document.getElementById("china");
    var euramerica = document.getElementById("euramerica");
    var korea = document.getElementById("korea");
    var japan = document.getElementById("japan");
    var es = document.getElementById("else");

    song.onclick=function () {
        songShow.style.display="block";
        songListShow.style.display="none";
        albumShow.style.display="none";
        mvShow.style.display="none";
    }
    songList.onclick=function () {
        songListShow.style.display="block";
        songShow.style.display="none";
        albumShow.style.display="none";
        mvShow.style.display="none";
    }
    album.onclick=function () {
        albumShow.style.display="block";
        mvShow.style.display="none";
        songShow.style.display="none";
        songListShow.style.display="none";
    }
    mv.onclick=function () {
        mvShow.style.display="block";
        songShow.style.display="none";
        songListShow.style.display="none";
        albumShow.style.display="none";
    }
}