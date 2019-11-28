
onload = function change() {


    var pause = document.getElementById("pause"); //暂停，播放
    var range = document.getElementById("play_load");
    var progress = document.getElementById("play_dot");
    var dot = document.getElementById("dot");
    // var time = document.getElementById("play_time");
    var audio=document.getElementById("audio");
    var current=document.getElementById("current_time");
    var left=document.getElementById("btn_left");
    var right=document.getElementById("btn_right");

    function format(t) {
        var m=Math.floor(t/60);
        var s=Math.floor(t%60);
        if(m<=9)     //小于10时，在前面填0
            m="0"+m;
        if(s<=9)
            s="0"+s;
        return m+":"+s;
    }


    audio.setAttribute("src","music/G.E.M.邓紫棋 - 画 (Live Piano Session II).mp3");



    var total = document.getElementById("total_time");
    audio.addEventListener("canplay", function () {
        total.innerHTML = format(audio.duration);       //获取总时间
    });


    var name= document.getElementById("music_name");

    var music = new Array();
    music = ["G.E.M.邓紫棋 - 画 (Live Piano Session II)", "冷雪儿 - 浪子回头", "慵狐、熙兮兮兮 - 出山","磯村由紀子 - 風の住む街","音阙诗听、赵方婧 - 霜降"];
    var num=0;
    name.innerHTML =music[num];
   //上一曲
    left.onclick = function () {
        num = (num + music.length-1) % music.length;
        audio.src = "music/"+music[num]+ ".mp3";
        name.innerHTML =music[num];
        pause.style.backgroundPosition="-30px 0px";
        audio.play();
    }

   //下一曲
    right.onclick=function(){
        num = (num + 1) % music.length;
        audio.src = "music/"+music[num]+ ".mp3";
        name.innerHTML =music[num];
        pause.style.backgroundPosition="-30px 0px";
        audio.play();
    }



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

//自动播放下一首
    audio.addEventListener('ended', function () {

        right.onclick();

    }, false);

//进度条
    setInterval(setProgress,100);   //通过定时器设置进度的自动改变
    function setProgress() {
        current.innerHTML=format(audio.currentTime);  //设置当前时间的显示
        progress.style.width=(audio.currentTime)/(audio.duration)*644+"px";  //644px是总宽度
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
        var l = ev.clientX - 326;          //获取圆距左端的距离
        if (l < 0) {
            l = 0;
        } else if (l>644) {
            pause.style.backgroundPosition="0px 0px";
            l = 644;
        }


        dot.style.left = l + "px";
        progress.style.width = l + "px";
        // if (progress.style.width="644px"){
        //
        // };
        audio.currentTime = (l / 644) * audio.duration;    //设置当前时间，以改变真正的播放进度
        current.innerHTML = format(audio.currentTime);  //当前时间
    }


    //声音开关
    var voice = document.getElementById("btn_voice");
    voice.onclick=function () {
        if(voice.style.backgroundPosition!="0px -182px"){
            voice.style.backgroundPosition="0px -182px";
            audio.muted=true;
        }else{
            voice.style.backgroundPosition="0px -144px";
            audio.muted=false;
        }
    }


    //声音控制条
    var voiceRange = document.getElementById("play_voice");
    var voiceProgress = document.getElementById("progress_voice");
    var voiceDot = document.getElementById("voice_dot");

    voiceRange.onmousedown = function (ev) {
        changProgressVoice(ev);
    }

    voiceDot.onmousedown = function (ev) {
        document.onmousemove = function (ev) {
            changProgressVoice(ev);
        }
        document.onmouseup = function () {      //当鼠标松开后关闭移动事件和自身事件
            document.onmousemove = null;
            document.onmouseup = null;
        }
        return false;
    }

    function changProgressVoice(ev) {
        var d = ev.clientX - 1345;          //获取圆距左端的距离
        if (d < 0) {
            d = 0;
        } else if (d > 75) {

            d = 75;
        }
        voiceDot.style.left = d + "px";
        voiceProgress.style.width = d + "px";
        var v = (d/75).toFixed(1);
        audio.volume=v;
    }



    var listIconPlay = document.getElementsByClassName("mod_list_iconPlay");

    var i;
    for (i = 0; i < listIconPlay.length; i++) {
        listIconPlay[i].id = i;
        listIconPlay[i].onclick = function () {
            if (listIconPlay[this.id].style.backgroundPosition != "-30px 0px") {
                listIconPlay[this.id].style.backgroundPosition = "-30px 0px";
            }else{
                listIconPlay[this.id].style.backgroundPosition = "0px 0px";

            }
        }
    }







}






