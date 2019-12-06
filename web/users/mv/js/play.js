/**
 * Created by user on 2019/12/6.
 */

document.onreadystatechange = loading;

// 页面加载事件
function loading() {
  var load = document.querySelector('.loading');

  if (document.readyState === "complete") {
	load.style.display = "none";
  } else {
	load.style.display = "block";
  }
}


function MVP(){
  this.mId = document.cookie.split(";")[1].split("=")[1];
  this.videoInfo = [];
  this.cmts = [];
}

MVP.prototype = {
  constructor: MVP,
  init: function(){
    var data = this.getData();
    this.videoInfo = data.videoInfo;
	this.addVideo();
  },
  addVideo: function(){
	var mvPlay = document.querySelector("#mvPlay");
	var video = document.createElement('video');
	video.setAttribute('src', this.videoInfo[0].url);
	video.play();
	mvPlay.appendChild(video);
  },
  getData: function(){
    var data;
    var self = this;
    $.ajax({
	  type: "get",
	  url: "/videoGet",
	  data: {"mId": self.mId},
	  async: false,
	  success: function(result){
	    data = JSON.parse(result);

	    console.log(data);
	  }
	});
    return data;
  }
};


window.onload = function(){
  var mvp = new MVP();
  mvp.init();
};
