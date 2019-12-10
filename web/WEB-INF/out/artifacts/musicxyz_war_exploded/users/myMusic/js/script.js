




// window.onload = function(){
//     document.getElementById("singList").onclick = function(){
//         document.getElementById("singListShow").style.display = "block";
//     }
// }

onload=function change() {
    var song = document.getElementById("a");
    var songShow = document.getElementById("song")
    var songList = document.getElementById("b");
    var songListShow = document.getElementById("songList");
    var album = document.getElementById("c");
    var albumShow = document.getElementById("album");
    var mv = document.getElementById("d");
    var mvShow = document.getElementById("mv");

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
