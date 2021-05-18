package com.qdu.nav.mapper;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoteMapper {
  public String getNote();
  public void saveNote(String note);
}
