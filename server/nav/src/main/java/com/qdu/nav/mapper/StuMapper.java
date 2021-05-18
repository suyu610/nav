package com.qdu.nav.mapper;

import com.qdu.nav.entity.PO.StuInfo;

import java.util.List;

public interface StuMapper {
  public StuInfo check(String name,String ssNumber);
  public List<StuInfo> search(String name);
}
