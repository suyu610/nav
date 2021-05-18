package com.qdu.nav.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName Item
 * @Description TODO
 * @Author uuorb
 * @Date 2021/5/12 9:53 下午
 * @Version 0.1
 **/
@Data
public class Item implements Serializable {
  int id;
  String title;
  String url;
  int tagId;
  int isPrivate;
  String desc;
}
