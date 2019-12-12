/**
 * Created by user on 2019/12/12.
 */

document.onreadystatechange = loading;
// 页面加载事件
function loading() {
  var load = document.createElement('div');
  load.classList.add(".loading");
  var loadImg = document.createElement('div');
  loadImg.classList.add(".load_img");
  load.appendChild(loadImg);
  document.body.appendChild(load);

  if (document.readyState === "complete") {
	load.style.display = "none";
  } else {
	load.style.display = "block";
  }

}