package com.qdu.nav.controller;

import com.github.pagehelper.PageInfo;
import com.qdu.nav.config.Authorization;
import com.qdu.nav.entity.PO.StuInfo;
import com.qdu.nav.entity.VO.Result;
import com.qdu.nav.entity.VO.SearchStuReqVO;
import com.qdu.nav.service.StuService;
import com.qdu.nav.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @ClassName StuController
 * @Description TODO
 * @Author uuorb
 * @Date 2021/5/18 11:55 上午
 * @Version 0.1
 **/
@Slf4j
@CrossOrigin(origins = {"https://www.qdu.life","https://cdns.qdu.life"})
@RestController()
public class StuController {
  @Autowired
  StuService service;

  @Autowired
  UserService userService;

  @Autowired
  private RedisTemplate redisTemplate;
  /*
    @params: name,number
    @return:
    {  code:200,
       msg:"成功:,
       data:{
            stu_info:{},
            status:"true|error_number|error_name|none"
       }
     }
   */

  @GetMapping("/stu/check")
  public Result checkStuInfo(@RequestBody StuInfo stu) {
    log.error("Something else is wrong here");
    return Result.ok(service.check(stu.getName(), stu.getSsNumber()));
  }

  @GetMapping("/stu/search")
  @Authorization
  public Result<PageInfo> searchStuInfo(@RequestBody SearchStuReqVO searchStuReqVO){
    PageInfo stuInfoList = service.search(searchStuReqVO);
    return Result.ok(stuInfoList);
  }

}
