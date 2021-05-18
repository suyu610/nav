package com.qdu.nav.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * @ClassName RestConfig
 * @Description TODO
 * @Author uuorb
 * @Date 2021/5/16 4:43 上午
 * @Version 0.1
 **/

@Configuration
public class RestConfig {
  @Bean
  public RestTemplate restTemplate(){
    RestTemplate restTemplate = new RestTemplate();
    return restTemplate;
  }
}
