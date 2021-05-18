package com.qdu.nav.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName User
 * @Description TODO
 * @Author uuorb
 * @Date 2021/5/12 9:53 下午
 * @Version 0.1
 **/

@Data
public class User implements Serializable {
  String username;
  String password;
  int id;
}
