package com.qdu.nav.service.impl;

import com.qdu.nav.service.TodoService;
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
public class TodoServiceImpl implements TodoService {
  @Autowired
  RestTemplate restTemplate;

  @Override
  public String getTodoFromRishiqing(){
    return testHttp();
  }

  @Override
  public String testHttp(){
    String url = "http://localhost:6886?typeId={typeId}";
    Map<String,Object> map = new HashMap<>();
    map.put("typeId",1);
    String str = restTemplate.getForObject(url, String.class,map);
    return str;
  }
}
