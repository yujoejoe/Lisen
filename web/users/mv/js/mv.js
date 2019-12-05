/**
 * Created by user on 2019/11/19.
 */

document.onreadystatechange = loading;


function MV() {
  this.area = "全部";
  this.version = "全部";
  this.page = 1;
  this.content = [];
  this.isEmpty = true;
}

MV.prototype = {
  constructor: MV,
  init: function () {
	var self = this;
	var data = this.getData();

	// 获取mv顶部标签容器
	var tags = document.getElementById('tags');
	clearChild(tags);

	// console.log("tags: " + tags);

	// 创建"区域"标签和"版本"标签
	var areaTag = document.createElement('div');
	var versionTag = document.createElement('div');
	areaTag.className = "tag";
	versionTag.className = "tag";
	// 创建小标题
	var title1 = document.createElement('h3');
	title1.className = "tag_title";
	title1.innerHTML = "区域";
	var title2 = document.createElement('h3');
	title2.className = "tag_title";
	title2.innerHTML = "版本";
	// 创建a标签
	var all1 = document.createElement('a');
	all1.classList.add("tag_item", "selected");
	all1.setAttribute('href', "javascript:;");
	all1.innerHTML = "全部";
	var all2 = document.createElement('a');
	all2.classList.add("tag_item", "selected");
	all2.setAttribute('href', "javascript:;");
	all2.innerHTML = "全部";

	// 添加子元素
	areaTag.appendChild(title1);
	versionTag.appendChild(title2);
	areaTag.appendChild(all1);
	versionTag.appendChild(all2);

	// console.log("areaTag" + areaTag);
	// console.log("versionTag" + versionTag);


	if (data.success === false) {
	  console.log("failure");
	  self.content = null;
	  return;
	}

	if (self.isEmpty) {
	  for (var i = 0; i < data.area.length; ++i) {
		var aTag = document.createElement('a');
		aTag.className = "tag_item";
		aTag.setAttribute('href', "javascript:;");
		aTag.innerHTML = data.area[i];
		areaTag.appendChild(aTag);
	  }

	  for (var i = 0; i < data.version.length; ++i) {
		var aTag = document.createElement('a');
		aTag.className = "tag_item";
		aTag.setAttribute('href', "javascript:;");
		aTag.innerHTML = data.version[i];
		versionTag.appendChild(aTag);
	  }
	  // 把标签放入tags容器
	  tags.appendChild(areaTag);
	  tags.appendChild(versionTag);
	  self.isEmpty = false;
	}

	var page = document.createElement('div');
	page.className = "page";


	self.resetList(data);

  },
  appendTo: function (pos) {
	var self = this;

	// 先清空容器原有数据
	clearChild(pos);

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
	  itemAuthor.innerHTML = self.content[i].singer;
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
	  pos.appendChild(liItem);
	}
  },
  resetList: function (data) {
    console.log(data);

	if (data.result.length === 0) {
	  return;
	}
	for (var j = 0; j < data.result.length; ++j) {
	  this.content.push({
		"img": data.result[j].img,
		"date": data.result[j].date,
		"play": (data.result[j].play / 10000).toFixed(1) + 'w',
		"title": data.result[j].title,
		"singer": data.result[j].singer,
		"url": data.result[j].url
	  });
	}
  },
  getData: function(){
    var data;
	$.ajax({
	  type: "get",
	  url: "/infoGet",
	  data: {"area": this.area, "version": this.version, "page": this.page},
	  async: false,
	  success: function (result) {

		// console.log(result);

		data = JSON.parse(result);
	  }
	});
	return data;
  }
};


// 页面加载事件
function loading() {
  var load = document.querySelector('.loading');

  if (document.readyState === "complete") {
	load.style.display = "none";
  } else {
	load.style.display = "block";
  }
}


window.onload = function () {

  var mv = new MV();
  mv.init();
  // console.log(mv);
  var mvList = document.getElementById('mv_list');
  mv.appendTo(mvList);

  var area = "全部";
  var version = "全部";
  var page = 1;

  var areaTags = document.getElementById('tags').firstChild.children;
  var versionTags = document.getElementById('tags').lastChild.children;

  bindEvent(areaTags, "area");
  bindEvent(versionTags, "version");



  function getData(area, version, page) {
	$.ajax({
	  type: "get",
	  url: "/infoGet",
	  data: {"area": area, "version": version, "page": page},
	  success: function (result) {
		var data = JSON.parse(result);
		if (data.success === false) {
		  console.log("failure");
		  mv.content.length = 0;
		}else{
		  mv.resetList(data);
		}
	  }
	});
  }

  function bindEvent(ele, type) {
	var index = 1;
	for(var i = 1; i < ele.length; ++i){
	  ele[i].id = i;
	  ele[i].onclick = function(){
		ele[index].classList.remove("selected");
		this.classList.add("selected");
		index = this.id;

		if(type === "area"){
		  area = this.innerText;
		}else if(type === "version"){
		  version = this.innerText;
		}else if(type === "page"){
		  page = this.innerText;
		}

		getData(area, version, page);

	  }
	}
  }

};

function clearChild(parent) {
  while (parent !== null && parent.hasChildNodes()) {
	parent.removeChild(parent.firstChild);
  }
}



