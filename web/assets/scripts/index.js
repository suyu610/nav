! function(e) {
  var n = !1;
  if ("function" == typeof define && define.amd && (define(e), n = !0), "object" == typeof exports && (module.exports = e(), n = !0), !n) {
    var o = window.Cookies,
      t = window.Cookies = e();
    t.noConflict = function() { return window.Cookies = o, t }
  }
}(function() {
  function e() { for (var e = 0, n = {}; e < arguments.length; e++) { var o = arguments[e]; for (var t in o) n[t] = o[t] } return n }

  function n(o) {
    function t(n, r, i) {
      var c;
      if ("undefined" != typeof document) {
        if (arguments.length > 1) {
          if ("number" == typeof(i = e({ path: "/" }, t.defaults, i)).expires) {
            var a = new Date;
            a.setMilliseconds(a.getMilliseconds() + 864e5 * i.expires), i.expires = a
          }
          i.expires = i.expires ? i.expires.toUTCString() : "";
          try { c = JSON.stringify(r), /^[\{\[]/.test(c) && (r = c) } catch (e) {} r = o.write ? o.write(r, n) : encodeURIComponent(r + "").replace(/%(23|24|26|2B|3A|3C|3E|3D|2F|3F|40|5B|5D|5E|60|7B|7D|7C)/g, decodeURIComponent), n = (n = (n = encodeURIComponent(n + "")).replace(/%(23|24|26|2B|5E|60|7C)/g, decodeURIComponent)).replace(/[\(\)]/g, escape);
          var s = "";
          for (var f in i) i[f] && (s += "; " + f, !0 !== i[f] && (s += "=" + i[f]));
          return document.cookie = n + "=" + r + s
        }
        n || (c = {});
        for (var p = document.cookie ? document.cookie.split("; ") : [], d = /(%[0-9A-Z]{2})+/g, u = 0; u < p.length; u++) {
          var l = p[u].split("="),
            C = l.slice(1).join("=");
          this.json || '"' !== C.charAt(0) || (C = C.slice(1, -1));
          try {
            var m = l[0].replace(d, decodeURIComponent);
            if (C = o.read ? o.read(C, m) : o(C, m) || C.replace(d, decodeURIComponent), this.json) try { C = JSON.parse(C) } catch (e) {}
            if (n === m) { c = C; break } n || (c[m] = C)
          } catch (e) {}
        }
        return c
      }
    }
    return t.set = t, t.get = function(e) { return t.call(t, e) }, t.getJSON = function() { return t.apply({ json: !0 }, [].slice.call(arguments)) }, t.defaults = {}, t.remove = function(n, o) { t(n, "", e(o, { expires: -1 })) }, t.withConverter = n, t
  }
  return n(function() {})
});


! function(o) {
  "use strict"

  function i(o) {
    let color = localStorage.getItem('color'); //获取key为name的数据--my name is Russ
    if (color == null) color = "#f5d9d9"
    var t = { bkgd: color, srch: "baidu" }
    return Cookies.get(o) || t[o]
  }
  // 切换tabbar
  function t(t) {
    o(".work-link").find(".tab span.active").removeClass("active")
    var e, n, a = "",
      l = o(t).attr("class")
    // 切换到todo 页面
    if (o(t)[0].className == "todo") {
      o(t).addClass("active"), o(".work-link").find(".tab span:first").hasClass("active")
      o(".work-link").css("opacity", "1").find(".info").hide().html(o("#todoHtml").html()).fadeIn(200)
    } else if (
      o(t).addClass("active"), o.each(comm_list, function(t, i) {
        l == i.slug && (e = i.list, o.each(e, function(t, i) {
          a += "<ul><li><img src=\"data:image/svg+xml;base64,PD94bWwgdmVyc2lvbj0iMS4wIiBlbmNvZGluZz0iaXNvLTg4NTktMSI/PjxzdmcgdmVyc2lvbj0iMS4xIiBpZD0iJiN4NTcxNjsmI3g1QzY0O18xIiB4bWxucz0iaHR0cDovL3d3dy53My5vcmcvMjAwMC9zdmciIHhtbG5zOnhsaW5rPSJodHRwOi8vd3d3LnczLm9yZy8xOTk5L3hsaW5rIiB4PSIwcHgiIHk9IjBweCIgdmlld0JveD0iMCAwIDEyIDEwIiBzdHlsZT0iZW5hYmxlLWJhY2tncm91bmQ6bmV3IDAgMCAxMiAxMDsiIHhtbDpzcGFjZT0icHJlc2VydmUiPjxnPjxyZWN0IHk9IjEiIHN0eWxlPSJmaWxsOiM4RDhEOEQ7IiB3aWR0aD0iMyIgaGVpZ2h0PSIzIi8+PHJlY3QgeT0iNyIgc3R5bGU9ImZpbGw6IzhEOEQ4RDsiIHdpZHRoPSIzIiBoZWlnaHQ9IjMiLz48cmVjdCB4PSIzIiB5PSI0IiBzdHlsZT0iZmlsbDojOEQ4RDhEOyIgd2lkdGg9IjMiIGhlaWdodD0iMyIvPjwvZz48L3N2Zz4=\"></img>" + i.tag + "</li>",
            n = i.link,
            o.each(n, function(o, t) {
              a += '<li data-toggle="tooltip" title="' + t.desc + '"id=' + t.id + ' ttitle=' + t.name + ' slug=' + t.slug + ' tag=' + t.tag + ' url=' + t.url + ' desc=' + t.desc + '>\
          <a href="' + t.url + '" target="_blank">' + t.name + "</a></li>"
            }),
            a += "</ul>"
        }))
      }), o(".work-link").find(".tab span:first").hasClass("active") && "1" == i("schl")) {} else {
      o(".work-link").css("opacity", "1").find(".info").hide().html(a).fadeIn(200)
    }
    $('li').on('contextmenu', onClick)

    o('[data-toggle="tooltip"]').jTippy({
      position: 'top',
      title: 'Hey! I\'m a tooltip!',
    });

  }

  var onClick = function(e) {
    var tid = $(this).attr("id");
    var title = $(this).attr("ttitle")
    var slug = $(this).attr("slug")
    var tag = $(this).attr("tag")
    var url = $(this).attr("url")
    var desc = $(this).attr("desc")
    // console.log("==================")
    // console.log("tid="+tid)
    // console.log("title="+title)
    // console.log("slug="+slug)
    // console.log("tag="+tag)
    // console.log("url="+url)
    // console.log("desc="+desc)
    // console.log("==================")

    var itemDel = function(e) {
      // 传个id就行了
      showDelDialog("确认删除?", title, tid)
    }

    var itemModify = function() {
      let modify_template = $('#modify_template').html()
      // 设置selected的选值，即 value="xx" => value="xx" selected
      let optionsStr = $("#tmp_tag").html()
      optionsStr = optionsStr.replace('value="' + tag + '"', 'value="' + tag + '" selected')
      // 改变模版(放在index.html)中的{title},{url}等等
      // 这里的 optionsStr 为 <option></option><option></option>
      modify_template = modify_template.replace("{title}", title).replace("{url}", url).replace("{desc}", desc).replace("{tag_options}", optionsStr).replace("{tag_value}", slug);

      new Dialogify(modify_template)
        .title('修改')
        .buttons([{
            text: '取消',
            click: function(e) {
              this.close();
            }
          },
          {
            text: '确定',
            type: Dialogify.BUTTON_PRIMARY,
            click: function(e) {
              let id = tid
              let title = this.$content.find("#modify_title").val()
              let desc = this.$content.find("#modify_desc").val()
              let url = this.$content.find("#modify_url").val()
              let tag = this.$content.find("#modify_tag").val()
              let token = localStorage.getItem("token");
              if (token != null && token != "undefined") {
                var data = { "id": id, "title": title, "url": url, "tagId": tag, "desc": desc }
                $.ajax({
                  type: "POST",
                  url: baseUrl + "/updateItem",
                  headers: {
                    'token': token,
                  },
                  data: JSON.stringify(data),
                  contentType: 'application/json',
                  dataType: "json",
                  success: function(data) {
                    showAlert("修改成功", "")
                  },
                  error: function(data) {
                    console.log(data.responseText)
                  }
                });
              } else {
                localStorage.clear("token")
                location.reload();
              }
              // console.log('ok click, title value: ' + this.$content.find('input.text-field').val());
            }
          }
        ])
        .showModal();
    }

    var copyAll = function() {
      $("#copy-btn").attr("data-clipboard-text", title + "            \n" + url + "             \t\t\n" + "来自素语导航 https://qdu.life").click();
      showAlert("复制成功", title + "<br>" + url)
    }

    var copyUrl = function() {
      $("#copy-btn").attr("data-clipboard-text", url).click();
      showAlert("复制网址成功", url)
    }

    var copyTitle = function() {
      $("#copy-btn").attr("data-clipboard-text", title).click();

      showAlert("复制标题成功", title)
    }


    let token = localStorage.getItem("token");
    // 不存在的话，执行登陆
    if (token == null || token == "undefined") {
      var items = [
        { title: '全部复制', icon: 'ion-help-buoy', fn: copyAll },
        {},
        { title: '复制标题', icon: 'ion-plus-round', fn: copyTitle },
        { title: '复制链接', icon: 'ion-person', fn: copyUrl }
      ]

    } else {
      var items = [
        { title: '删除', icon: 'ion-plus-round', fn: itemDel },
        { title: '修改', icon: 'ion-person', fn: itemModify },
        {},
        { title: '复制全部', icon: 'ion-help-buoy', fn: copyAll },
        {},
        { title: '复制标题', icon: 'ion-plus-round', fn: copyTitle },
        { title: '复制链接', icon: 'ion-person', fn: copyUrl }
      ]
    }
    basicContext.show(items, e.originalEvent)
  }

  function e(o, t, i) { Cookies.set(o, t, { expires: i || 3650 }) }

  function n(t) { o("body").css("background", t) }

  function a(t) {
    if (o(t).addClass("active").siblings(".active").removeClass("active"), o(".search-hidden").remove(), o(t).hasClass("baidu")) o(".search-form").attr("action", "https://www.baidu.com/s"), o(".search-keyword").attr({ name: "word", placeholder: "百度一下，你就知道" })
    else if (o(t).hasClass("google")) o(".search-form").attr("action", "https://www.google.com/search"), o(".search-keyword").attr({ name: "q", placeholder: "Google 搜索" })
    else if (o(t).hasClass("zhihu")) o(".search-form").attr("action", "https://www.zhihu.com/search"), o(".search-keyword").attr({ name: "q", placeholder: "知乎" })
    else if (o(t).hasClass("bilibili")) o(".search-form").attr("action", "https://search.bilibili.com/all?keyword="), o(".search-keyword").attr({ name: "keyword", placeholder: "bilibili" })
    o(".search-keyword").focus()

  }

  let colorHtml = '\
      <div style="padding: 30px 30px 0;min-height: 400px;">\
        <div class="row">\
          <div id="setting-bkgd" class="column col-4">\
            <label>背景色</label>\
            <select style="cursor:pointer">\
              <option value="#f5d9d9">桃夭</option>\
              <option value="#ededed">山雾</option>\
              <option value="#ffffff">素白</option>\
              <option value="#8d6262">荔枝</option>\
              <option value="#b9d7ea">天色</option>\
              <option value="#aacfd0">青川</option>\
              <option value="#283c63">深海</option>\
              <option value="#928a97">陆离</option>\
              <option value="#444f5a">青纯</option>\
              <option value="#373c38">石墨</option>\
              <option value="#40514e">月夜</option>\
              <option value="#4d4545">消炭</option>\
            </select>\
          </div>\
        </div>'
  let loginHtml = '\
      <div class="row">\
        <input id="setting-save" type="button" value="保存" style="padding: 0 40px;">\
      </div>\
      <div class="row">\
        <input class="column col-3" type="text" id="username" name="username" placeholder="账号" style="margin-right:10px;padding: 10px 40px;">\
        <input class="column col-3" type="password" id="password" name="password" placeholder="密码" style="margin-right:10px;padding: 0 40px;">\
        <input id="login-btn" type="button" value="登陆" style="padding: 0 40px;" onclick="login()">\
      </div>\
    </div>'
  let exitHtml = '\
      <div class="row">\
        <input id="setting-save" class="column col-2" type="button" value="保存" style="padding: 0 40px;">\
      </div>\
      <div class="row">\
        <input class="column col-2" type="text" id="title"  placeholder="标题"  style="margin-right:5px;padding: 0px 20px;"/>\
        <input class="column col-2" type="text" id="desc"  placeholder="描述"  style="margin-right:5px;padding: 0 20px;"/>\
        <input class="column col-2" type="text" id="url"   placeholder="https://"  style="margin-right:5px;padding: 0 20px;"/>\
        <select id="tag_id" class="column col-2" style="cursor:pointer;margin-right:5px;padding:0 20px"></select>\
        <input class="column col-1" type="text" id="private"  placeholder="" value="0" style="margin-right:5px;padding: 0 20px;"/>\
        <input class="column col-2" id="submit-btn" type="button" value="提交" style="padding: 0 40px;" onclick="submit()">\
      </div>\
      <div class="row">\
        <input id="login-btn" class="column col-2" type="button" value="退出登陆" style="padding: 0 40px;" onclick="login()">\
      </div>\
    </div>'

  let token = localStorage.getItem("token");
  // 存在的话，执行退出
  if (token != null && token != "undefined") {
    // console.log("存在")
    loginHtml = exitHtml
  }
  o.ajaxSetup({ cache: !0 }), n(i("bkgd")), t(o(".work-link").find(".tab span:first")), a(o(".search-tab").find("span." + i("srch"))), o(".work-link .tab").on("click", "span", function() { t(o(this)) }), o(".search-tab").on("click", "span", function() { a(o(this)), e("srch", this.className.split(" ")[0]) }), o("#setting-icon").on("click", function() {
    o(".work-link .info").hide().html(colorHtml + loginHtml).fadeIn(200), o("#setting-bkgd select").val(i("bkgd")),
      o("#setting-bkgd select").change(function() {
        n(o(this).val())
        RENDERER.changeColor(hex2hsl(hex2ry(o("#setting-bkgd select").val())));
      })
    o("#tag_id").html(o("#tmp_tag").html()),
      // 点击保存
      o("#setting-save").off("click").on("click", function() {
        localStorage.setItem('color', o("#setting-bkgd select").val()); //存储key为name的数据data
        showAlert("保存成功", "")
      })
  })
}(jQuery)
