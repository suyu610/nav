package com.qdu.nav.mapper;

import com.qdu.nav.entity.PO.StuInfo;
import com.qdu.nav.entity.VO.SearchStuReqVO;
import com.qdu.nav.entity.VO.StuInfoVO;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

@Mapper
public interface StuMapper {
  public StuInfo check(String name,String ssNumber);
  public List<StuInfoVO> search(SearchStuReqVO req);
}
