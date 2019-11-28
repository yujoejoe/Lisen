/**
 * Created by user on 2019/11/27.
 */

$(document).ready(function () {
  $.ajax({
    type: "post",
    url: "Teacher",
    success: function(data){
      var td = $('tbody tr td');
    }
  });
});

