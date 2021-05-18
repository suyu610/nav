package com.qdu.nav.controller;

import com.qdu.nav.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName TodoController
 * @Description TODO
 * @Author uuorb
 * @Date 2021/5/16 4:17 上午
 * @Version 0.1
 **/
@CrossOrigin("https://www.qdu.life")
@RestController
public class TodoController {
  @Autowired
  TodoService todoService;

  @GetMapping("/todo")
  public String getTodos(HttpServletRequest httpRequest){
    String token = (String) httpRequest.getHeader("token");
    return todoService.getTodoFromRishiqing(token);
  }
}
