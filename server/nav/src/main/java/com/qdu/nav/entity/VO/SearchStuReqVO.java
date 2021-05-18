package com.qdu.nav.entity.VO;

import lombok.Data;

/**
 * @ClassName SearchStuReqVO
 * @Description TODO
 * @Author uuorb
 * @Date 2021/5/18 3:20 下午
 * @Version 0.1
 **/
@Data
public class SearchStuReqVO {
  String name;
  String ssNumber;
  String gender;
  int year;
  int pageNum;
  int pageSize;
}
