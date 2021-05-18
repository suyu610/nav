package com.qdu.nav.controller;

import com.qdu.nav.entity.PO.Item;
import com.qdu.nav.entity.PO.StuInfo;
import com.qdu.nav.entity.VO.Result;
import com.qdu.nav.entity.VO.StuInfoVO;
import com.qdu.nav.service.StuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @ClassName StuController
 * @Description TODO
 * @Author uuorb
 * @Date 2021/5/18 11:55 上午
 * @Version 0.1
 **/

@CrossOrigin(origins = {"https://www.qdu.life","https://cdns.qdu.life"})
@RestController("/stu")
public class StuController {
  @Autowired
  StuService service;
  /*
    @params: name,number
    @return: {  code:200,
                msg:"成功:,
                data:{
                  stu_info:{},
                  status:"true|error_number|error_name|none"
                }
   */
  @GetMapping("/check")
  Result<StuInfoVO> checkStuInfo(@RequestBody StuInfo stu) {
    System.out.println(stu.getName());
    System.out.println(stu.getSsNumber());
    StuInfoVO stuInfo = service.check(stu.getName(), stu.getSsNumber());
    if (stuInfo != null) {
      return Result.ok(stuInfo);
    } else {
      return new Result(-1, "信息有误", null);
    }
  }


    @GetMapping("/search")
    Result<List<StuInfoVO>> searchStuInfo(@RequestBody StuInfo stu){
      List<StuInfoVO> stuInfoList = service.search(stu.getName());
      if(stuInfoList.size()!=0) {
        return Result.ok(stuInfoList);
      }else{
        return new Result(-1,"信息有误",null);
      }
  }

}
