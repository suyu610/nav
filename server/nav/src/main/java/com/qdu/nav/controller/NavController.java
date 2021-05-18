package com.qdu.nav.controller;

import com.qdu.nav.config.Authorization;
import com.qdu.nav.entity.PO.Item;
import com.qdu.nav.entity.VO.Result;
import com.qdu.nav.service.NavService;
import com.qdu.nav.service.UserService;
import com.qdu.nav.service.impl.NavServiceImpl;
import com.qdu.nav.service.impl.UserServiceImpl;

import io.netty.util.internal.StringUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName NavController
 * @Description TODO
 * @Author uuorb
 * @Date 2021/5/12 9:45 下午
 * @Version 0.1
 **/
@Api(tags = "链接信息管理")
@CrossOrigin(origins = {"https://www.qdu.life","https://cdns.qdu.life"})
@RestController
public class NavController {
  @Autowired
  NavService navService;

  @Autowired
  UserService userService;

  @ApiOperation("获取所有的链接")
  @GetMapping("/pubnavs")
  public Result getPublicNavs(HttpServletRequest httpRequest){
    String token = httpRequest.getHeader("token");
    return Result.ok(navService.getAllPublicNav(token));
  }

  @Authorization
  @PostMapping("/insert")
  public int insertNavItem(@RequestBody Item item){
    return navService.insertItem(item);
  }

  @Authorization
  @PostMapping("/updateItem")
  public int updateNavItem( @RequestBody Item item){
    return navService.updateItem(item);
  }

  @Authorization
  @GetMapping("/delItem")
  public Result delItem(HttpServletRequest req){
    String id =  req.getHeader("id");
    String result = navService.delItem(id);
    if (StringUtil.isNullOrEmpty(result)){
      return new Result(-1,"未删除",null);
    }
    return Result.ok(result);
  }

  @GetMapping("/tags")
  public Result getTags(){
    return Result.ok(navService.getTags());
  }
}
