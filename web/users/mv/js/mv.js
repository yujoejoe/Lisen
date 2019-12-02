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
  init: function(){
    var self = this;

    // 获取mv顶部标签容器
    var tags = document.getElementById('tags');

    $.ajax({
      type: "get",
      url: "/infoGet",
      data: {"area": "all", "version": "all", "page": this.page},
      dataType: "json",
      async: false,
      success: function(data){
        // success:
        // type:  二维数组
        // content: artist, title, play, date, img    对象数组
        if(data.success === false){
          self.content = null;
          self.isEmpty = true;
          return;
        }

        for( var i = 0; i < data.type.length; ++i){
          // 添加类型标签
          var tag = document.createElement('div');
          tag.className = 'tag';
          // 添加标题
          var title = document.createElement('h3');
          title.className = 'tag_title';

  // 添加标题（未完成）   title.

          // 添加‘全部’标签
          var all = document.createElement('a');
          all.classList.add('tag_item', 'tag_item_select');
          all.innerHTML = "全部";

          tag.appendChild(title);
          tag.appendChild(all);

          data.type[i].forEach(function(ele){
            var aTag = document.createElement('a');
            aTag.className = 'tag_item';
            aTag.innerHTML = ele;
            tag.appendChild(aTag);
          });

          tags.appendChild(tag);
        }





        for(var j  = 0 ; j < data.content.length; ++j){
          self.content.push({
            "img": data.content[i].img,
            "date": data.content[i].date,
            "play": data.content[i].play,
            "title": data.content[i].title,
            "artist": data.content[i].artist
          });
        }
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
