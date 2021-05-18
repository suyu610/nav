$(function() {
  $.ajax({
    type: "GET",
    url: baseUrl + "/todo",
    dataType: "json",
    headers: {
      token: token,
    },
    success: function(data) {
      if(data.code == 200){
        let todoList = jQuery.parseJSON(data.data)
        handleGetTodoData(todoList)      
      }
    },
    error: function(data) {
      console.log(data.responseText)
    }
  });

  function handleGetTodoData(data) {
    $(".description").html("")
    let todoJsonArr = data;
    let todoJsonObj;
    let todoHtmlStr;
    let remainDayBgColor = "#f75a5a";
    let remainDayColor = "#fff";
    let index = [0, 0, 0, 0]
    for (var i = todoJsonArr.length - 1; i >= 0; i--) {
      // 清空默认值
      todoJsonObj = todoJsonArr[i]
      if (todoJsonObj.isDone == false) {
        let priority = todoJsonObj.priority
        // 设置序号
        // 设置颜色
        // 0-2天 为红色
        // 3-10天 为蓝色
        // 11天以上 为绿色
        // 如果是周期性的，就写周期
        // console.log(todoJsonObj.daysRemaining)
        if (todoJsonObj.cycle != null){
          remainDayBgColor = "#f7d9d9";
          remainDayColor = "#444";
          todoJsonObj.daysRemaining = "周期";
        }
        else if (todoJsonObj.daysRemaining <= 2) {
          remainDayBgColor = "#f75a5a";
          remainDayColor = "#fff";
          todoJsonObj.daysRemaining = "剩" + todoJsonObj.daysRemaining +"天"
        }
        else if (todoJsonObj.daysRemaining <= 10 && todoJsonObj.daysRemaining > 2) {
          remainDayBgColor = "#b7d7e9";
          remainDayColor = "#444";
          todoJsonObj.daysRemaining = "剩" + todoJsonObj.daysRemaining +"天"
        } else if (todoJsonObj.daysRemaining > 10) {
          remainDayBgColor = "#f7d9d9";
          remainDayColor = "#444";
          todoJsonObj.daysRemaining = "剩" + todoJsonObj.daysRemaining +"天"
        }
        index[priority]++
        let todoDom = '\
          <div class="description-item">\
            <div class="description-item-index">' + index[priority] + '.</div>\
            <div class="description-item-title">' + todoJsonObj.name + '</div>\
            <div class="description-item-remain" style="background-color:' + remainDayBgColor + ';color:' + remainDayColor + '">' + todoJsonObj.daysRemaining + '</div>\
          </div>\
        '
        $("#todo-description-" + priority).append(todoDom)
      }else{
        let priority = todoJsonObj.priority
        let todoDom = '\
          <div class="description-item" style="">✓&nbsp;&nbsp;\
            <div class="description-item-title" style="color:#bfb0b0;text-decoration:line-through;"> ' + todoJsonObj.name + '</div>\
          </div>\
        '
        $("#todo-description-" + priority).append(todoDom)

      }
    }
  }
});
