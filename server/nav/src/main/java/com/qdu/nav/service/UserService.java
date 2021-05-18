package com.qdu.nav.service;

import com.alibaba.druid.util.StringUtils;
import com.alibaba.fastjson.JSONObject;
import com.qdu.nav.entity.PO.User;
import com.qdu.nav.entity.VO.Result;
import com.qdu.nav.util.JwtTokenUtil;

public interface UserService {

  public Result login(String username, String password);

  public boolean checkToken(String token);
}
