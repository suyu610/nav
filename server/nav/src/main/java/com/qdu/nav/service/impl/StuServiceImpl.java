package com.qdu.nav.service.impl;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.qdu.nav.entity.PO.StuInfo;
import com.qdu.nav.entity.VO.SearchStuReqVO;
import com.qdu.nav.entity.VO.StuInfoVO;
import com.qdu.nav.mapper.StuMapper;
import com.qdu.nav.service.StuService;
import com.qdu.nav.util.BeanUtil;
import com.qdu.nav.util.JwtTokenUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;

/**
 * @ClassName StuService
 * @Description TODO
 * @Author uuorb
 * @Date 2021/5/18 1:05 下午
 * @Version 0.1
 **/
@Service
public class StuServiceImpl implements StuService {
  @Autowired
  StuMapper mapper;

  @Override
  @Cacheable(value = "stu", key = "methodName+#name+#ssNumber")
  public boolean check(String name,String ssNumber){
    StuInfo stuInfo = mapper.check(name,ssNumber);
    return StringUtils.equals(stuInfo.getSsNumber(),ssNumber);
  }

  @Override
  @Cacheable(value = "stu", key = "methodName+#searchStuReqVO")
  public PageInfo search(SearchStuReqVO searchStuReqVO){
    // 1. 开启分页 传入参数
    PageHelper.startPage(searchStuReqVO.getPageNum(), searchStuReqVO.getPageSize());

    // 2.查询数据
    List<StuInfoVO> stuInfoVOList = mapper.search(searchStuReqVO);

    // 3. 用PageInfo对结果进行包装
    PageInfo pageInfo = new PageInfo(stuInfoVOList);
    return pageInfo;
  }
}
