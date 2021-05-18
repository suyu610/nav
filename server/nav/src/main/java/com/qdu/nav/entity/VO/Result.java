package com.qdu.nav.entity.VO;

import com.qdu.nav.entity.PO.StuInfo;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @ClassName Result
 * @Description TODO
 * @Author uuorb
 * @Date 2021/5/18 12:54 下午
 * @Version 0.1
 **/
@Data
@AllArgsConstructor
public class Result<T> implements Serializable {
  int code;
  String msg;
  T data;

  public static<T> Result ok(T data){
    return new Result(200,"成功",data);
  }

}
