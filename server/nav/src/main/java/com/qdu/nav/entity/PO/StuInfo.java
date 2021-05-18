package com.qdu.nav.entity.PO;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName StuInfo
 * @Description TODO
 * @Author uuorb
 * @Date 2021/5/18 12:55 下午
 * @Version 0.1
 **/
@Data
public class StuInfo implements Serializable {
  int id;
  String name;
  String ssNumber;
  int type;
  String major;
  String className;
  int year;
  String gender;
  String qq;
  String idNumber;
}
