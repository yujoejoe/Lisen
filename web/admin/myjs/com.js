/**
 * Created by user on 2019/12/20.
 */

function getAdmin(){
  let data;
  let url = "/userGet";
  $.ajax({
	type: "get"
	, url: url
	, async: false
	, success: function(res){
	  data = JSON.parse(res);
	  console.log(data);
	}
  });
  return data;
}