package com.qdu.nav.service;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.qdu.nav.entity.PO.User;
import com.qdu.nav.mapper.UserMapper;
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
public class UserService {
  @Autowired
  UserMapper userMapper;

  public String login(String username,String password){
    if(StringUtils.isEmpty(username) || StringUtils.isEmpty(password)){
      return "账号或密码为空";
    }

    User user = userMapper.isCorrect(username);

    if( user != null && StringUtils.equals(user.getPassword(),password)){
      String token =  JwtTokenUtil.getAccessToken(username,null);
//      System.out.println(token);
      JSONObject jsonObject = new JSONObject();
      jsonObject.put("token",token);
      return jsonObject.toString();
    }else {
      return "账号或密码错误";
    }
  }

  public boolean checkToken(String token){
    return JwtTokenUtil.validateToken(token);
  }
}
