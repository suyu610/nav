package com.qdu.nav.entity.VO;

import lombok.Data;

/**
 * @ClassName LoginReqVO
 * @Description TODO
 * @Author uuorb
 * @Date 2021/5/18 3:08 下午
 * @Version 0.1
 **/
@Data
public class LoginReqVO {
  String username;
  String password;
  String verCode;
}
