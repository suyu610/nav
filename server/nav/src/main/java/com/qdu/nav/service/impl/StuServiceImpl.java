package com.qdu.nav.service;

import com.qdu.nav.entity.PO.StuInfo;
import com.qdu.nav.entity.VO.StuInfoVO;
import com.qdu.nav.mapper.StuMapper;
import com.qdu.nav.util.BeanUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
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
public class StuService {
  @Autowired
  StuMapper mapper;

  public StuInfoVO check(String name,String ssNumber){
    StuInfoVO stuInfoVO = new StuInfoVO();
    BeanUtils.copyProperties(mapper.search(name),stuInfoVO);
    return stuInfoVO;
  }

  public List<StuInfoVO> search(String name){
    List<StuInfoVO> stuInfoVOList = BeanUtil.copyListProperties(mapper.search(name),StuInfoVO::new);
    return stuInfoVOList;
  }
}
