package com.qdu.nav.service.impl;

import com.qdu.nav.mapper.NoteMapper;
import com.qdu.nav.mapper.UserMapper;
import com.qdu.nav.service.NoteService;
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
public class NoteServiceImpl implements NoteService {
  @Autowired
  NoteMapper noteMapper;

  public String getNote(){
    return noteMapper.getNote();
  }

  public void saveNote(String note){
    noteMapper.saveNote(note);
  }
}
