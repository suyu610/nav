package com.qdu.nav.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.qdu.nav.entity.PO.User;
import com.qdu.nav.entity.VO.Result;
import com.qdu.nav.mapper.UserMapper;
import com.qdu.nav.service.UserService;
import com.qdu.nav.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName UserService
 * @Description TODO
 * @Author uuorb
 * @Date 2021/5/12 10:48 下午
 * @Version 0.1
 **/
@Service
public class UserServiceImpl implements UserService {
  @Autowired
  UserMapper userMapper;

  @Override
  public Result login(String username, String password){
    if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
      return new Result(-2,"账号或密码为空",null);
    }

    User user = userMapper.isCorrect(username);

    if( user != null && StringUtils.equals(user.getPassword(),password)){
      String token =  JwtTokenUtil.getAccessToken(username,null);
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("token",token);
      return Result.ok(jsonObject.toString());
    }else {
      return new Result(-2,"账号或密码错误",null);
    }
  }

  public boolean checkToken(String token){
    if(StringUtils.isEmpty(token)){
      return false;
    }
    return JwtTokenUtil.validateToken(token);
  }
}
