package com.qdu.nav;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@EnableCaching
@SpringBootApplication
@MapperScan("com.qdu.nav.mapper")
public class NavApplication {

  public static void main(String[] args) {
    SpringApplication.run(NavApplication.class, args);
  }

}
