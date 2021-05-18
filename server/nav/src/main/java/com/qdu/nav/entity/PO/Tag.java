package com.qdu.nav.entity.PO;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName Tag
 * @Description TODO
 * @Author uuorb
 * @Date 2021/5/12 9:53 下午
 * @Version 0.1
 **/
@Data
public class Tag implements Serializable {
  int id;
  String title;
  int slug_id;
}
