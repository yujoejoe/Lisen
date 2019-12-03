/**
 * Created by user on 2019/11/19.
 */


document.onreadystatechange = loading;





function MV(area, version, page){
  this.area = area;
  this.version = version;
  this.page = 1;
  this.content = [];
  this.isEmpty = false;
}

MV.prototype = {
  constructor: MV,
  init: function(){
    var self = this;

    // 获取mv顶部标签容器
	var tags = document.getElementById('tags');
	while(tags.hasChildNodes()){
	  tags.removeChild(tags.firstChild);
    }

    console.log("tags: " + tags);

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
	var all = document.createElement('a');
	all.className = "tag_item";
	all.setAttribute('href',"javascript:;");
	all.innerHTML = "全部";

	// 添加子元素
	areaTag.appendChild(title1);
	versionTag.appendChild(title2);
    areaTag.appendChild(all);
    versionTag.appendChild(all);

    // console.log("areaTag" + areaTag);
    // console.log("versionTag" + versionTag);



    $.ajax({
      type: "get",
      url: "/infoGet",
      data: {"area": "all", "version": "all", "page": this.page},
      async: false,
      success: function(result){

        var data = JSON.parse(result);

		console.log(data);
        // success:
        // area:
        // version:
        // content: artist, title, play, date, img    对象数组
        if(data.success === false){
		  console.log("failure");
          self.content = null;
          self.isEmpty = true;
          return;
        }

        for( var i = 0; i < data.area.length; ++i){
          var aTag = document.createElement('a');
          aTag.className = "tag_item";
          aTag.setAttribute('href',"javascript:;");
          aTag.innerHTML = data.area[i];
          areaTag.appendChild(aTag);
        }

		for( var i = 0; i < data.version.length; ++i){
		  var aTag = document.createElement('a');
		  aTag.className = "tag_item";
		  aTag.setAttribute('href',"javascript:;");
		  aTag.innerHTML = data.version[i];
		  versionTag.appendChild(aTag);
		}

		// 把标签放入tags容器
        tags.appendChild(areaTag);
        tags.appendChild(versionTag);


        // for(var j  = 0 ; j < data.content.length; ++j){
        //   self.content.push({
        //     "img": data.content[i].img,
        //     "date": data.content[i].date,
        //     "play": data.content[i].play,
        //     "title": data.content[i].title,
        //     "artist": data.content[i].artist
        //   });
        // }
      }
    });
  }

};



// 页面加载事件
function loading(){
  var load = document.querySelector('.loading');

  if(document.readyState === "complete"){
    load.style.display = "none";
  }else {
    load.style.display = "block";
  }
}


window.onload = function () {

  var mv = new MV();
  console.log(mv);
  mv.init();
};