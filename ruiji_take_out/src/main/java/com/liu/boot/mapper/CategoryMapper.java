package com.liu.boot.mapper;

import com.liu.boot.pojo.Category;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author liududu
* @description 针对表【category(菜品及套餐分类)】的数据库操作Mapper
* @createDate 2023-09-04 16:22:11
* @Entity com.liu.boot.pojo.Category
*/
@Repository
public interface CategoryMapper extends BaseMapper<Category> {

}




