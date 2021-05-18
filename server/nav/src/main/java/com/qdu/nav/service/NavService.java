package com.qdu.nav.service;

/**
 * @ClassName NavService
 * @Description TODO
 * @Author uuorb
 * @Date 2021/5/12 9:38 下午
 * @Version 0.1
 **/

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qdu.nav.entity.Item;
import com.qdu.nav.entity.Slug;
import com.qdu.nav.entity.Tag;
import com.qdu.nav.mapper.NavMapper;
import com.qdu.nav.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@Service
public class NavService {
  @Autowired
  NavMapper navMapper;

  public String delItem(String token, String itemId){
    JSONObject jsonObject = new JSONObject();
    if(JwtTokenUtil.validateToken(token)) {
      jsonObject.put("name",navMapper.delItem(itemId));
      return jsonObject.toString();
    }

    return jsonObject.toString();
  }

  /**将方法运行结果进行缓存，当方法被再次调用时，直接返回缓存中的结果。
   * 参数：
   * value：指定缓存组件的名字
   * key：缓存的key。可以使用SpEl表达式
   * condition：缓存条件。（为true时缓存），使用EL表达式
   * unless：否定缓存。（为true时不缓存）unless在方法执行之后判断，所以unless可以用结	果作为判断条件。
   * @return
   */

  public String getAllPublicNav(String token) {
    List<Slug> slugList = navMapper.getAllSlug();
    int privateFlag = 0;
    if(JwtTokenUtil.validateToken(token)){
      privateFlag = 1;
    }

    JSONArray slugJsonArray = new JSONArray();

    // 在这要凑了
    HashMap resultMap = new HashMap();

    for (Slug slug:slugList) {
      JSONObject slugJsonObject = new JSONObject();
      slugJsonObject.put("slug",slug.getTitle());
      List<Tag> tagList = navMapper.getTagBySlutId(slug.getId());
      JSONArray tagJsonArray = new JSONArray();

      for (Tag tag:tagList){
        JSONObject tagJsonObject = new JSONObject();
        tagJsonObject.put("tag",tag.getTitle());
        List<Item> itemList = navMapper.getItemByTagId(tag.getId(),privateFlag);
        JSONArray itemJsonArray = new JSONArray();

        for (Item item:itemList){
          JSONObject itemJsonObject = new JSONObject();
          itemJsonObject.put("name",item.getTitle());
          itemJsonObject.put("url",item.getUrl());
          itemJsonObject.put("id",item.getId());
          itemJsonObject.put("tag",tag.getId());
          itemJsonObject.put("slug",slug.getId());
          itemJsonObject.put("desc",item.getDesc());
          itemJsonObject.put("private",item.getIsPrivate());

          itemJsonArray.add(itemJsonObject);
        }
        tagJsonObject.put("link",itemJsonArray);
        tagJsonArray.add(tagJsonObject);
      }

      slugJsonObject.put("list",tagJsonArray);
      slugJsonArray.add(slugJsonObject);
    }



    return slugJsonArray.toString();
  }


  // 修改了数据库的数据，同时更新缓存。先调用目标方法，然后缓存方法结果。
  // 只能是result.id
  public int insertItem(Item item,String token) {
    if(JwtTokenUtil.validateToken(token)){

      return navMapper.insertItem(item);
    }else{
      return 0;
    }
  }

  public int updateItem(Item item,String token) {
    if(JwtTokenUtil.validateToken(token)){
      return navMapper.updateItem(item);
    }
    return 0;
  }

  public String getTags() {
    List<Tag> tagList = navMapper.getAllTags();
    List<Slug> slugList = navMapper.getAllSlug();
    HashMap slugMap = new HashMap();
    JSONArray tagJsonArray = new JSONArray();

    for (Slug slug:slugList) {
      slugMap.put(slug.getId(),slug.getMemo());
    }

    for (Tag tag:tagList){
      JSONObject object = new JSONObject();
      object.put("name",slugMap.get(tag.getSlug_id()) + "-" + tag.getTitle());
      object.put("id",tag.getId());
      tagJsonArray.add(object);
    }
    return tagJsonArray.toString();
  }

//
//  //删除数据之后，清除缓存
//  @CacheEvict(value = "nav", key = "targetClass + methodName")
//  public String deleteUser(Integer id) {
//    userInfoMapper.deleteUserById(id);
//    return "已删除";
//  }
}

