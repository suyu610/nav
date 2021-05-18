package com.qdu.nav.controller;

import com.qdu.nav.entity.Item;
import com.qdu.nav.service.NavService;
import com.qdu.nav.service.UserService;
import com.qdu.nav.util.JwtTokenUtil;

import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;

/**
 * @ClassName NavController
 * @Description TODO
 * @Author uuorb
 * @Date 2021/5/12 9:45 下午
 * @Version 0.1
 **/

@CrossOrigin(origins = {"https://www.qdu.life","https://cdns.qdu.life"})
@RestController
public class NavController {
  @Autowired
  NavService navService;
  @Autowired
  UserService userService;

  @GetMapping("/pubnavs")
  public String getPublicNavs(HttpServletRequest httpRequest){
    String token = (String) httpRequest.getHeader("token");
    return navService.getAllPublicNav(token);
  }

  @PostMapping("/insert")
  public int insertNavItem(HttpServletRequest httpRequest, @RequestBody Item item){
    String token =  httpRequest.getHeader("token");
    System.out.println(item);
    System.out.println(token);
    return navService.insertItem(item,token);
  }
  @PostMapping("/updateItem")
  public int updateNavItem(HttpServletRequest httpRequest, @RequestBody Item item){
    String token =  httpRequest.getHeader("token");
    return navService.updateItem(item,token);
  }
  @GetMapping("/delItem")
  public String delItem(HttpServletRequest req){
    String token =  req.getHeader("token");
    String id =  req.getHeader("id");
    return navService.delItem(token,id);
  }
  @GetMapping("/login")
  public String login(HttpServletRequest req){
    String username = req.getHeader("username");
    String password = req.getHeader("password");
    System.out.println("======== NavController ========");
    System.out.println(username);
    System.out.println(password);
    return userService.login(username,password);
  }

  @GetMapping("/checkToken")
  public Boolean checkToken(HttpServletRequest req) {
    String token = req.getHeader("token");
    if(token==null || token == ""){
      return false;
    }else{
      return userService.checkToken(token);
    }

  }

    @GetMapping("/tags")
  public String getTags(){
    return navService.getTags();
  }
}
