package com.qdu.nav;

import com.qdu.nav.entity.VO.StuInfoVO;
import com.qdu.nav.service.RedisService;
import com.qdu.nav.util.RedisKeyUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.*;

import javax.annotation.Resource;
import java.util.Set;
import java.util.concurrent.TimeUnit;


public class RedisTest {
  @Autowired
  private StringRedisTemplate stringRedisTemplate;

  @Autowired
  private RedisTemplate redisTemplate;

  @Resource
  private ValueOperations<String,Object> valueOperations;

  @Autowired
  private HashOperations<String, String, Object> hashOperations;

  @Autowired
  private ListOperations<String, Object> listOperations;

  @Autowired
  private SetOperations<String, Object> setOperations;

  @Autowired
  private ZSetOperations<String, Object> zSetOperations;

  @Resource
  private RedisService redisService;

  @Test
  public void testObj() throws Exception{
    StuInfoVO userVo = new StuInfoVO();
    userVo.setName("黄鹏宇");
    userVo.setClassName("机械一班");
    userVo.setGender("男");
    ValueOperations<String,Object> operations = redisTemplate.opsForValue();
    redisService.expireKey("name",20, TimeUnit.SECONDS);
    String key = RedisKeyUtil.getKey(StuInfoVO.class.getName(),"name",userVo.getName());
    StuInfoVO vo = (StuInfoVO) operations.get(key);
    System.out.println(vo);
  }

  @Test
  public void testValueOption( )throws  Exception{
    StuInfoVO userVo = new StuInfoVO();
    userVo.setName("皇甫素素");
    userVo.setClassName("电子信息");
    userVo.setGender("女");
    valueOperations.set("test",userVo);

    System.out.println(valueOperations.get("test"));
  }

  @Test
  public void testSetOperation() throws Exception{
    StuInfoVO userVo = new StuInfoVO();
    userVo.setName("皇甫素素");
    userVo.setClassName("电子信息");
    userVo.setGender("女");

    StuInfoVO userVo1 = new StuInfoVO();
    userVo1.setName("黄鹏宇");
    userVo1.setClassName("机械工程");
    userVo1.setGender("男");
    setOperations.add("user:test",userVo,"test");
    Set<Object> result = setOperations.members("user:test");
    System.out.println(result);
  }

  @Test
  public void HashOperations() throws Exception{
    StuInfoVO userVo = new StuInfoVO();
    userVo.setName("皇甫素素");
    userVo.setClassName("电子信息");
    userVo.setGender("女");
    hashOperations.put("hash:user",userVo.hashCode()+"",userVo);
    System.out.println(hashOperations.get("hash:user",userVo.hashCode()+""));
  }

  @Test
  public void  ListOperations() throws Exception{
    StuInfoVO userVo = new StuInfoVO();
    userVo.setName("皇甫素素");
    userVo.setClassName("电子信息");
    userVo.setGender("女");
    // listOperations.leftPush("list:user",userVo);
    // System.out.println(listOperations.leftPop("list:user"));
    // pop之后 值会消失
    System.out.println(listOperations.leftPop("list:user"));
  }
}
