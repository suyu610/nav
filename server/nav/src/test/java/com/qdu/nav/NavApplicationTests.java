package com.qdu.nav;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.StringRedisTemplate;

@SpringBootTest
class NavApplicationTests {

  @Autowired
  StringRedisTemplate stringRedisTemplate;

  @Test
  public void testRedis()
  {
    stringRedisTemplate.opsForValue().append("ms","hello");

  }


}
