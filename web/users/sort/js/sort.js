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
  init: function(){
        var type = this.getType();
        var theme = this.getTheme();
        console.log(type);
        console.log(theme);
        this.addTag(type, theme);
  },
  getType: function(){
    var type;
    $.ajax({
      type: "get",
      url: "/Style",
      async: false,
      success: function(result){
        type = JSON.parse(result);
      }
    });
    return type;
  },
  getTheme: function(){
	var theme;
	$.ajax({
	  type: "get",
	  url: "/Theme",
	  async: false,
	  success: function(result){
		theme = JSON.parse(result);
	  }
	});
	return theme;
  },
  getList: function(){
	var data;
	$.ajax({
	  type: "get",
	  url: "/Theme",
	  async: false,
	  success: function(result){
		data = JSON.parse(result);
	  }
	});
	return data;
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

	if(theme.result !== undefined) {
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
  addItem: function(data){

  }

};

function clearChild(parent) {
  while (parent !== null && parent.hasChildNodes()) {
	parent.removeChild(parent.firstChild);
  }
}

$(document).ready(function () {
  var sort = new Sort();
  sort.init();
});