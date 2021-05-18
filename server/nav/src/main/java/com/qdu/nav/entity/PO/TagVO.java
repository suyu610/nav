package com.qdu.nav.entity.PO;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName TagVO
 * @Description TODO
 * @Author uuorb
 * @Date 2021/5/13 3:18 上午
 * @Version 0.1
 **/
@Data
public class TagVO implements Serializable {
  String tagName;
  String slugName;
  int tagId;
}
