/**
 * Created by user on 2019/11/27.
 */

$(document).ready(function () {
  $.ajax({
    type: "get",
    url: "/singer/query",
    success: function(data){

      var tbody = $('tbody');

      var result = JSON.parse(data).result;

      var html = "";

      var checkbox = "<td><input type=\"checkbox\" name=\"\" lay-skin=\"primary\"></td>";

      var operation = "<td class=\"td-manage\"> "
        + "<a title=\"查看\" onclick=\"xadmin.open('编辑','order-view.html')\" href=\"javascript:;\"> "
          + "<i class=\"layui-icon\">&#xe63c;</i>"
        + "</a>"
        + "<a title=\"删除\" onclick=\"member_del(this,'要删除的id')\" href=\"javascript:;\">"
          + "<i class=\"layui-icon\">&#xe640;</i>"
        + "</a>"
        + "</td>";

      for(var i in result){
        html += "<tr>";
        html += checkbox;
        html += "<td>" + result[i].name + "</td>";
        html += "<td>" + result[i].alias + "</td>";
        html += "<td>" + result[i].py + "</td>";
        html += "<td>" + result[i].sex + "</td>";
        html += "<td>" + result[i].birthday + "</td>";
        html += "<td>" + result[i].area + "</td>";
        html += "<td>" + result[i].style + "</td>";
        html += "<td>" + result[i].songNum + "</td>";
        html += "<td>" + result[i].fans + "</td>";
        html += operation;
        html += "</tr>";
      }
      $(tbody).html(html);
    }
  });
});

