 let baseProdUrl = "https://api-word.qdu.life";
 let baseDevUrl = "http://localhost:18814";
 let baseUrl = baseProdUrl;

 new ClipboardJS('#copy-btn');
 $('.note').draggabilly()

 $.ajaxSettings.async = false;
 var comm_list;
 var todo_list = [];
 let token = localStorage.getItem("token");

 if (token != null && token != "undefined") {
   // 去掉的disabled
   document.getElementById("noteTextArea").removeAttribute("disabled")
 } else {
   console.log("不存在token")
 }

 function jump2Rishiqing() {
   window.open("https://www.rishiqing.com/app/todo")
 }

 function login() {
   // 判断本地有没有token
   let token = localStorage.getItem("token");
   // 不存在的话，执行登陆
   if (token == null || token == "undefined") {
     $.ajax({
       type: "GET",
       url: baseUrl + "/login",
       headers: {
         username: $("#username").val(),
         password: $("#password").val()
       },
       dataType: "json",
       success: function(data) {
         localStorage.setItem("token", data.token)
         location.reload();
       },
       error: function(data) {
         console.log(data.responseText)
         showAlert("登陆失败","账号或密码错误")
       }
     });
     // 存在的话，执行退出
   } else {
     localStorage.clear("token")
     location.reload();
   }
 }


 function submit() {
   if (token != null && token != "undefined") {
     var data = { "title": $("#title").val(), "url": $("#url").val(), "tagId": $("#tag_id").val(), "isPrivate": $("#private").val(), "desc": $("#desc").val() }
     $.ajax({
       type: "POST",
       url: baseUrl + "/insert",
       headers: {
         'token': token,
       },
       data: JSON.stringify(data),
       contentType: 'application/json',
       dataType: "json",
       success: function(data) {
         // location.reload();
         showAlert("添加成功", "")
         //  清空
         $("#title").val("")
         $("#url").val("")
         $("#private").val("0")
       },
       error: function(data) {
         console.log(data.responseText)
       }
     });
   } else {
     localStorage.clear("token")
     location.reload();
   }
 }

 function getNote() {
   if (token != null && token != "undefined") {
     $.ajax({
       type: "GET",
       url: baseUrl + "/getNote",
       headers: {
         'token': token,
       },
       contentType: 'application/json',
       dataType: "json",
       success: function(data) {
         $("#noteTextArea").html(data.note)
       },
       error: function(data) {
         console.log("没权限")
       }
     });
   }
 }

 function saveNote() {
   if (token != null && token != "undefined") {
     let data = { "note": $("#noteTextArea").val() }
     // console.log(data)
     $.ajax({
       type: "POST",
       url: baseUrl + "/saveNote",
       headers: {
         'token': token,
       },
       contentType: 'application/json',
       dataType: "text",
       data: JSON.stringify(data),
       error: function(data) {
         console.log("没有存的权限")
       }
     });
   }
 }

 function stopPropagation(e) {
   e = e || window.event;
   if (e.stopPropagation) { //W3C阻止冒泡方法  
     e.stopPropagation();
   } else {
     e.cancelBubble = true; //IE阻止冒泡方法  
   }
 }

 function clickNote(e) {
   $("input[id='a']").each(function() {
     if ($(this).attr("checked")) {
       $(this).removeAttr("checked");
     } else {
       $(this).attr("checked", "true");
     }
   })
   stopPropagation(e)
 }

 function clickNoteChild() {
   stopPropagation()
 }

 function debounce(fn, delay) {
   let delays = delay || 500;
   let timer;
   return function() {
     let th = this;
     let args = arguments;
     if (timer) {
       clearTimeout(timer);
     }
     timer = setTimeout(function() {
       timer = null;
       fn.apply(th, args);
     }, delays);
   };
 }

 function showAlert(title, description) {
   GrowlNotification.notify({
     title: title,
     description: description,
     type: 'success',
     position: 'top-right',
     showProgress: true,
     closeTimeout: 2500
   });
 }

 function showDelDialog(title, description, id) {
   GrowlNotification.notify({
     title: title,
     description: description,
     type: 'alert',
     position: 'top-right',
     closeTimeout: 0,
     showButtons: true,
     buttons: {
       action: {
         text: '确认',
         callback: function delFunction() {
           if (token != null && token != "undefined") {
             $.ajax({
               type: "GET",
               url: baseUrl + "/delItem",
               headers: {
                 'token': token,
                 'id': id,
               },
               contentType: 'application/json',
               dataType: "json",
               success: function(data) {
                 setTimeout(function() {
                   location.reload()
                 }, 1000)
                 showAlert("删除成功", data.name)
               },
               error: function(data) {
                 showAlert("删除失败", data.responseJSON.error)
               }
             });
           } else {
             showAlert("非法操作", "token为空")
           }
         }
       },
       cancel: {
         text: '取消',
       }
     },
   });
 }

 $.ajax({
   type: "GET",
   url: baseUrl + "/pubnavs",
   headers: {
     token: token,
   },
   dataType: "json",
   success: function(data) {
     // console.log(data)
     comm_list = data
   },
   error: function(data) {
     console.log(data.responseText)
   }
 });


 $(document).ready(function() {
   getNote();
   // 监听值改变, 防抖 
   $('#noteTextArea').on('input propertychange', debounce(function() {
     // 给后端加备忘录
     saveNote()
   }));

   var tag_list;

   $.ajax({
     type: "GET",
     url: baseUrl + "/tags",
     dataType: "json",
     success: function(aJson, tag_list) {
       $.each(aJson, function(index, value) {
         let name = value.name
         let id = value.id
         tag_list = []
         tag_list.push("<option value='" + id + "'>" + name + "</option>");
         $("#tmp_tag").append("<option value='" + id + "'>" + name + "</option>");
       });
     },
     error: function(data) {
       console.log(data.responseText)
     }
   });


 })
