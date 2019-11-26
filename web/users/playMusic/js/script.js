
onload = function change() {

    var total=document.getElementById("total_time");
    window.onload=function () {
        audio.addEventListener("canplay", function() {
            total.innerHTML=format(audio.duration);       //获取总时间
        });
    }


    function format(t) {
        var m=Math.floor(t/60);
        var s=Math.floor(t%60);
        if(m<=9)     //小于10时，在前面填0
            m="0"+m;
        if(s<=9)
            s="0"+s;
        return m+":"+s;
    }



    var pause = document.getElementById("pause"); //暂停，播放
    var range = document.getElementById("play_load");
    var progress = document.getElementById("play_dot");
    var dot = document.getElementById("dot");
    // var time = document.getElementById("play_time");
    var audio=document.getElementById("audio");
    var current=document.getElementById("current_time");




    audio.setAttribute("src","../images/1.mp3");
//播放，暂停
    pause.onclick=function () {
        if(pause.style.backgroundPosition!="-30px 0px"){
            audio.play();
            pause.style.backgroundPosition="-30px 0px";
        }else{
            audio.pause();
            pause.style.backgroundPosition="0px 0px";
        }
    }




//进度条
    audio.play();
    setInterval(setProgress,10);   //通过定时器设置进度的自动改变
    function setProgress() {
        current.innerHTML=format(audio.currentTime);  //设置当前时间的显示
        progress.style.width=(audio.currentTime)/(audio.duration)*644+"px";  //780px是总宽度
        dot.style.left=progress.style.width;
    }


    range.onmousedown = function (ev) {
        changProgress(ev);
    }

    dot.onmousedown = function (ev) {
        document.onmousemove = function (ev) {
            changProgress(ev);
        }
        document.onmouseup = function () {      //当鼠标松开后关闭移动事件和自身事件
            document.onmousemove = null;
            document.onmouseup = null;
        }
        return false;
    }

    function changProgress(ev) {
        // var p = ev.clientX;                //获取鼠标点击的x轴坐标
        // var l = ((p - 326) / 644).toFixed(3) * 100;
        //
        // if(l<0){
        //     l=0;
        // }
        // else if(l>100){
        //     l=100;
        // }
        // progress.style.width = l + "%";


        // var ev=ev||event;
        var l = ev.clientX - 326;          //获取圆距左端的距离
        if(l<0){
            l=0;
        }
        else if (l > 644) {
            l = 644;
        }
        dot.style.left=l+"px";
        progress.style.width=l+"px";
        audio.currentTime=(l/644)*audio.duration;    //设置当前时间，以改变真正的播放进度
        current.innerHTML=format(audio.currentTime);  //当前时间



    }









}



// function changeProgress(ev) {
//     // var ev=ev||event;
//     // var l = ev.clientX;
//     dot.style.left="50%";
//     progress.style.width="50%";
//
//
// }

