package com.qdu.nav.service;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.qdu.nav.entity.PO.Item;
import com.qdu.nav.entity.PO.Slug;
import com.qdu.nav.entity.PO.Tag;
import com.qdu.nav.util.JwtTokenUtil;

import java.util.HashMap;
import java.util.List;

public interface NavService {

  public String delItem(String itemId);

  public String getAllPublicNav(String token);

  public int insertItem(Item item);

  public int updateItem(Item item);

  public String getTags() ;
}
