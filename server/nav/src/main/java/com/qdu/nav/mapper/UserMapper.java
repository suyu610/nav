package com.qdu.nav.mapper;

import com.qdu.nav.entity.PO.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
  public User isCorrect(String username);
}
