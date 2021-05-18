package com.qdu.nav.service.impl;

/**
 * @ClassName NavService
 * @Description TODO
 * @Author uuorb
 * @Date 2021/5/12 9:38 下午
 * @Version 0.1
 **/

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qdu.nav.entity.PO.Item;
import com.qdu.nav.entity.PO.Slug;
import com.qdu.nav.entity.PO.Tag;
import com.qdu.nav.mapper.NavMapper;
import com.qdu.nav.service.NavService;
import com.qdu.nav.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service("NavService")
public class NavServiceImpl implements NavService{
  @Autowired
  NavMapper navMapper;

  public String delItem(String itemId){
    JSONObject jsonObject = new JSONObject();
    // 删除的个数
    String title = navMapper.getItemNameByItemId(itemId);

    int delCount =  navMapper.delItem(itemId);
    System.out.println("==============");
    System.out.println(delCount);
    System.out.println("==============");

    if(delCount>=1){
      System.out.println(title);
      jsonObject.put("name",title);
    }else{
      return null;
    }
    return jsonObject.toString();
  }

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
  public int insertItem(Item item) {
      return navMapper.insertItem(item);
  }

  public int updateItem(Item item) {
      return navMapper.updateItem(item);
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
}

