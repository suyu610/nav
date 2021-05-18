package com.qdu.nav.service;

import com.github.pagehelper.PageInfo;
import com.qdu.nav.entity.VO.SearchStuReqVO;
import com.qdu.nav.entity.VO.StuInfoVO;
import com.qdu.nav.util.BeanUtil;
import org.springframework.beans.BeanUtils;

import java.util.List;

public interface StuService {

  boolean check(String name, String ssNumber);

  PageInfo search(SearchStuReqVO searchStuReqVO);
}
