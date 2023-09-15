package com.liu.boot.service;

import com.liu.boot.pojo.Dish;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liu.boot.pojo.dto.DishDTO;


/**
* @author liududu
* @description 针对表【dish(菜品管理)】的数据库操作Service
* @createDate 2023-09-04 18:08:05
*/
public interface DishService extends IService<Dish> {

    /**
     * 添加数据到菜品表，同时插入数据到菜品口味表
     * @param dishDTO
     * @return
     */
    Boolean saveDishAndDishFlavor(DishDTO dishDTO);

    /**
     * 根据id查询菜品信息以及口味,用于在修改页面回显数据
     * @param id
     * @return
     */
    DishDTO getDishAndFlavorById(Long id);

    /**
     * 用于修改菜品信息
     * @param dishDTO
     * @return
     */
    Boolean updateDishAndFlavor(DishDTO dishDTO);


}
