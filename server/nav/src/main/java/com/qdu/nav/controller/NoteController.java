package com.qdu.nav.controller;

import com.qdu.nav.service.NoteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName NoteController
 * @Description TODO
 * @Author uuorb
 * @Date 2021/5/14 7:09 上午
 * @Version 0.1
 **/

@CrossOrigin("https://www.qdu.life")
@RestController
public class NoteController {
  @Autowired
  NoteService noteService;
  @GetMapping("/getNote")
  public String getNote(HttpServletRequest httpRequest){
    String token = (String) httpRequest.getHeader("token");
    return noteService.getNote(token);
  }

  @PostMapping("/saveNote")
  public void saveNote(HttpServletRequest httpRequest,@RequestBody String note){
    String token = (String) httpRequest.getHeader("token");
//    System.out.println(note);
    noteService.saveNote(token,note);
  }
}
