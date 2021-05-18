package com.qdu.nav.entity.PO;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @ClassName Slug
 * @Description TODO
 * @Author uuorb
 * @Date 2021/5/12 9:53 下午
 * @Version 0.1
 **/
@Data
public class Slug implements Serializable {
  String title;
  String memo;
  int id;
}
