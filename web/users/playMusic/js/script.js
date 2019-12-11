window.onload = function change() {


  var pause = document.getElementById("pause"); //暂停，播放
  var range = document.getElementById("play_load");
  var progress = document.getElementById("play_dot");
  var dot = document.getElementById("dot");
  // var time = document.getElementById("play_time");
  var audio = document.getElementById("audio");
  var current = document.getElementById("current_time");
  var left = document.getElementById("btn_left");
  var right = document.getElementById("btn_right");

  /*==== 歌词容器 ====*/
  var lyric = document.getElementById('lyric');


  function format(t) {
    var m = Math.floor(t / 60);
    var s = Math.floor(t % 60);
    if (m <= 9)     //小于10时，在前面填0
      m = "0" + m;
    if (s <= 9)
      s = "0" + s;
    return m + ":" + s;
  }


    /*接受url传来的参数*/

    function GetQueryString(name)
    {
        var reg = new RegExp("(^|&)"+ name +"=([^&]*)(&|$)");
        var r = window.location.search.substr(1).match(reg);
        if(r!=null) return  unescape(r[2]); return null;
    }

// 调用方法
//     alert(decodeURI(GetQueryString("song")));
//     alert(decodeURI(GetQueryString("singer")));
//     alert(decodeURI(GetQueryString("album")));
    console.log(decodeURI(GetQueryString("song")));
    console.log(decodeURI(GetQueryString("singer")));
    var  song =decodeURI(GetQueryString("song"));
    var  singer =decodeURI(GetQueryString("singer"));

// //单曲播放
//     if(singer!="null") {
//         $(".list_author")[0].append(singer);
//         $($(".list_music")[0]).show();
//         $(".list_name")[0].append(song);
//         audio.setAttribute("src", "http://192.168.1.125:8080/music/song/music/"+singer+"/"+song+".mp3");
//
//         audio.play();
//         if (audio.played) {
//             pause.style.backgroundPosition = "-30px  0px";
//         }
//     }

    //获取总时间
    var total = document.getElementById("total_time");
    audio.addEventListener("canplay", function () {
        total.innerHTML = format(audio.duration);
    });

    var name = document.getElementById("music_name");
    var song_name = document.getElementById("song_info_name");
    var singer_name = document.getElementById("song_info_singer");
    var music = new Array();           //存放获取的歌曲
    var song_singer = new Array();     //存放 歌曲 - 歌手
    var pic_song = new Array();
    var pic_singer = new Array();
    var num = 0;

    //单曲播放
    if (singer != "null") {
        $(".list_author")[0].append(singer);
        $($(".list_music")[0]).show();
        $(".list_name")[0].append(song);
        audio.setAttribute("src", "http://192.168.1.125:8080/music/song/music/" + singer + "/" + song + ".mp3");

        audio.play();
        name.innerHTML = $($(".list_name")[0]).html()+" - "+$($(".list_author")[0]).html()
        if (audio.played) {
            pause.style.backgroundPosition = "-30px  0px";
        }
    }



    $(document).ready(function () {
        var search = decodeURI(GetQueryString("album"));
        // var music = new Array();

        //专辑播放列表
        $.get(
            "/album/song/get",
            {"search": search},
            function (result) {
                var data = JSON.parse(result);
                console.log(data);

                if (result != null) {
                    for (var j = 0; j < data.result.length; j++) {
                        $(".list_name")[j].append(data.result[j].song);
                        $(".list_author")[j].append(data.result[j].singer);
                        $($(".list_music")[j]).show();
                    }
                    audio.setAttribute("src","http://192.168.1.125:8080/music/song/music/"+data.result[0].singer+"/"+data.result[0].song+".mp3");
                    $("#song_info_name").html(data.result[0].singer);           //歌词滚动歌手
                    $("#song_info_singer").html(data.result[0].song);
                    /*==== 添加歌词 ====*/

                    //歌词滚动歌名
                    audio.play();
                    pause.style.backgroundPosition = "-30px  0px";
                    for (var i = 0; i <data.result.length; i++) {
                        var urlSinger = $($(".list_author")[i]).html();
                        var urlSong = $($(".list_name")[i]).html();
                        music[i] = urlSinger+"/"+urlSong;
                        song_singer[i]= urlSong+" - "+urlSinger;
                        pic_song[i] = urlSong;
                        pic_singer[i] = urlSinger;
                        console.log(music[i]);
                    }
                    name.innerHTML= song_singer[0];
                }
                addLyric(lyric);
            }
        );

        console.log(decodeURI(GetQueryString("oneSinger")));
        var  oneSinger =decodeURI(GetQueryString("oneSinger"));
        alert(decodeURI(GetQueryString("oneSinger")));
        //单曲列表播放
        $.get(
            "/single/get",
            {"search":oneSinger},
            function (result) {
                var data = JSON.parse(result);
                console.log(data);
                if (result != null) {
                    for (var k= 0; k< data.result.length; k++) {
                        $($(".list_author")[k]).append(oneSinger);
                        $($(".list_time")[k]).append(data.result[k].duration);
                        $($(".list_name")[k]).append(data.result[k].song);
                        $($(".list_music")[k]).show();
                    }
                    audio.setAttribute("src","http://192.168.1.125:8080/music/song/music/"+oneSinger+"/"+data.result[0].song+".mp3");
                    $("#song_info_name").html(oneSinger);
                    $("#song_info_singer").html(data.result[0].song);
                    audio.play();
                    // /*==== 添加歌词 ====*/
                    // addLyric(lyric);
                    pause.style.backgroundPosition = "-30px  0px";
                    for (var i = 0; i <data.result.length; i++) {
                        var urlSinger = oneSinger;
                        var urlSong = $($(".list_name")[i]).html();
                        music[i] = urlSinger+"/"+urlSong;
                        song_singer[i] = urlSong+" - "+urlSinger;
                        pic_song[i] = urlSong;
                        pic_singer[i] = urlSinger;

                        console.log(music[i]);
                    }
                    name.innerHTML= song_singer[0];

                }
                addLyric(lyric);
            }

        );
        // var  pic_right_name = $("").html();
        // //右部歌手图片
        // $.get(
        //     "/info/get/",
        //     {"search":pic_right_name},
        //     function f() {
        //
        //
        //
        //     }
        //
        // )

        addLyric(lyric);

    });

    // var name = document.getElementById("music_name");
    // var music = new Array();
    // music = ["音阙诗听、赵方婧 - 霜降", "G.E.M.邓紫棋 - 画 (Live Piano Session II)", "冷雪儿 - 浪子回头", "慵狐、熙兮兮兮 - 出山", "磯村由紀子 - 風の住む街"];
    // var num = 0;
    // name.innerHTML =song+" - "+singer;

  // /*==== 添加歌词 ====*/
  // addLyric(lyric);

  //上一曲
  left.onclick = function () {
    num = (num + music.length - 1) % music.length;
    audio.src = "http://192.168.1.125:8080/music/song/music/" + music[num] + ".mp3";
    name.innerHTML = song_singer[num];
    pause.style.backgroundPosition = "-30px 0px";
    song_name.innerHTML = pic_song[num];
    singer_name.innerHTML = pic_singer[num];
    audio.play();

    /*==== 添加歌词 ====*/
    clearLyric(lyric);
    addLyric(lyric);

  };

  //下一曲
  right.onclick = function () {
    num = (num + 1) % music.length;
    audio.src = "http://192.168.1.125:8080/music/song/music/" + music[num] + ".mp3";
    name.innerHTML = song_singer[num];
    pause.style.backgroundPosition = "-30px 0px";
      song_name.innerHTML = pic_song[num];
      singer_name.innerHTML = pic_singer[num];
    audio.play();

    /*==== 添加歌词 ====*/
    clearLyric(lyric);
    addLyric(lyric);

  };



//播放，暂停
  pause.onclick = function () {
    if (pause.style.backgroundPosition != "-30px 0px") {
      audio.play();
      pause.style.backgroundPosition = "-30px 0px";
    } else {
      audio.pause();
      pause.style.backgroundPosition = "0px 0px";
    }
  };

//自动播放下一首
  audio.addEventListener('ended', function () {

    right.onclick();

  }, false);









/*============ 播放进度条 ============*/


  /*==== 获取进度条的宽度 ====*/
  var rangeWidth = parseInt($(range).css('width').substring(0, $(range).css('width').length - 2));


  var mute = false;   //  默认打开声音


  setInterval(setProgress, 100);   //通过定时器设置进度的自动改变
  function setProgress() {
    current.innerHTML = format(audio.currentTime);  //设置当前时间的显示
    progress.style.width = (audio.currentTime) / (audio.duration) * rangeWidth + "px";
    dot.style.left = progress.style.width;
  }

  range.onmousedown = function (ev) {
    changProgress(ev);
  };

  dot.onmousedown = function (ev) {
    document.onmousemove = function (ev) {
      mute = true;    // 鼠标移动时静音
      // console.log("静音");
      changProgress(ev);
    };
    document.onmouseup = function (ev) {      //当鼠标松开后关闭移动事件和自身事件
      mute = false;   // 鼠标松开时打开声音
      // console.log("打开声音");
      changProgress(ev);
      document.onmousemove = null;
      document.onmouseup = null;
    };
    return false;
  };

  /*==== 获取左外边距 ====*/
  var MarginLeft = $(range).parent().css('margin-left');
  MarginLeft = MarginLeft.substring(0, MarginLeft.length - 2);
  /*==== 获取left值 ====*/
  var rangeLeft = $(range).parent().parent().css('left');
  rangeLeft = rangeLeft.substring(0, rangeLeft.length - 2);
  /*==== 获取圆的宽度 ====*/
  var dotWidth = $(dot).css('width');
  dotWidth = dotWidth.substring(0, dotWidth.length - 2);
  /*==== 计算圆离左边的距离 ====*/
  var leftDistance = parseInt(MarginLeft) + parseInt(rangeLeft) + parseInt(dotWidth) / 2;

  // 改变进度条
  function changProgress(ev) {
    // console.log("ev.clientX: " + ev.clientX);
    // console.log("leftDistance； " + leftDistance);
    var l = ev.clientX - leftDistance;          //获取圆距左端的距离
    if (l < 0) {
      l = 0;
    } else if (l > rangeWidth) {
      pause.style.backgroundPosition = "0px 0px";
      l = rangeWidth;
    }

    // console.log("l: " + l);

    dot.style.left = l + "px";
    progress.style.width = l + "px";

    audio.muted = mute;

    audio.currentTime = (l / rangeWidth) * audio.duration;    //设置当前时间，以改变真正的播放进度
    current.innerHTML = format(audio.currentTime);  //当前时间
  }







  /*============ 声音进度条 ============*/


  //声音开关
  var voice = document.getElementById("btn_voice");
  voice.onclick = function () {
    if (voice.style.backgroundPosition !== "0px -182px") {
      voice.style.backgroundPosition = "0px -182px";
      audio.muted = true;
    } else {
      voice.style.backgroundPosition = "0px -144px";
      audio.muted = false;
    }
  };

  //声音控制条
  var voiceRange = document.getElementById("play_voice");
  var voiceProgress = document.getElementById("progress_voice");
  var voiceDot = document.getElementById("voice_dot");

  /*==== 获取声音控制条宽度 ====*/
  var voiceRangeWidth = $(voiceRange).css('width');
  voiceRangeWidth = parseInt(voiceRangeWidth.substring(0, voiceRangeWidth.length - 2));
  /*==== 获取control的宽度 ====*/
  var controlWidth = $(voiceRange).parent().parent().css('width');
  controlWidth = controlWidth.substring(0, controlWidth.length - 2);
  /*==== 控制条左边距离 ====*/
  var voiceLeftDistance = parseInt(controlWidth) + parseInt(rangeLeft) - voiceRangeWidth + parseInt(dotWidth) / 2;


  // console.log("control的宽度："+controlWidth);
  //
  // console.log("声音控制条宽度："+voiceRangeWidth);
  //
  // console.log("控制条左边距离："+voiceLeftDistance);


  voiceRange.onmousedown = function (ev) {
    changProgressVoice(ev);
  };

  voiceDot.onmousedown = function (ev) {
    document.onmousemove = function (ev) {
      changProgressVoice(ev);
    };
    document.onmouseup = function () {      //当鼠标松开后关闭移动事件和自身事件
      document.onmousemove = null;
      document.onmouseup = null;
    };
    return false;
  };

  // 改变进度条
  function changProgressVoice(ev) {
    // console.log("声音控制条x坐标："+ev.clientX);
    var d = ev.clientX - voiceLeftDistance;          //获取圆距左端的距离
    if (d < 0) {
      d = 0;
    } else if (d > voiceRangeWidth) {

      d = voiceRangeWidth;
    }
    console.log("d: " + d);
    voiceDot.style.left = d + "px";
    voiceProgress.style.width = d + "px";
    var v = (d / voiceRangeWidth).toFixed(2);
    audio.volume = v;
  }


  var listIconPlay = document.getElementsByClassName("mod_list_iconPlay");

  var i;
  for (i = 0; i < listIconPlay.length; i++) {
    listIconPlay[i].id = i;
    listIconPlay[i].onclick = function () {
      if (listIconPlay[this.id].style.backgroundPosition != "-30px 0px") {
        listIconPlay[this.id].style.backgroundPosition = "-30px 0px";
      } else {
        listIconPlay[this.id].style.backgroundPosition = "0px 0px";

      }
    }
  }







  /*============ 歌词滚动 ============*/

  // var url = './music/音阙诗听、赵方婧 - 霜降.lrc';

  var singerName = "G.E.M.邓紫棋";
  var songName = "画 (Live Piano Session II)";


  /*==== 添加歌词 ====*/
  function addLyric(lyric) {

    var path = music[num];
    console.log(music[num]);
    console.log(path);

    var song = new Song();

    // 获取歌词
    song.getLyric(path);
    // 添加歌词到指定容器
    song.appendTo(lyric);

    /*==== 歌词高亮行数 ====*/
    var line = 0;
    var p = document.querySelectorAll('#lyric p');
    /*==== 获取行高 ====*/
    var lineHeight = $(lyric).css('line-height');
    lineHeight = parseInt(lineHeight.substring(0, lineHeight.length - 2));
    /*==== 获取下外边距 ====*/
    var marginBottom = $(p[0]).css('margin-bottom');
    marginBottom = parseInt(marginBottom.substring(0, marginBottom.length - 2));
    /*==== 计算p标签高度 ====*/
    var pHeight = lineHeight + marginBottom;


    /*==== 绑定滚动事件 ====*/
    audio.addEventListener('timeupdate', function () {
      var curTime = audio.currentTime;
      if (audio.ended === true) {
        return;
      }
      scroll(curTime);
    });

//
    /*==== 歌词滚动 ====*/
    function scroll(time) {
      if (song.isEmpty) {
        lyric.style.transform = "translateY(0px)";
        return;
      }

      var offset = -1 * line * pHeight; // 计算偏移量

      // console.log("line: " + line);

      // console.log("song.lyric.length: " + song.lyric.length);

      if (time >= song.lyric[song.lyric.length - 1].time) {
        line = song.lyric.length;
        offset = -1 * line * pHeight;
        lyric.style.transform = "translateY(" + offset + "px)";
        setHighLight(line - 1);
        return;
      }

      for (var i = 1; i < song.lyric.length; ++i) {
        if (time < song.lyric[i].time) {
          line = i - 1;
          offset = -1 * line * pHeight;
          break;
        }
      }

      if (time >= song.lyric[line].time && time < song.lyric[line + 1].time) {
        // console.log("curT: " + time + " line: " + line);
        lyric.style.transform = "translateY(" + offset + "px)";
        setHighLight(line);
        line++;
      }
    }

    /*==== 歌词高亮 ====*/
    function setHighLight(row) {
      for (var i = 0; i < p.length; ++i) {
        p[i].className = "";
      }
      p[row].className = "on";
    }

  }
//

  /*==== 清除歌词 ====*/
  function clearLyric(lyric) {
    $(lyric).empty();
  }


};

// 构造song对象
function Song(ar, ti, lrc) {
  this.artist = ar;
  this.title = ti;
  this.lyric = lrc;
  this.isEmpty = false;
}

Song.prototype = {
  constructor: Song,
  appendTo: function (pos) {                              // 添加歌词到指定容器
    while(pos.hasChildNodes()){
      pos.removeChild(pos.firstChild);
    }
    if (this.lyric === undefined) {
      this.isEmpty = true;
      var none = document.createElement('p');
      var noneText = document.createTextNode("暂无歌词");
      none.appendChild(noneText);
      pos.appendChild(none);
      return;
    }
    for (var i in this.lyric) {
      var p = document.createElement('p');
      var text = document.createTextNode(this.lyric[i].content);
      p.appendChild(text);
      pos.appendChild(p);
    }

  },
  getLyric: function (path) {       // 获取歌词
    var song = this;
    $.ajax({
      type: "get",
      url: "/lyricGet",     // 获取歌词的servlet
      data: {"path":path},   // {"singer": singerName, "song": songName}
      async: false,       // 必须为false才能接受到有效返回值
      success: function (result) {
        console.log(result);
        if (result === "null") {
          console.log("%c 获取歌词失败！", "color: #fff;background: #f00; padding:5px 0;");
          return;
        }
        console.log("%c 获取歌词成功！", "color: #0f0;background: #eee; padding:5px 0;");
        // 分割换行符，获取每行歌词和时间
        var data = result.split('\n\n');
//
//         // 获取歌手名和歌曲名
        var artist = data[0].substring(data[0].indexOf(':') + 1, data[0].indexOf(']'));
        var title = data[1].substring(data[1].indexOf(':') + 1, data[1].indexOf(']'));

        song.artist = artist;
        song.title = title;
        var lyric = [];
//         // 获取歌词和时间,data最后一行是空行
        for (var i = 2; i < data.length - 1; ++i) {
          var time = data[i].substring(data[i].indexOf('[') + 1, data[i].indexOf(']'));
          var t = parseInt(time.split(':')[0]) * 60 + parseFloat(time.split(':')[1]);
          var content = data[i].substring(data[i].indexOf(']') + 1);
          lyric.push({time: t.toFixed(3), content: content});
        }
        song.lyric = lyric;
      }
    });
  }
};




