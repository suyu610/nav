package com.qdu.nav;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import springfox.documentation.oas.annotations.EnableOpenApi;

@EnableCaching
@SpringBootApplication
@MapperScan("com.qdu.nav.mapper")
@EnableOpenApi
public class NavApplication {

  public static void main(String[] args) {
    SpringApplication.run(NavApplication.class, args);
  }

}
