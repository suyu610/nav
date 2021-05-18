package com.qdu.nav.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * @ClassName FilterConfiguration
 * @Description TODO
 * @Author uuorb
 * @Date 2021/5/18 10:48 下午
 * @Version 0.1
 **/

@Configuration
public class WebAppConfig implements WebMvcConfigurer {

  @Autowired
  private AuthInterceptor authInterceptor;

  /**
   * addPathPatterns 添加拦截规则
   * excludePathPatterns 排除拦截规则
   *
   * @param registry
   */

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(authInterceptor).addPathPatterns("/**").excludePathPatterns("/swagger-ui/**").excludePathPatterns("/druid/**").excludePathPatterns("/login");
  }
}
