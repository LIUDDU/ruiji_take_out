package com.liu.boot.mapper;

import com.liu.boot.pojo.Dish;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author liududu
* @description 针对表【dish(菜品管理)】的数据库操作Mapper
* @createDate 2023-09-04 18:08:05
* @Entity com.liu.boot.pojo.Dish
*/
@Repository
public interface DishMapper extends BaseMapper<Dish> {

}




