/**
 * Created by user on 2019/12/16.
 */

function getCookie(key) {
  if (document.cookie) {
	var cookies = document.cookie.replace(/\s*/g, "").split(";");
	console.log(cookies);
	var obj = [];
	var value;
	for (var i = 0; i < cookies.length; ++i) {
	  var o = {
		"key": cookies[i].split("=")[0],
		"value": cookies[i].split("=")[1]
	  };
	  obj.push(o);
	}
	console.log(obj);

	for (var j = 0; j < obj.length; ++j) {
	  if (obj[j].key === key) {
		value = obj[j].value;
		break;
	  }
	}
	return value;
  }
}

function clearChild(parent) {
  while (parent !== null && parent.hasChildNodes()) {
	parent.removeChild(parent.firstChild);
  }
}