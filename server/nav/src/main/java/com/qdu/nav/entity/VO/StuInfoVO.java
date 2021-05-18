package com.qdu.nav.entity.VO;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName StuVO
 * @Description TODO
 * @Author uuorb
 * @Date 2021/5/18 1:27 下午
 * @Version 0.1
 **/
@Data
public class StuInfoVO implements Serializable {
  String name;
  String ssNumber;
  String major;
  String className;
  int year;
  String gender;
  String qq;
  String idNumber;
}
