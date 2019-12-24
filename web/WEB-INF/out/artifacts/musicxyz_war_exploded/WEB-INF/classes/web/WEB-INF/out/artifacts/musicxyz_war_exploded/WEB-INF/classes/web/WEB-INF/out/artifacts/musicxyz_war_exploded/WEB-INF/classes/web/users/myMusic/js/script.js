// onload=function change() {
//     var song = document.getElementById("a");
//     var songShow = document.getElementById("song")
//     var songList = document.getElementById("b");
//     var songListShow = document.getElementById("song_list");
//     var album = document.getElementById("c");
//     var albumShow = document.getElementById("album");
//     var mv = document.getElementById("d");
//     var mvShow = document.getElementById("mv");
//
//      song.onclick=function () {
//          songShow.style.display="block";
//          songListShow.style.display="none";
//          albumShow.style.display="none";
//          mvShow.style.display="none";
//      }
//      songList.onclick=function () {
//          songListShow.style.display="block";
//          songShow.style.display="none";
//          albumShow.style.display="none";
//          mvShow.style.display="none";
//      }
//      album.onclick=function () {
//          albumShow.style.display="block";
//          mvShow.style.display="none";
//          songShow.style.display="none";
//          songListShow.style.display="none";
//      }
//      mv.onclick=function () {
//          mvShow.style.display="block";
//          songShow.style.display="none";
//          songListShow.style.display="none";
//          albumShow.style.display="none";
//      }
// };

$(document).ready(function () {

  var userId = null;          //用户Id
  var songId = null;          //歌曲Id


//接受url传来的参数
  function GetQueryString(name) {
	var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)");
	var r = window.location.search.substr(1).match(reg);
	if (r != null) return unescape(r[2]);
	return null;
  }

// 调用方法
//     alert(decodeURI(GetQueryString("user")));
  var search = decodeURI(GetQueryString("user"));
  $.get(
	"/userGet",
	{"search": search},
	function (result) {
	  var data = JSON.parse(result);
	  console.log(data);
	  if (data.success) {
		if (data.result[0].img !== "") {
		  $(".img img").attr('src', data.result[0].img);
		}
		userId = data.result[0].id;
		$(".userName")[0].append(data.result[0].name)
	  }else{
	    location.href = "/users/loginMyMusic/loginMyMusic.html";
	  }

	}
  );


//     $.get(
//     "/collection/get",
//     {"search":search},
//     function (result) {
//         var  data = JSON.parse(result);
//         console.log(data);
//
//             if(result!=null) {
//
//
//                 for (var i = 0; i < data.result.length; i++) {
//                     // 歌曲
//                     $(".single_song")[i].append(data.result[i].song);
//                     $(".single_singer")[i].append(data.result[i].singer);
//                     $(".single_duration")[i].append(data.result[i].duration);
//                     $($(".single_header")[i]).show();
//                     // 专辑
//                     $(".txt_img")[i].src = data.result[i].albumListImg;
//                     $(".txt_name")[i].append(data.result[i].albumListName);
//                     $($(".show_hide")[i]).show();
//                     // MV
//                     $(".txt_imgM")[i].src = data.result[i].mvImg;
//                     $(".txt_nameM")[i].append(data.result[i].mvName);
//                     $($(".show_hideM")[i]).show();
//                 }
//             }
//
//     }
// );
  // 歌曲
  $.get(
	"/collections/get",
	{"search": search},
	function (result) {
	  var data = JSON.parse(result);
	  console.log(data);
	  $("#song").append(data.counts);
	  for (var i = 0; i < data.result.length; i++) {

		$(".single_song")[i].append(data.result[i].song);
		$(".single_singer")[i].append(data.result[i].singer);
		$(".single_duration")[i].append(data.result[i].duration);
		$($(".single_header")[i]).show();
	  }
	}
  );


  //歌单
  $.get(
	"/collection/list/get",
	{"search": search},
	function (result) {
	  var data = JSON.parse(result);
	  console.log(data);
	  $("#songList").append(data.counts);
	  for (var i = 0; i < data.result.length; i++) {
		$(".txt_imgS")[i].src = data.result[i].songListImg;
		$(".txt_nameS")[i].append(data.result[i].songListName);
		$($(".show_hideS")[i]).show();
	  }

	}
  );


  // 专辑
  $.get(
	"/collection/album/get",
	{"search": search},
	function (result) {
	  var data = JSON.parse(result);
	  console.log(data);
	  $("#album").append(data.counts);
	  for (var i = 0; i < data.result.length; i++) {

		$(".txt_img")[i].src = data.result[i].albumListImg;
		$(".txt_name")[i].append(data.result[i].albumListName);
		$($(".show_hide")[i]).show();
	  }
	}
  );

  // MV
  $.get(
	"/collection/mv/get",
	{"search": search},
	function (result) {
	  var data = JSON.parse(result);
	  console.log(data);
	  $("#mv").append(data.counts);

	  for (var i = 0; i < data.result.length; i++) {

		$(".txt_imgM")[i].src = data.result[i].mvImg;
		$(".txt_nameM")[i].append(data.result[i].mvName);
		$($(".show_hideM")[i]).show();
	  }
	}
  );


  $("#mv").addClass("curr");
  //点击歌曲
  $("#song").click(function () {
	$("#song").addClass("curr");
	$("#songList").removeClass("curr");
	$("#album").removeClass("curr");
	$("#mv").removeClass("curr");
	$("#single").show();
	$("#list_list").hide();
	$("#list_mv").hide();
	$("#list_song").hide();
  });
  //点击歌单
  $("#songList").click(function () {
	$("#songList").addClass("curr");
	$("#song").removeClass("curr");
	$("#album").removeClass("curr");
	$("#mv").removeClass("curr");
	$("#single").hide();
	$("#list_list").hide();
	$("#list_mv").hide();
	$("#list_song").show();

  });

  //点击专辑
  $("#album").click(function () {
	$("#album").addClass("curr");
	$("#song").removeClass("curr");
	$("#songList").removeClass("curr");
	$("#mv").removeClass("curr");
	$("#single").hide();
	$("#list_list").show();
	$("#list_mv").hide();
	$("#list_song").hide();
  });

  //点击mv

  $("#mv").click(function () {
	$("#mv").addClass("curr");
	$("#song").removeClass("curr");
	$("#songList").removeClass("curr");
	$("#album").removeClass("curr");
	$("#single").hide();
	$("#list_list").hide();
	$("#list_mv").show();
	$("#list_song").hide();
  });

  $(".icon_delete").click(function () {
	var songName = $(this).parents("ul").find("li").eq(0).html();
	$(this).parents("ul").hide();
	console.log(songName);
	//获取歌曲id
	$.ajax({
	  type: "get",
	  url: "/collection/song/get",
	  data: {"name": songName},
	  async: false,
	  success: function (result) {
		var data = JSON.parse(result);
		console.log(data);
		songId = data.result[0];

		// 删除歌曲
		$.ajax({
		  type: "get",
		  url: "/collection/song/delete",
		  data: {"userId": userId, "songId": songId},
		  async: false,
		  success: function (result) {
			console.log(songId);
			var data = JSON.parse(result);
			console.log(data);

		  }
		});

	  }
	});
  })


});





