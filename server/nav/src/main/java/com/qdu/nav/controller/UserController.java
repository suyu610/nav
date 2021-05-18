package com.qdu.nav.controller;

import com.qdu.nav.entity.VO.LoginReqVO;
import com.qdu.nav.entity.VO.Result;
import com.qdu.nav.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName UserController
 * @Description TODO
 * @Author uuorb
 * @Date 2021/5/18 3:07 下午
 * @Version 0.1
 **/
@CrossOrigin("https://www.qdu.life")
@RestController
public class UserController {
  @Autowired
  UserService userService;

  @PostMapping("/login")
  public Result login(@RequestBody LoginReqVO vo){
    String username = vo.getUsername();
    String password = vo.getPassword();
    return userService.login(username,password);
  }
}
