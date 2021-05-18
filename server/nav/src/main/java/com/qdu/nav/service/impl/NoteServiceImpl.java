package com.qdu.nav.service;

import com.qdu.nav.mapper.NoteMapper;
import com.qdu.nav.mapper.UserMapper;
import com.qdu.nav.util.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @ClassName NoteService
 * @Description TODO
 * @Author uuorb
 * @Date 2021/5/14 7:10 上午
 * @Version 0.1
 **/
@Service
public class NoteService {
  @Autowired
  NoteMapper noteMapper;

  public String getNote(String token){
    if(JwtTokenUtil.validateToken(token)){
      return noteMapper.getNote();
    }
    return "";
  }

  public void saveNote(String token,String note){
    if(JwtTokenUtil.validateToken(token)){
      noteMapper.saveNote(note);
    }else{
      System.out.println("Token 不正确");
    }
  }
}
