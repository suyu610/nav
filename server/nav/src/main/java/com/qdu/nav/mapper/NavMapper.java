package com.qdu.nav.mapper;


import com.qdu.nav.entity.PO.Item;
import com.qdu.nav.entity.PO.Slug;
import com.qdu.nav.entity.PO.Tag;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;

import java.util.List;

/**
 * @ClassName NavMapper
 * @Description TODO
 * @Author uuorb
 * @Date 2021/5/12 9:38 下午
 * @Version 0.1
 **/

@Mapper
public interface NavMapper {

  String getItemNameByItemId(String id);
  List<Tag> getTagBySlutId(int slugId);
  @Cacheable(value = "nav", key = "methodName+#tagId+#privateFlag")
  List<Item> getItemByTagId(@Param("tagId")int tagId, @Param("privateFlag") int privateFlag);
  List<Slug> getAllSlug();
  //  删除所有的缓存
  @CacheEvict(value = "nav", allEntries = true)
  int insertItem(@Param("item")Item item);
  @CacheEvict(value = "nav", allEntries = true)
  int updateItem(@Param("item")Item item);
  List<Tag> getAllTags();
  // 返回值为影响的行数
  @CacheEvict(value = "nav", allEntries = true)
  int delItem(String itemId);
}
