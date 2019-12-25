/**
 * Created by user on 2019/12/6.
 */


function MVP() {
  this.mId = getCookie("mId");									// 视频id
  this.videoInfo = [];											// 接受后台发送的视频信息
  this.cmts = [];												// 接受发送的评论信息
  // 视频
  this.duration = 0;											// 视频总时长
  this.isPlay = false;											// 判断当前是否在播放状态
  this.vPro = document.querySelector(".progress");		        // 视频进度条
  this.playBtn = document.querySelector("#icoPlay");			// 播放按钮
  this.vDot = document.querySelector(".v_dot");					// 视频小圆点
  this.video = document.querySelector("#mvPlay video");			// 视频
  this.vProInner = document.querySelector(".progress .progress_inner");
  // 声音部分
  this.volPro = document.querySelector(".vol_progress");	// 声音进度条
  this.volProInner = document.querySelector(".vol_progress_inner");
  this.volDot = document.querySelector(".volDot");
  this.volumeBtn = document.querySelector("#icoVolume");	    // 声音按钮
  this.fullscreenBtn = document.querySelector("#icofullscreen");// 全屏按钮
  this.dmBtn = document.querySelector(".dmWrap button");		// 发送弹幕按钮
  this.dmInput = document.querySelector(".dmWrap input");		// 弹幕输入框
  // 弹幕
  this.barrageCont = document.querySelector(".mv_danmu");
  this.dmPool = [
    "你指尖跃动的电光，是我此生不变的信仰！",
	"樱花满地集于我心，楪舞纷飞祈愿相随！",
	"2333~"
  ];
  this.domPool = [];
  this.cmtInput = document.querySelector("#cmt cmt_input");		// 评论输入框
}

MVP.prototype = {
  constructor: MVP,
  init: function () {
	var data = this.getData();
	this.videoInfo = data.videoInfo;
	this.addVideo();
	this.addInfo();
  },
  getData: function () {
	var data;
	var self = this;
	$.ajax({
	  type: "get",
	  url: "/videoGet",
	  data: {"mId": self.mId},
	  async: false,
	  success: function (result) {
		data = JSON.parse(result);

		console.log(data);
	  }
	});
	return data;
  },
  addVideo: function () {
    let self = this;
	this.video.setAttribute('src', this.videoInfo[0].url);
	this.video.addEventListener('canplay', function () {
	  this.muted = false;
	  this.play();
	  self.playBtn.classList.remove("fa-play");
	  self.playBtn.classList.add("fa-pause");
	  self.isPlay = true;
	})
  },
  addInfo: function () {
	var mv_title = document.querySelector(".mv_title");
	var mv_listen = document.querySelector(".mv_listen");

	// 歌曲标题
	mv_title.innerHTML = this.videoInfo[0].singer + " - " + this.videoInfo[0].title;
	mv_title.setAttribute('title', this.videoInfo[0].title);

	// 播放量
	var str = this.videoInfo[0].play > 10000 ? ((this.videoInfo[0].play / 10000).toFixed(1)) : this.videoInfo[0].play;
	mv_listen.innerHTML = "播放量：" + str + "万";

	// 视频简介
	var mv_singer = document.querySelector(".mv_singer");
	var mv_date = document.querySelector(".mv_info_date span");
	mv_singer.innerHTML = this.videoInfo[0].singer;
	mv_date.innerHTML = this.videoInfo[0].date;
  },
  addBarrage: function(){
	var self = this;
	var channel = 3;
	var counts = 3;

	for(var i = 0; i < channel; ++i){
	  var dom = [];
	  for(var j = 0; j < counts; ++j){
	    var dm = document.createElement('div');
	    dm.classList.add("barrage");
	    dm.style.top = i * 20 + 'px';
	    dm.addEventListener('transitionend', function () {
		  this.style.transform = null;
		});
	    dom.push(dm);
	    self.barrageCont.appendChild(dm);
	  }
	  self.domPool.push(dom);
	}
  },
  timeUpdate: function () {
	var self = this;

	// 视频剩余时间文本容器
	var timeLeft = document.querySelector(".v_time .time_left");

	// 视频总时间文本容器
	var timeDuration = document.querySelector('.v_time .time_duration');

	// 获取视频总时长
	this.video.addEventListener('canplay', function () {
	  self.duration = this.duration;
	  var duration = self.duration;     		// duration 以秒计时
	  var M = parseInt(duration / 60) % 60;
	  M = M < 10 ? ('0' + M) : M;
	  var S = parseInt(duration % 60);
	  S = S < 10 ? ('0' + S) : S;
	  timeDuration.innerHTML = M + ":" + S;
	});

	// 添加监听事件
	this.video.addEventListener('timeupdate', function () {
	  var curT = this.currentTime;
	  // 分钟
	  var m = parseInt(curT / 60) % 60;
	  m = m < 10 ? ('0' + m) : m;
	  // 秒
	  var s = parseInt(curT % 60);
	  s = s < 10 ? ('0' + s) : s;
	  //写入剩余时间
	  timeLeft.innerHTML = m + ":" + s;

	  // 进度条
	  var proWidth = $($(self.vPro)).css('width');			// 进度条总宽度
	  proWidth = parseInt(proWidth.match(/\d+/)[0]);
	  var curWidth = curT / self.duration * proWidth;		// 当前宽度
	  $(self.vProInner).css('width', curWidth + 'px');

	  // 小圆点
	  var dotWidth = $(self.vDot).css('width').match(/\d+/)[0];
	  dotWidth = parseInt(dotWidth);
	  $(self.vDot).css('left', (curWidth - dotWidth / 2) + 'px');	// 小圆点left值等于进度条当前宽度 - 半径
	});
  },
  event: function(){						// 绑定各种简单的事件
    var self = this;

    // 播放按钮
	this.playBtn.onclick = function () {
	  if (!self.isPlay) {
		self.video.play();
		this.classList.remove("fa-play");
		this.classList.add("fa-pause");
		self.isPlay = true;
	  } else {
		self.video.pause();
		this.classList.remove("fa-pause");
		this.classList.add("fa-play");
		self.isPlay = false;
	  }
	};


	// 全屏按钮
	// this.fullscreenBtn.onclick = function(){
	//
	// };


	// 点击播放进度条
	this.vPro.onmousedown = videoEvent;
	// 声音
	this.volPro.onmousedown = volumeEvent;

	// 弹幕输入框
	this.dmInput.onfocus = function () {
	  this.placeholder = "";
	};
	this.dmInput.onblur = function () {
	  this.placeholder = "发送弹幕~";
	};

	// 发送弹幕
	// 点击发送
	this.dmBtn.onclick = function(){
	  var barrage = self.dmInput.value;
	  if(barrage !== ""){
	    var dm = document.createElement('div');
	    var videoWidth = $(self.video).css('width').match(/\d+/g)[0];		// 视频宽度
		videoWidth = parseInt(videoWidth);
		// console.log("videoWidth: " + videoWidth);
	    dm.classList.add("barrage");
	    dm.innerHTML = barrage;
	    dm.style.top = parseInt(Math.random()*100 + 10) + 'px';
		self.barrageCont.appendChild(dm);			// 添加弹幕

		// var dmWidth = $(dm).css('width').match(/\d+/g)[0];			// 获取文本宽度
		// dmWidth = parseInt(dmWidth);
		// console.log("dmWidth: " + dmWidth);
		//
		// var X = videoWidth + dmWidth + 50;
		// dm.style.transform = "translateX(-" + X + "px)";

		////绑定过渡时间结束事件
		// dm.addEventListener('transitionend', function(){
		//   setTimeout(function () {
		// 	dm.style.left = "100%";
		// 	dm.style.transform = "translateX(-" + X + "px)";
		//   }, 1000);
		// });

	    self.dmInput.value = "";
	  }
	};
	// 回车发送
	this.dmInput.addEventListener('keyup', function(e){
		if(e.key === 'Enter'){
		  self.dmBtn.onclick();
		}
	});

	function videoEvent(e){
	  var videoWidth = $('.mv_inner').css('width').match(/\d+/)[0];
	  videoWidth = parseInt(videoWidth);
	  var marginLeft = (document.body.clientWidth - videoWidth) / 2;		// 获取左外边距
	  // 修改进度条宽度
	  var curWidth = e.clientX - marginLeft;			// 当前宽度
	  $(self.vProInner).css('width', curWidth+'px');
	  // 修改小圆点left值
	  var dotWidth = $(self.vDot).css('width').match(/\d+/)[0];
	  dotWidth = parseInt(dotWidth);
	  $(self.vDot).css('left', (curWidth - dotWidth / 2) + 'px');
	  // 修改当前播放时间
	  self.video.currentTime = self.duration * (curWidth / videoWidth);
	  // 播放视频
	  self.video.play();
	  self.playBtn.classList.remove("fa-play");
	  self.playBtn.classList.add("fa-pause");
	  self.isPlay = true;
	}

	function volumeEvent(e){

	  var volProBgHeight = $(".vol_progress_bg").css('height').match(/\d+/g)[0];	// 实际高度
	  volProBgHeight = parseInt(volProBgHeight);
	  var volProHeight = $(".vol_progress").css('height').match(/\d+/g)[0];		// 总高度
	  volProHeight = parseInt(volProHeight);
	  var offset = volProHeight - volProBgHeight;									// 高度差
	  // console.log("offset： " + offset);
	  var rec = self.volPro.getBoundingClientRect();
	  var volHeight = parseInt(e.clientY) - parseInt(Math.floor(rec.top)) - offset / 2;
	  volHeight = volHeight < 0 ? 0 : volHeight;
	  volHeight = volHeight > volProBgHeight ? volProBgHeight : volHeight;
	  volHeight = volProBgHeight - volHeight;

	  self.volProInner.style.height = volHeight + 'px';

	  self.video.volume = (volHeight / volProBgHeight).toFixed(1);
	}

  },
  shutBarrage:function () {
	var self = this;

  }
};


window.onload = function () {
  var mvp = new MVP();
  mvp.init();
  var htmlTitle = document.querySelector('title');
  htmlTitle.innerHTML = mvp.videoInfo[0].singer + " - " + mvp.videoInfo[0].title;

  mvp.timeUpdate();
  mvp.event();

  var heart = document.querySelector(".mv_btn .fa-heart");
  var click = false;
  heart.onclick = function(){
    if(click){
      heart.style.color = "#fff";
      click = false;
	}else{
      heart.style.color = "#31c27c";
      click = true;
	}
  };

  var fakeInput = document.querySelector("#fake_input");
  var trueInput = document.querySelector("#true_input");
  trueInput.style.display = "none";
  trueInput.style.color = "#333";

  fakeInput.onfocus = function () {
	this.style.display = "none";
	trueInput.style.display = "block";
	trueInput.focus();
  };
  trueInput.onblur = function () {
	this.style.display = "none";
	fakeInput.style.display = "block";
  }

};
