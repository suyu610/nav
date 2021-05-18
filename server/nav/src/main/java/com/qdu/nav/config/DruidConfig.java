package com.qdu.nav.config;

/**
 * @ClassName DruidConfig
 * @Description TODO
 * @Author uuorb
 * @Date 2021/5/12 9:29 下午
 * @Version 0.1
 **/

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class DruidConfig {

  //加载application.yaml中的Druid配置
  @ConfigurationProperties(prefix = "spring.datasource")
  @Bean
  public DataSource druid(){
    return  new DruidDataSource();
  }

  //配置Druid的监控
  //1、配置一个管理后台的Servlet
  @Bean
  public ServletRegistrationBean statViewServlet(){
    ServletRegistrationBean bean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
    Map<String,String> initParams = new HashMap<>();

    initParams.put("loginUsername","hq");
    initParams.put("loginPassword","564925080");
    initParams.put("allow","");//默认就是允许所有访问
    initParams.put("deny","192.168.15.21");

    bean.setInitParameters(initParams);
    return bean;
  }


  //2、配置一个web监控的filter
  @Bean
  public FilterRegistrationBean webStatFilter(){
    FilterRegistrationBean bean = new FilterRegistrationBean();
    bean.setFilter(new WebStatFilter());

    Map<String,String> initParams = new HashMap<>();
    initParams.put("exclusions","*.js,*.css,/druid/*");

    bean.setInitParameters(initParams);
    bean.setUrlPatterns(Arrays.asList("/*"));

    return  bean;
  }
}

