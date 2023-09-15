package com.liu.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.boot.exception.CustomServiceException;
import com.liu.boot.mapper.DishMapper;
import com.liu.boot.mapper.SetmealMapper;
import com.liu.boot.pojo.Category;
import com.liu.boot.pojo.Dish;
import com.liu.boot.pojo.Setmeal;
import com.liu.boot.service.CategoryService;
import com.liu.boot.mapper.CategoryMapper;
import com.liu.boot.service.DishService;
import com.liu.boot.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
* @author liududu
* @description 针对表【category(菜品及套餐分类)】的数据库操作Service实现
* @createDate 2023-09-04 16:22:11
*/
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category>
    implements CategoryService{

    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;

    /**
     * 用于删除分类,根据id
     * @param id
     * @return
     */
    @Override
    public Boolean remove(Long id) {

        //删除分类时，要查询是否和菜品有关联，有则不能删除
        //组装条件
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        dishLambdaQueryWrapper.eq(Dish::getCategoryId,id);
        int dishCount = dishService.count(dishLambdaQueryWrapper);
        //判断
        if(dishCount > 0){
            //有关联，抛出异常
            throw new CustomServiceException("当前分类关联了菜品，不能删除");

        }
        //删除分类时，要查询是否和套餐有关联，有则不能删除
        //组装条件
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId,id);
        int setmealCount = setmealService.count(setmealLambdaQueryWrapper);

        //判断
        if (setmealCount > 0){
            //有关联，抛出异常
            throw new CustomServiceException("当前分类关联了套餐，不能删除");
        }

        //没有关联任何菜品，套餐可以删除分类
        boolean b = super.removeById(id);

        return b;
    }
}




