package com.qdu.nav.util;

/**
 * @ClassName RedisKeyUtil
 * @Description TODO
 * @Author uuorb
 * @Date 2021/5/19 12:00 上午
 * @Version 0.1
 **/

import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.core.*;

/**
 * redisKey设计
 */
public class RedisKeyUtil {

  /**
   * redis的key
   * 形式为：
   * 表名:主键名:主键值:列名
   *
   * @param tableName 表名
   * @param majorKey 主键名
   * @param majorKeyValue 主键值
   * @param column 列名
   * @return
   */
  public static String getKeyWithColumn(String tableName,String majorKey,String majorKeyValue,String column){
    StringBuffer buffer = new StringBuffer();
    buffer.append(tableName).append(":");
    buffer.append(majorKey).append(":");
    buffer.append(majorKeyValue).append(":");
    buffer.append(column);
    return buffer.toString();
  }
  /**
   * redis的key
   * 形式为：
   * 表名:主键名:主键值
   *
   * @param tableName 表名
   * @param majorKey 主键名
   * @param majorKeyValue 主键值
   * @return
   */
  public static String getKey(String tableName,String majorKey,String majorKeyValue){
    StringBuffer buffer = new StringBuffer();
    buffer.append(tableName).append(":");
    buffer.append(majorKey).append(":");
    buffer.append(majorKeyValue).append(":");
    return buffer.toString();
  }


  /**
   * 对hash类型的数据操作
   *
   * @param redisTemplate
   * @return
   */
  @Bean
  public HashOperations<String, String, Object> hashOperations(RedisTemplate<String, Object> redisTemplate) {
    return redisTemplate.opsForHash();
  }

  /**
   * 对redis字符串类型数据操作
   *
   * @param redisTemplate
   * @return
   */
  @Bean
  public ValueOperations<String, Object> valueOperations(RedisTemplate<String, Object> redisTemplate) {
    return redisTemplate.opsForValue();
  }

  /**
   * 对链表类型的数据操作
   *
   * @param redisTemplate
   * @return
   */
  @Bean
  public ListOperations<String, Object> listOperations(RedisTemplate<String, Object> redisTemplate) {
    return redisTemplate.opsForList();
  }

  /**
   * 对无序集合类型的数据操作
   *
   * @param redisTemplate
   * @return
   */
  @Bean
  public SetOperations<String, Object> setOperations(RedisTemplate<String, Object> redisTemplate) {
    return redisTemplate.opsForSet();
  }

  /**
   * 对有序集合类型的数据操作
   *
   * @param redisTemplate
   * @return
   */
  @Bean
  public ZSetOperations<String, Object> zSetOperations(RedisTemplate<String, Object> redisTemplate) {
    return redisTemplate.opsForZSet();
  }
}
