package com.qdu.nav.service;

import com.qdu.nav.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName TodoService
 * @Description TODO
 * @Author uuorb
 * @Date 2021/5/16 4:18 上午
 * @Version 0.1
 **/
@Service
public class TodoService {
  @Autowired
  RestTemplate restTemplate;

  public String getTodoFromRishiqing(String token){
    System.out.println(token);
    if(JwtTokenUtil.validateToken(token)){
      return testHttp();
    }else {
      return "";
    }
  }

  public String testHttp(){
    String url = "http://localhost:6886?typeId={typeId}";
    Map<String,Object> map = new HashMap<>();
    map.put("typeId",1);
    String str = restTemplate.getForObject(url, String.class,map);
    System.out.println(str);
    return str;
  }
}
