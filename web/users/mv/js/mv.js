/**
 * Created by user on 2019/11/19.
 */

function MV() {
  this.page = 0;		// 0 表示获取数据库中的全部记录
  this.size = 8;		// 每页显示的数量
  this.order = 0;		// 排序方式（默认为0）： 0 按时间排序  1 按播放量排序
  this.counts = 1;		// 数据库中总记录数
  this.area = null;		// 区域默认为“全部”
  this.content = [];	// 从数据库获取到的内容
  this.isEmpty = true;
  this.version = null;	// 版本默认为“全部”
  this.mvList = document.getElementById('mv_list');
  this.tags = document.querySelector(".tags");
}

MV.prototype = {
  constructor: MV,
  init: function () {
    // 获取数据
	var data = this.getMV();
	var area = this.getArea();
	var version = this.getVersion();

	// 添加标签
	if(data !== undefined) {
	  this.reset(data);
	  this.appendMV();
	  this.addPage();
	}
	if(area !== undefined && version !== undefined) {
	  this.addTag(area, version);
	}
  },
  getMV: function () {				// 从数据库获取数据
	var data;
	// 获取数据
	$.ajax({
	  type: "get",
	  url: "/mvGet",
	  data: {"area": this.area, "version": this.version, "page": this.page, "order": this.order},
	  async: false,
	  success: function (result) {
		// console.log(result);
		data = JSON.parse(result);
	  }
	});
	return data;
  },
  getArea: function(){
    var area;
	$.ajax({
	  type: "get",
	  async: false,
	  url: "/Area",
	  success: function(result){
		area = JSON.parse(result);
	  }
	});
	return area;
  },
  getVersion: function(){
	var version;
	$.ajax({
	  type: "get",
	  async: false,
	  url: "/Version",
	  success: function (result) {
		version = JSON.parse(result);
	  }
	});
	return version;
  },
  clear: function () {
	this.content.length = 0;
  },
  reset: function (data) {					// 重置MV的各个属性
	console.log(data);

	if (data.success === false || data.result.length === 0) {
	  return;
	}
	this.counts = data.counts;

	var size = data.result.length > this.size ? this.size : data.result.length;

	for (var j = 0; j < size; ++j) {
	  var play = parseInt(data.result[j].play);
	  play = play >= 10000 ? ((play/10000).toFixed(1) + '万') : play;
	  this.content.push({
		"img": data.result[j].img,
		"date": data.result[j].date,
		"play": play,
		"title": data.result[j].title,
		"author": data.result[j].singer,
		"url": data.result[j].url,
		"id": data.result[j].id
	  });
	}
  },
  addTag: function (area, version) {
	var self = this;
	// 获取mv顶部标签容器
	clearChild(this.tags);
	// 创建"区域"标签和"版本"标签
	var areaTag = document.createElement('div');
	areaTag.setAttribute("id", "area");
	areaTag.className = "tag";
	var versionTag = document.createElement('div');
	versionTag.setAttribute("id", "version");
	versionTag.className = "tag";
	// 创建小标题
	var areaTitle = document.createElement('h3');
	areaTitle.className = "tag_title";
	areaTitle.innerHTML = "区域";
	var versionTitle = document.createElement('h3');
	versionTitle.className = "tag_title";
	versionTitle.innerHTML = "版本";
	// 创建a标签
	var areaAll = document.createElement('a');
	areaAll.classList.add("tag_item", "selected");
	areaAll.setAttribute('href', "javascript:;");
	areaAll.innerHTML = "全部";

	var versionAll = document.createElement('a');
	versionAll.classList.add("tag_item", "selected");
	versionAll.setAttribute('href', "javascript:;");
	versionAll.innerHTML = "全部";

	// 添加子元素
	areaTag.appendChild(areaTitle);
	areaTag.appendChild(areaAll);
	versionTag.appendChild(versionTitle);
	versionTag.appendChild(versionAll);

	if(version.result !== undefined) {
	  for (var i = 0; i < version.result.length; ++i) {
		var aTag = document.createElement('a');
		aTag.className = "tag_item";
		aTag.setAttribute('href', "javascript:;");
		aTag.innerHTML = version.result[i];
		versionTag.appendChild(aTag);
	  }
	}

	if (area.result !== undefined) {
	  var index = 0;
	  for (var i = 0; i < area.result.length; ++i) {
		var aTag = document.createElement('a');
		aTag.className = "tag_item";
		aTag.setAttribute('href', "javascript:;");
		aTag.innerHTML = area.result[i];
		areaTag.appendChild(aTag);
	  }
	}
	// 把标签放入tags容器
	self.tags.appendChild(areaTag);
	self.tags.appendChild(versionTag);
  },
  appendMV: function () {
	var self = this;

	// 先清空容器原有数据
	clearChild(this.mvList);

	for (var i = 0; i < self.content.length; ++i) {
	  //1、 创建li
	  var liItem = document.createElement('li');
	  liItem.className = "list_item";
	  //1.1、 创建li内部容器
	  var liBox = document.createElement('div');
	  liBox.className = "list_box";
	  //1.1.1、 创建图片容器
	  var itemBox = document.createElement('a');
	  itemBox.className = "item_box";
	  itemBox.setAttribute('href', "./playvideo.html");
	  // 绑定点击事件设置cookie
	  itemBox.setAttribute('data-id', self.content[i].id);
	  itemBox.onclick = setCookie;

	  //1.1.1.1、 创建图片
	  var itemImg = document.createElement('img');
	  itemImg.className = "item_img";
	  itemImg.setAttribute('src', self.content[i].img);
	  //1.1.1.2、 创建播放图标
	  var iconPlay = document.createElement('i');
	  iconPlay.className = "icon-play";
	  // 把图片和播放图标添加到图片容器
	  itemBox.appendChild(itemImg);
	  itemBox.appendChild(iconPlay);
	  //1.1.2、 创建标题
	  var itemTitle = document.createElement('a');
	  itemTitle.className = "item_title";
	  itemTitle.innerHTML = self.content[i].title;
	  //1.1.3、 创建歌手
	  var itemAuthor = document.createElement('a');
	  itemAuthor.className = "item_author";
	  itemAuthor.innerHTML = self.content[i].author;
	  //1.1.4、 创建播放信息
	  var itemInfo = document.createElement('div');
	  itemInfo.className = "item_info";
	  //1.1.4.1、 播放量
	  var itemPlay = document.createElement('span');
	  itemPlay.className = "item_play";
	  //1.1.4.1.1、 播放图标
	  var itemPlayIcon = document.createElement('i');
	  itemPlayIcon.className = "fa fa-video-camera item_play_icon";
	  //1.1.4.1.2、 播放量文本
	  var text = document.createTextNode(self.content[i].play);
	  // 添加播放量
	  itemPlay.appendChild(itemPlayIcon);
	  itemPlay.appendChild(text);
	  //1.1.4.2、 发布时间
	  var date = document.createElement('span');
	  date.innerHTML = self.content[i].date;
	  // 把播放量和发布日期添加到播放信息
	  itemInfo.appendChild(itemPlay);
	  itemInfo.appendChild(date);
	  // 把图片容器、标题、歌手、播放信息添加到li内部容器
	  liBox.appendChild(itemBox);
	  liBox.appendChild(itemTitle);
	  liBox.appendChild(itemAuthor);
	  liBox.appendChild(itemInfo);
	  // 把li内部容器添加到li中
	  liItem.appendChild(liBox);
	  // 最后把li添加到指定容器
	  this.mvList.appendChild(liItem);
	}
  },
  addPage: function () {
	var self = this;
	var pageBox = document.getElementById('pageBox');
	clearChild(pageBox);
	var size = Math.ceil(this.counts / this.size);
	// console.log("pages: " + size);
	if (size > 1) {
	  pageBox.style.display = "block";
	  // 上一页
	  var prev = document.createElement('span');
	  prev.classList.add("page_index");
	  prev.innerHTML = "<";
	  prev.style.display = "none";
	  pageBox.appendChild(prev);

	  // 下一页
	  var next = document.createElement('span');
	  next.classList.add("page_index");
	  next.innerHTML = ">";
	  next.style.display = "none";
	  if (size < 3) {
		for (var i = 1; i <= size; ++i) {
		  var page = document.createElement('a');
		  page.classList.add("page_index");
		  if (i === 1) {
			page.classList.add("selected");
		  }
		  page.innerHTML = i;
		  pageBox.appendChild(page);
		}
	  } else {
		next.style.display = "inline-block";
		for (var i = 1; i <= 3; ++i) {
		  var page = document.createElement('a');
		  page.classList.add("page_index");
		  if (i === 1) {
			page.classList.add("selected");
		  }
		  page.innerHTML = i;
		  pageBox.appendChild(page);
		}
		// 更多
		var more = document.createElement('a');
		more.classList.add("page_more");
		more.innerHTML = "...";
		pageBox.appendChild(more);
		// // 最大页数
		// var pageMax = document.createElement('a');
		// pageMax.classList.add("page_index");
		// pageMax.innerHTML = size;
		// pageBox.appendChild(pageMax);
	  }
	  // 下一页
	  pageBox.appendChild(next);
	}

	var pages = document.querySelectorAll('#pageBox a');
	// console.log("pages: " + pages);
	var index = 0;
	for (var k = 0; k < pages.length; ++k) {
	  pages[k].index = k;
	  pages[k].onclick = function () {
		pages[index].classList.remove("selected");
		pages[this.index].classList.add("selected");
		self.page = this.innerHTML;
		self.clear();
		self.reset(self.getMV());
		self.appendMV();
		index = this.index;
	  }
	}
  },
  bindClick: function(){
    var self = this;
    var areaTags = this.tags.firstElementChild.querySelectorAll('a');
    var versionTags = this.tags.lastElementChild.querySelectorAll('a');

    click(areaTags, "area");
    click(versionTags, "version");
    function click(ele, type){
		var index = 0;
		if(typeof ele === "object"){
		  for(var i = 0; i < ele.length; ++i){
		    ele[i].index = i;
		    ele[i].onclick = function () {
			  ele[index].classList.remove("selected");
			  ele[this.index].classList.add("selected");
			  index = this.index;
			  
			  if (type === "area") {
				self.area = this.innerText === "全部" ? "" : this.innerText;
			  } else if (type === "version") {
				self.version = this.innerText === "全部" ? "" : this.innerText;
			  }
			  self.page = 0;
			  self.clear();
			  self.reset(self.getMV());
			  self.addPage();
			  self.appendMV();
			}
		  }
		}
	}
  }
};

function clearChild(parent) {
  while (parent !== null && parent.hasChildNodes()) {
	parent.removeChild(parent.firstChild);
  }
}

function setCookie() {
  document.cookie = "mId=" + this.getAttribute('data-id') + ";path=/";
  console.log(document.cookie);
}


$(document).ready(function () {
  var mv = new MV();
  mv.init();
  mv.bindClick();
// console.log(mv);
// console.log(mvList);
// console.log(mv);

  var newest = document.getElementById("newest");
  var hottest = document.getElementById("hottest");

  newest.onclick = function () {
	this.classList.add("selected");
	hottest.classList.remove("selected");
	mv.order = 0;
	mv.page = 0;
	query(mv);
  };
  hottest.onclick = function () {
	this.classList.add("selected");
	newest.classList.remove("selected");
	mv.order = 1;
	mv.page = 0;
	query(mv);
  };

  function query(mv, flag) {
	if (mv.constructor !== MV) {
	  return;
	}
	// 1、先清除mv的内容
	mv.clear();
	// 2、重新获取数据重置mv的内容
	var data = mv.getMV();
	if(data !== undefined) {
	  mv.reset(data);
	  // 3、把mv的内容添加到容器
	  mv.appendMV();
	  // 4、添加分页
	  mv.addPage();
	}
  }
});




