package com.qdu.nav.config;

import org.springframework.stereotype.Component;

/**
 * @ClassName TokenFilter
 * @Description TODO
 * @Author uuorb
 * @Date 2021/5/18 9:49 下午
 * @Version 0.1
 **/
@Component
@Authorization
public class TokenFilter{
  @Override
  protected void beforeRequest(HttpServletRequest request, String message) {
    logger.debug("before req params:", request.getParameterMap());
  }

  @Override
  protected void afterRequest(HttpServletRequest request, String message) {
    logger.debug("after req params:", request.getParameterMap());
  }

}
