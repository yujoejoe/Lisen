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

  // 登陆状态
  var profile = $('.had-login');    // 头像
  var notLog = $('.not-login');
  // 发送请求获取数据
  $.ajax({
    type: "get",
    url: "userGet",
    data: {},
    success: function(result){
      console.log(result);
      var data = JSON.parse(result);
      console.log(data);
      if(data.success === false){
        $(notLog).css('display', 'block');
		$(profile).css('display', 'none')
      }else{
		$(notLog).css('display', 'none');
        $(profile).css('display', 'block');
      }

    }
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

    this.newSong = {
      1: ['slider_img1.jpg', 'slider_img2.jpg', 'slider_img3.jpg', 'slider_img4.jpg', 'slider_img5.jpg', 'slider_img6.jpg', 'slider_img7.jpg', 'slider_img8.jpg', 'slider_img9.jpg', 'slider_img10.jpg', 'slider_img11.jpg', 'slider_img12.jpg'],
      2: ['slider_img13.jpg', 'slider_img14.jpg', 'slider_img15.jpg', 'slider_img16.jpg', 'slider_img17.jpg', 'slider_img18.jpg', 'slider_img19.jpg', 'slider_img20.jpg', 'slider_img21.jpg', 'slider_img22.jpg', 'slider_img23.jpg', 'slider_img24.jpg'],
      3: ['slider_img10.jpg', 'slider_img4.jpg', 'slider_img11.jpg', 'slider_img12.jpg', 'slider_img5.jpg', 'slider_img6.jpg', 'slider_img1.jpg', 'slider_img2.jpg', 'slider_img3.jpg', 'slider_img7.jpg', 'slider_img8.jpg', 'slider_img9.jpg'],
      4: ['slider_img4.jpg', 'slider_img11.jpg', 'slider_img10.jpg', 'slider_img12.jpg', 'slider_img5.jpg', 'slider_img8.jpg', 'slider_img9.jpg', 'slider_img6.jpg', 'slider_img1.jpg', 'slider_img2.jpg', 'slider_img3.jpg', 'slider_img7.jpg'],
      5: ['slider_img11.jpg', 'slider_img10.jpg', 'slider_img4.jpg', 'slider_img12.jpg', 'slider_img2.jpg', 'slider_img3.jpg', 'slider_img5.jpg', 'slider_img6.jpg', 'slider_img1.jpg', 'slider_img7.jpg', 'slider_img8.jpg', 'slider_img9.jpg']
    };

      // this.newSong = {
      //     1: [
      //         ['slider_img1.jpg', 'slider_img2.jpg', 'slider_img3.jpg', 'slider_img4.jpg'],
      //         ['slider_img5.jpg', 'slider_img6.jpg', 'slider_img7.jpg', 'slider_img8.jpg'],
      //         ['slider_img9.jpg', 'slider_img10.jpg', 'slider_img11.jpg', 'slider_img12.jpg']
      //     ],
      //     2: [
      //         ['slider_img4.jpg', 'slider_img5.jpg', 'slider_img6.jpg', 'slider_img1.jpg'],
      //         ['slider_img2.jpg', 'slider_img3.jpg', 'slider_img7.jpg', 'slider_img8.jpg'],
      //         ['slider_img9.jpg', 'slider_img10.jpg', 'slider_img11.jpg', 'slider_img12.jpg']
      //     ],
      //     3: [
      //         ['slider_img10.jpg', 'slider_img4.jpg', 'slider_img11.jpg', 'slider_img12.jpg'],
      //         ['slider_img5.jpg', 'slider_img6.jpg', 'slider_img1.jpg', 'slider_img2.jpg'],
      //         ['slider_img3.jpg', 'slider_img7.jpg', 'slider_img8.jpg', 'slider_img9.jpg']
      //     ],
      //     4: [
      //         ['slider_img4.jpg', 'slider_img11.jpg', 'slider_img10.jpg', 'slider_img12.jpg'],
      //         ['slider_img5.jpg', 'slider_img8.jpg', 'slider_img9.jpg', 'slider_img6.jpg'],
      //         ['slider_img1.jpg', 'slider_img2.jpg', 'slider_img3.jpg', 'slider_img7.jpg']
      //     ],
      //     5: [
      //         ['slider_img11.jpg', 'slider_img10.jpg', 'slider_img4.jpg', 'slider_img12.jpg'],
      //         ['slider_img2.jpg', 'slider_img3.jpg', 'slider_img5.jpg', 'slider_img6.jpg'],
      //         ['slider_img1.jpg', 'slider_img7.jpg', 'slider_img8.jpg', 'slider_img9.jpg']
      //     ]
      // };

    this.mv = {
      1: ['mv_img1.jpg', 'mv_img2.jpg', 'mv_img3.jpg', 'mv_img4.jpg', 'mv_img5.jpg', 'mv_img6.jpg', 'mv_img7.jpg', 'mv_img8.jpg'],
      2: ['mv_img8.jpg', 'mv_img5.jpg','mv_img1.jpg', 'mv_img4.jpg', 'mv_img3.jpg', 'mv_img6.jpg', 'mv_img7.jpg', 'mv_img2.jpg'],
      3: ['mv_img1.jpg', 'mv_img5.jpg', 'mv_img6.jpg', 'mv_img2.jpg', 'mv_img3.jpg', 'mv_img4.jpg', 'mv_img7.jpg', 'mv_img8.jpg'],
      4: ['mv_img4.jpg', 'mv_img5.jpg','mv_img1.jpg', 'mv_img2.jpg', 'mv_img3.jpg', 'mv_img6.jpg', 'mv_img7.jpg', 'mv_img8.jpg'],
      5: ['mv_img1.jpg', 'mv_img5.jpg', 'mv_img6.jpg', 'mv_img2.jpg', 'mv_img3.jpg', 'mv_img8.jpg', 'mv_img4.jpg', 'mv_img7.jpg'],
      6: ['mv_img8.jpg', 'mv_img2.jpg', 'mv_img3.jpg', 'mv_img4.jpg', 'mv_img5.jpg', 'mv_img6.jpg', 'mv_img7.jpg', 'mv_img1.jpg']
    };
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
    change: function (index) {


      var self = this;
      var img_src = 'user/index/images/cont/newSong/';
      var mv_src = 'user/index/images/cont/mv/';
      this.$tab_item.removeClass('item-cur');
      this.$tab_item.eq(index).addClass("item-cur");

      if (this.oWrapper.attr('id') === 'newSong') {

        var img_length = self.newSong[1].length;
        $(this.$tab_img).each(function (i) {
          if(i < 4){
            // 让前面的复制体图片等于第三板块图片
            $(self.$tab_img[i]).attr('src', img_src + self.newSong[index + 1][img_length-4+i]+'');
          }else if(i > 15){
            // 让后面的复制体图片等于第一板块图片
            $(self.$tab_img[i]).attr('src', img_src + self.newSong[index + 1][i-img_length-4]+'');
          }else{
            $(self.$tab_img[i]).attr('src', img_src + self.newSong[index + 1][i-4]+'');
          }
        });

      } else if (this.oWrapper.attr('id') === 'mv') {

        $(this.$tab_img).each(function (i) {
          $(self.$tab_img[i]).attr('src', mv_src + self.mv[index + 1][i]+'');
        });

      }
    },

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


