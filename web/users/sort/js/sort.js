function Sort() {
  this.size = 8;		// 每页显示的数量
  this.order = 0;		// 排序方式（默认为0）： 0 按时间排序  1 按播放量排序
  this.type = null;
  this.theme = null;
  this.content = [];	// 从数据库获取到的内容
  this.tags = document.querySelector(".tags");
  this.playList = document.querySelector(".play_list");
}

Sort.prototype = {
  constructor: Sort,
  init: function () {
	// 获取数据
	this.getList();
	var type = this.getType();
	var theme = this.getTheme();
	// console.log(type);
	// console.log(theme);

	// 添加数据
	this.addItem();
	this.addTag(type, theme);
  },
  getType: function () {
	var type;
	$.ajax({
	  type: "get",
	  url: "/Style",
	  async: false,
	  success: function (result) {
		type = JSON.parse(result);
	  }
	});
	return type;
  },
  getTheme: function () {
	var theme;
	$.ajax({
	  type: "get",
	  url: "/Theme",
	  async: false,
	  success: function (result) {
		theme = JSON.parse(result);
	  }
	});
	return theme;
  },
  getList: function () {
	var data;
	this.content.length = 0;
	// 获取数据
	$.ajax({
	  type: "get",
	  url: "/SongListGet",
	  data: {"style": this.style, "theme": this.theme, "order": this.order},
	  async: false,
	  success: function (result) {
		data = JSON.parse(result);
		console.log(data);
	  }
	});
	// 存储数据
	if (data.success === false || data.result.length === 0) {
	  return;
	}
	for (var j = 0; j < data.result.length; ++j) {
	  var play = parseInt(data.result[j].play);
	  play = play >= 10000 ? ((play/10000).toFixed(1) + '万') : play;
	  this.content.push({
		"img": data.result[j].img,
		"play": play,
		"title": data.result[j].name,
		"author": data.result[j].author,
		"id": data.result[j].id
	  });
	}
  },
  addTag: function (type, theme) {
	var self = this;
	// 获取mv顶部标签容器
	clearChild(this.tags);
	// 创建"区域"标签和"版本"标签
	var areaTag = document.createElement('div');
	areaTag.setAttribute("id", "type");
	areaTag.className = "tag";
	var versionTag = document.createElement('div');
	versionTag.setAttribute("id", "theme");
	versionTag.className = "tag";
	// 创建小标题
	var areaTitle = document.createElement('h3');
	areaTitle.className = "tag_title";
	areaTitle.innerHTML = "风格";
	var versionTitle = document.createElement('h3');
	versionTitle.className = "tag_title";
	versionTitle.innerHTML = "主题";

	// 添加子元素
	areaTag.appendChild(areaTitle);
	versionTag.appendChild(versionTitle);

	if (theme.result !== undefined) {
	  for (var i = 0; i < theme.result.length; ++i) {
		var aTag = document.createElement('a');
		aTag.className = "tag_item";
		aTag.setAttribute('href', "javascript:;");
		aTag.innerHTML = theme.result[i];
		versionTag.appendChild(aTag);
	  }
	}

	if (type.result !== undefined) {
	  var index = 0;
	  for (var i = 0; i < type.result.length; ++i) {
		var aTag = document.createElement('a');
		aTag.className = "tag_item";
		aTag.setAttribute('href', "javascript:;");
		aTag.innerHTML = type.result[i];
		areaTag.appendChild(aTag);
	  }
	}
	// 把标签放入tags容器
	self.tags.appendChild(areaTag);
	self.tags.appendChild(versionTag);
  },
  addItem: function () {
	var self = this;

	// 先清空容器原有数据
	clearChild(this.playList);

	for (var i = 0; i < self.content.length; ++i) {
	  //1、 创建li
	  var liItem = document.createElement('li');
	  liItem.classList.add("list_item", "song_list_item");
	  //1.1、 创建li内部容器
	  var liBox = document.createElement('div');
	  liBox.classList.add("list_box");
	  //1.1.1、 创建图片容器
	  var itemBox = document.createElement('a');
	  itemBox.classList.add("item_box","song_item_box");
	  itemBox.setAttribute('href', "./playlist.html");				// 点击跳转目标页面
	  // 绑定点击事件设置cookie
	  itemBox.setAttribute('data-id', self.content[i].id);			// 此处设置cookie，目标页面读取cookie
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

	  //1.1.4.1.2、 播放量文本
        var text = document.createTextNode("播放量：" + self.content[i].play);
	  // 添加播放量

	  itemPlay.appendChild(text);
	  // 把播放量和发布日期添加到播放信息
	  itemInfo.appendChild(itemPlay);
	  // 把图片容器、标题、歌手、播放信息添加到li内部容器
	  liBox.appendChild(itemBox);
	  liBox.appendChild(itemTitle);
	  liBox.appendChild(itemAuthor);
	  liBox.appendChild(itemInfo);
	  // 把li内部容器添加到li中
	  liItem.appendChild(liBox);
	  // 最后把li添加到指定容器
	  this.playList.appendChild(liItem);
	}
  },
  bindClick: function () {
	var self = this;
	var areaTags = this.tags.firstElementChild.querySelectorAll('a');
	var versionTags = this.tags.lastElementChild.querySelectorAll('a');
	click(areaTags, "style");
	click(versionTags, "theme");

	var newest = document.getElementById("newest");
	newest.index = 0;
	var hottest = document.getElementById("hottest");
	hottest.index = 1;

	btnClick(newest, hottest);

	function btnClick(first, second) {
	  first.onclick = function () {
		first.classList.add("selected");
		second.classList.remove("selected");
		self.order = first.index;
		// 重新获取数据并添加
		self.getList();
		self.addItem();
	  };
	  second.onclick = function () {
		second.classList.add("selected");
		first.classList.remove("selected");
		self.order = first.index;
		// 重新获取数据并添加
		self.getList();
		self.addItem();
	  }
	}

	function click(ele, type) {
	  var index = 0;
	  if (typeof ele === "object") {
		for (var i = 0; i < ele.length; ++i) {
		  ele[i].index = i;
		  ele[i].onclick = function () {
			ele[index].classList.remove("selected");
			ele[this.index].classList.add("selected");
			index = this.index;

			if (type === "style") {
			  self.style = this.innerText;
			} else if (type === "theme") {
			  self.theme = this.innerText;
			}

			// 重新获取数据并添加
			self.getList();
			self.addItem();
		  }
		}
	  }
	}
  }
};

function setCookie() {
  var cName = "slId";
  document.cookie = cName + "=" + this.getAttribute('data-id') + ";path=/";
  console.log(document.cookie);
}



$(document).ready(function () {
  var sort = new Sort();
  sort.init();
  sort.bindClick();
});