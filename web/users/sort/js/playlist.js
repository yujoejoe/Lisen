/**
 * Created by user on 2019/12/16.
 */

$(document).ready(function () {
	var list = new List();
	list.init();
	console.log(list);
});


function List(){
  this.listId = getCookie("slId");
  this.info = [];
  this.songs = [];
  this.page = 1;
}

List.prototype = {
  constructor: List,
  init: function(){
    this.getInfo();
    this.getSongs();
    this.addInfo();
	this.addSongs();
	this.addPages();
	this.bindClick();
  },
  getInfo: function(){
    var self = this;
	$.ajax({
	  type: "get",
	  url: "/SongListGet",
	  data: {"listId": self.listId},
	  async: false,
	  success: function(result){
	    var data = JSON.parse(result);
	    if(data.success === false){
	      return ;
		}
		self.info = data.result;
	  }
	});
  },
  getSongs: function () {
    var self = this;
    // self.songs.length = 0;
	$.ajax({
	  type: "get",
	  url: "/ListGet",
	  data: {"listId": self.listId, "page": self.page},
	  async: false,
	  success: function(result){
		var data = JSON.parse(result);
		if(data.success === false){
		  return ;
		}
		self.songs = data.result;
	  }
	});
  },
  addInfo: function(){
    var topTitle = document.querySelector('title');
    topTitle.innerHTML = "哩森音乐 - " + this.info[0].name;

    // 歌单图片
    var avatar = document.querySelector(".avatar img");
    avatar.setAttribute("src", this.info[0].img);
    // 歌单标题
    var title = document.querySelector(".detail .title");
    title.innerText = this.info[0].name;
    // 作者
    var author = document.querySelector(".author span");
    author.innerText = this.info[0].author;
	// 标签
    var items = document.querySelectorAll('.info_item span');
    items[0].innerHTML = this.info[0].style;
	items[1].innerHTML = this.info[0].play;

  },
  addSongs: function(){
    var self = this;
	var song_list = document.querySelector(".song_list");
	clearChild(song_list);

	for(var i = 0; i < self.songs.length; ++i){
	  var sl_item = document.createElement('a');
	  sl_item.classList.add("song_list_item");

	  var btn = document.createElement('a');
	  btn.classList.add("song_list_play");
	  btn.setAttribute("href", "/users/playMusic/playMusic.html");

	  var tag = document.createElement('i');
	  tag.classList.add("fa", "fa-play");

	  btn.appendChild(tag);

	  var number = document.createElement('span');
	  number.classList.add("number");
	  number.index = i + 1;
	  number.innerText = number.index;

	  var slName = document.createElement('div');
	  slName.classList.add("songlist_name");
	  slName.innerText = self.songs[i].song;

	  var slSinger = document.createElement('div');
	  slSinger.classList.add("songlist_singer");
	  slSinger.innerText = self.songs[i].singer;

	  var slAlbum = document.createElement('div');
	  slAlbum.classList.add("songlist_album");
	  slAlbum.innerText = self.songs[i].album;

	  var slTime = document.createElement('div');
	  slTime.classList.add("songlist_time");
	  slTime.innerText = self.songs[i].duration;

	  // 添加子元素
	  sl_item.appendChild(btn);
	  sl_item.appendChild(number);
	  sl_item.appendChild(slName);
	  sl_item.appendChild(slSinger);
	  sl_item.appendChild(slAlbum);
	  sl_item.appendChild(slTime);

	  // 跳转到播放音乐页面
	  sl_item.setAttribute("href", "/users/playMusic/playMusic.html");

	  song_list.appendChild(sl_item);
	}
  },
  addPages: function(){
    var self = this;
    var pageBox = document.querySelector('#pageBox');
	clearChild(pageBox);

	var size = Math.ceil(self.songs.length / 10);

	var index = 0;
    for(var i = 0; i < size - 1; ++i){
      var page = document.createElement('span');
      page.classList.add("page_index");
      if(i === 0){
        page.classList.add("selected");
	  }
	  page.innerHTML = i + 1;
      page.index = i;
      pageBox.appendChild(page);
	}
  },
  bindClick: function(){
    var self = this;
    var pages = document.querySelector('#pageBox').querySelectorAll('.page_index');

    var index = 0;
    for(var i = 0; i < pages.length; ++i){
      pages[i].onclick = function(){
        pages[index].classList.remove("selected");
        pages[this.index].classList.add("selected");
        index = this.index;
        self.page = this.innerHTML;
        self.getSongs();
        self.addSongs();
	  }
	}
  }
};

