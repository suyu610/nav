package com.qdu.nav.service;

import com.qdu.nav.util.JwtTokenUtil;

/**
 * @ClassName NoteService
 * @Description TODO
 * @Author uuorb
 * @Date 2021/5/18 2:47 下午
 * @Version 0.1
 **/

public interface NoteService {

  public String getNote();

  public void saveNote(String note);
}
