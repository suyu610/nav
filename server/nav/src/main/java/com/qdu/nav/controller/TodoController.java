package com.qdu.nav.controller;

import com.qdu.nav.config.Authorization;
import com.qdu.nav.entity.VO.Result;
import com.qdu.nav.service.TodoService;
import com.qdu.nav.service.impl.TodoServiceImpl;
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

  @Authorization
  @GetMapping("/todo")
  public Result getTodos(HttpServletRequest req){
    return Result.ok(todoService.getTodoFromRishiqing());
  }
}
