package com.qdu.nav.config;

/**
 * @ClassName CrossOriginfilter
 * @Description TODO
 * @Author uuorb
 * @Date 2021/5/12 10:13 下午
 * @Version 0.1
 **/

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configuration
public class CrossOriginFilter implements Filter {

  @Override
  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
    HttpServletResponse response= (HttpServletResponse) servletResponse;
    HttpServletResponse httpResponse = (HttpServletResponse) response;
    HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
    httpResponse.setHeader("Access-Control-Allow-Origin", "*");
    httpResponse.setHeader("Access-Control-Allow-Methods", httpRequest.getMethod());
    httpResponse.setHeader("Access-Control-Max-Age", "3600");
    httpResponse.setHeader("Access-Control-Allow-Headers", httpRequest.getHeader("Access-Control-Request-Headers"));
    filterChain.doFilter(servletRequest, response);
  }
}
