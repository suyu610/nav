package com.qdu.nav.mapper;

import com.qdu.nav.entity.User;

public interface NoteMapper {
  public String getNote();
  public void saveNote(String note);
}
