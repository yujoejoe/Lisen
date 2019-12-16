var num = 0;

$(function () {

  // search 反馈结果
  var $search = $('.header-search');
  var $search_text = $search.find('.text');
  var $search_result = $search.find('.result');

  $search_text.focus(function () {
    $search_result.show();
  }).click(function () {
    return false;
  });

  $(document).click(function () {
    $search_result.hide();
  });



  var newSong = $('#newSong');
  var mv = $('#mv');
  var hotSong = $('#hotSong');

  // 幻灯片切换
  newSong.slider();
  hotSong.slider();

  // tab 切换
  newSong.tab();
  mv.tab();


  // 旋转木马
  var $recommend = $('#recommend');
  var $carousel_item = $recommend.find('.carousel-slider').find('.item');
  var $carousel_prev = $recommend.find('.slider-prev');
  var $carousel_next = $recommend.find('.slider-next');

  var carouselArr = ['item-pic1', 'item-pic2', 'item-pic3', 'item-pic4', 'item-pic5', 'item-pic6'];
  var b_stop = true;

  var src = null;

  $carousel_prev.click(function () {
    if (b_stop) {
      b_stop = false;

      carouselArr.push(carouselArr.shift());

      $carousel_item.each(function (i) {
        $carousel_item.removeClass(carouselArr[i]);  
        $(this).addClass(carouselArr[i]);

        setTimeout(function () {
          b_stop = true;
        }, 500);
      });

      src = $('.item-pic3').find('img').attr('src');

      $recommend.css('backgroundImage', 'url('+src+')');

    }

    return false;
  });

  $carousel_next.click(function () {
    if (b_stop) {
      b_stop = false;
    
      carouselArr.unshift(carouselArr.pop());

      $carousel_item.each(function (i) {
        $carousel_item.removeClass(carouselArr[i]);  
        $(this).addClass(carouselArr[i]);

        setTimeout(function () {
          b_stop = true;
        }, 500);
      });
    }

    src = $('.item-pic3').find('img').attr('src');

    $recommend.css('backgroundImage', 'url('+src+')');

    return false;
  });

  var id = setInterval(function(){
    $carousel_prev.click();
  }, 3000);

});

(function ($, window, document, undefined) {

  var Plugin = function (elem) {
    this.oWrapper = elem;

    this.$tab_item = this.oWrapper.find('.tab-title').find('.item');
    this.$tab_img = this.oWrapper.find('.tab-cont').find('img');
    this.$slider_wrapper = $('.slider-wrapper');

    this.$slider_btn = $('.slider-btns').find('span');


      //新歌首发部分
      $.get(
          "/timeDown/get",
          function (result) {
              var data = JSON.parse(result);
              for (var j = 0; j < 20; j++) {
                  $(".newSongs_img")[j].src=data.result[j].img;
                  $(".newSongs_song")[j].append(data.result[j].song);
                  $(".newSongs_singerName")[j].append(data.result[j].singer);
              }
          }
      );

      //新歌首发全部部分
      $("#all_NewSong").click(function () {
          /*$(".newSongs_img").src.empty();
          $(".newSongs_song").empty();
          $(".newSongs_singerName").empty();*/
          $.get(
              "/timeDown/get",
              function (result) {
                  var data = JSON.parse(result);
                  for (var j = 0; j < 20; j++) {
                      $(".newSongs_img")[j].src=data.result[j].img;
                      $(".newSongs_song")[j].append(data.result[j].song);
                      $(".newSongs_singerName")[j].append(data.result[j].singer);
                  }
              }
          );
      });

      //新歌首发华语部分
      $("#China_NewSong").click(function () {
          /*$(".newSongs_img").src.empty();
          $(".newSongs_song").empty();
          $(".newSongs_singerName").empty();*/
          $.get(
              "/timeDown/get",
              function (result) {
                  var data = JSON.parse(result);
                  for (var j = 0; j < 20; j++) {
                      $(".newSongs_img")[j].src=data.result[j%data.result.length].img;
                      $(".newSongs_song")[j].append(data.result[j%data.result.length].song);
                      $(".newSongs_singerName")[j].append(data.result[j%data.result.length].singer);
                  }
              }
          );
      });

      //新歌首发日韩部分
      $("#JapanKorea_NewSong").click(function () {
         /* var temp = "";
          $(".newSongs_img").src.innerHTML=temp;
          $(".newSongs_song").innerHTML=temp;
          $(".newSongs_singerName").innerHTML=temp;*/
          $.get(
              "/Japan/get",
              function (result) {
                  var data = JSON.parse(result);
                  for (var j = 0; j < 20; j++) {
                      $(".newSongs_img")[j].src=data.result[j%data.result.length].img;
                      $(".newSongs_song")[j].append(data.result[j%data.result.length].song);
                      $(".newSongs_singerName")[j].append(data.result[j%data.result.length].singer);
                  }
              }
          );
      });

      //新歌首发欧美部分
      $("#EuropeAmerica_NewSong").click(function () {
          /*$(".newSongs_img").src.empty();
          $(".newSongs_song").empty();
          $(".newSongs_singerName").empty();*/
          $.get(
              "/EuropeAmerica/get",
              function (result) {
                  var data = JSON.parse(result);
                  for (var j = 0; j < 20; j++) {
                      $(".newSongs_img")[j].src=data.result[(j+3)%data.result.length].img;
                      $(".newSongs_song")[j].append(data.result[(j+3)%data.result.length].song);
                      $(".newSongs_singerName")[j].append(data.result[(j+3)%data.result.length].singer);
                  }
              }
          );
      });

      /*$.get(
          "/mvGet",
          function (result) {
              var data = JSON.parse(result);
              //MV部分
              this.mv = {
                  1: [data.result[0].img,data.result[1].img,data.result[2].img,data.result[3].img,data.result[4].img,data.result[5].img,data.result[6].img,data.result[7].img],
                  2: [data.result[8].img,data.result[9].img,data.result[10].img,data.result[11].img,data.result[12].img,data.result[13].img,data.result[14].img,data.result[15].img],
                  3: [data.result[16].img,data.result[17].img,data.result[18].img,data.result[19].img,data.result[20].img,data.result[21].img,data.result[22].img,data.result[23].img],
                  4: [data.result[24].img,data.result[25].img,data.result[26].img,data.result[27].img,data.result[28].img,data.result[3].img,data.result[1].img,data.result[2].img]
              };
          }
      );*/

  };




  Plugin.prototype = {
    init: function () {
      var self = this;
      this.$tab_item.click(function () {
        self.change($(this).index());
        self.$slider_wrapper.css({left: -1200});
        self.$slider_btn.removeClass('cur');
        self.$slider_btn.eq(0).addClass('cur');

        num = 0;
      });
    },

    // 切换图片
    /*change: function (index) {

      var self = this;

      // var img_src = 'user/index/images/cont/newSong/';

       /!* //新歌首发华语部分
        $("#China_NewSong").click(function () {
            $.get(
                "/timeDown/get",
                function (result) {
                    var data = JSON.parse(result);
                    var img_src = data.result.img;
                }
            );
        });*!/

      // var mv_src = 'user/index/images/cont/mv/';

        $.get(
            "/mvGet",
            function (result) {
                var data = JSON.parse(result);
                var mv_src = data.result.img;
            }
        );

      this.$tab_item.removeClass('item-cur');
      this.$tab_item.eq(index).addClass("item-cur");

      /!*if (this.oWrapper.attr('id') === 'newSong') {

        var img_length = self.newSong[1].length;
        /!*$(this.$tab_img).each(function (i) {
          if(i < 4){
            // 让前面的复制体图片等于第三板块图片
            $(self.$tab_img[i]).attr('src', img_src + self.newSong[index + 1][img_length-4+i]+'');
          }else if(i > 15){
            // 让后面的复制体图片等于第一板块图片
            $(self.$tab_img[i]).attr('src', img_src + self.newSong[index + 1][i-img_length-4]+'');
          }else{
            $(self.$tab_img[i]).attr('src', img_src + self.newSong[index + 1][i-4]+'');
          }
        });*!/



        /!*  $(this.$tab_img).each(function (i) {
            if(i < 4){
              // 让前面的复制体图片等于第三板块图片
              $(self.$tab_img[i]).attr('src',  self.newSong[index + 1][img_length-4+i]+'');
            }else if(i > 15){
              // 让后面的复制体图片等于第一板块图片
              $(self.$tab_img[i]).attr('src',  self.newSong[index + 1][i-img_length-4]+'');
            }else{
              $(self.$tab_img[i]).attr('src', self.newSong[index + 1][i-4]+'');
            }
          });
*!/

      } else if (this.oWrapper.attr('id') === 'mv') {

       /!* $(this.$tab_img).each(function (i) {
          $(self.$tab_img[i]).attr('src', mv_src + self.mv[index + 1][i]+'');
        });*!/

          $(this.$tab_img).each(function (i) {
              $(self.$tab_img[i]).attr('src',  self.mv[index + 1][i]+'');
          });

      }*!/
    },*/

    constructor: Plugin
  };

  $.fn.tab = function () {
    var plugin = new Plugin(this);

    return plugin.init();
  };

})(jQuery, window, document);

(function ($, window, document, undefined) {

  var Plugin = function (elem) {
    this.$oParent = elem;
    this.$slider_wrapper = this.$oParent.find('.slider-wrapper');
    this.$slider_item = this.$slider_wrapper.find('li');
    this.$slider_btn = this.$oParent.find('.slider-btns').find('span');
    this.$slider_prev = this.$oParent.find('.slider-prev');
    this.$slider_next = this.$oParent.find('.slider-next');
    this.dis_w = 1200;
    this.b_stop = true;
  };

  Plugin.prototype = {
    init: function () {
      var self = this;

      // 克隆元素

      if(this.$oParent.attr('id') === 'newSong'){
         this.$slider = $('#newSong');
      }else if(this.$oParent.attr('id') === 'hotSong'){
        this.$slider = $('#hotSong');
      }
      this.$slider_li = this.$slider.find('.slider-li');
      this.$firstClone = $(this.$slider_li[this.$slider_li.length-1]).clone();
      this.$lastClone = $(this.$slider_li[0]).clone();
      this.$first_img = this.$firstClone.find('img');
      this.$last_img = this.$lastClone.find('img');
      $(this.$slider_li[0]).before(this.$firstClone);
      $(this.$slider_li[this.$slider_li.length-1]).after(this.$lastClone);


      this.eventInfo();

      this.$slider_btn.click(function () {
        var cur_index = $(this).index();
        console.log(cur_index);
        self.setDot(cur_index);

        self.$slider_wrapper.animate({left: -(cur_index+1) * self.dis_w},500);

        num = cur_index;
      });

      this.$slider_prev.click(function () {
        if (self.b_stop) {
          self.b_stop = false;

          self.prevClick();
        }
      });

      this.$slider_next.click(function () {
        if (self.b_stop) {
          self.b_stop = false;

          self.nextClick();
        }
      });
    },

    eventInfo: function () {
      this.$slider_item.each(function (i) {
        $(this).addClass(i % 2 ? 'info-event' : '')
      });
    },

    setDot: function (index) {
      this.$slider_btn.removeClass('cur');
      this.$slider_btn.eq(index).addClass('cur');
    },

// 原 prevClick
    // prevClick: function () {
    //   var self = this;
    //
    //   if (num === 0) {
    //     num = this.$slider_btn.length - 1;
    //   } else {
    //     num --;
    //   }
    //   console.log(num);
    //
    //   this.setDot(num);
    //
    //   this.$slider_wrapper.animate({left: -num * this.dis_w}, 600, function () {
    //     self.b_stop = true;
    //   });
    // },

// 改 prevClick
      prevClick: function () {
          var self = this;

          if(num === -1){
            num = this.$slider_btn.length - 1;
            this.$slider_wrapper.animate({left: -(num+1) * this.dis_w}, 0, function () {
                  self.b_stop = true;
              });
          }
        num--;
        this.setDot(num%this.$slider_btn.length);
        this.$slider_wrapper.animate({left: -(num+1) * this.dis_w}, 600, function () {
          self.b_stop = true;
        });

      },

// 原 nextClick
//     nextClick: function () {
//       var self = this;
//
//       if (num === this.$slider_btn.length - 1) {
//         num = 0;
//       } else {
//         num ++;
//       }
//
//       this.setDot(num);
//
//       this.$slider_wrapper.animate({left: -num * this.dis_w}, 600, function () {
//         self.b_stop = true;
//       });
//     },

// 改 nextClick
      nextClick: function () {
          var self = this;


          if(num === this.$slider_btn.length){
            num = 0;
            this.$slider_wrapper.animate({left: -(num+1) * this.dis_w}, 0, function () {
                  self.b_stop = true;
              });
          }

          num++;
          this.setDot(num%this.$slider_btn.length);
          this.$slider_wrapper.animate({left: -(num+1) * this.dis_w}, 600, function () {
                  self.b_stop = true;
              });
      },

    constructor: Plugin
  };

  $.fn.slider = function () {
    var plugin = new Plugin(this);

    return plugin.init();
  };

})(jQuery, window, document);


