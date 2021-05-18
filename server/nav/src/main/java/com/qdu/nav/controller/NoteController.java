package com.qdu.nav.controller;

import com.qdu.nav.config.Authorization;
import com.qdu.nav.entity.VO.Result;
import com.qdu.nav.service.NoteService;
import com.qdu.nav.service.impl.NoteServiceImpl;
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

  @Authorization
  public Result getNote(){
    return Result.ok(noteService.getNote());
  }

  @PostMapping("/saveNote")
  @Authorization
  public Result saveNote(@RequestBody String note){
    noteService.saveNote(note);
    return Result.ok();
  }

}
