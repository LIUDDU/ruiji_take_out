package com.liu.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;

import com.liu.boot.pojo.Dish;
import com.liu.boot.pojo.DishFlavor;
import com.liu.boot.pojo.dto.DishDTO;
import com.liu.boot.service.CategoryService;
import com.liu.boot.service.DishFlavorService;
import com.liu.boot.service.DishService;
import com.liu.boot.mapper.DishMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/**
* @author liududu
* @description 针对表【dish(菜品管理)】的数据库操作Service实现
* @createDate 2023-09-04 18:08:05
*/
@Service
@Slf4j
public class DishServiceImpl extends ServiceImpl<DishMapper, Dish>
    implements DishService{

    @Autowired
    private DishFlavorService dishFlavorService;

    /**
     * 增加数据到菜品表，同时保存菜品的口味到菜品口味表
     *
     * 涉及到多张表，加入事务
     *
     * @param dishDTO
     * @return
     */
    //事务管理，发生异常时回滚
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Boolean saveDishAndDishFlavor(DishDTO dishDTO) {

        //增加菜品数据到菜品表
        //执行DishService的保存方法，执行sql
        boolean saveDishResult = super.save(dishDTO);

        //获取菜品id,dish_id
        Long dishId = dishDTO.getId();

        //增加数据到菜品口味表
        List<DishFlavor> dishFlavors = dishDTO.getFlavors();
        //为DishFlavor实体赋值,通过stream流处理集合,加入dishId
        //获得一个关于DishFlavor的集合
        dishFlavors = dishFlavors.stream().map((item) -> {
            item.setDishId(dishId);
            return item;
        }).collect(Collectors.toList());

        //执行dishFlavorService的添加方法
        boolean saveDishFlavorResult = dishFlavorService.saveBatch(dishFlavors);

        //返回添加结果
        return saveDishResult && saveDishFlavorResult;
    }


    /**
     * 根据菜品id查询菜品信息，以及口味信息
     *
     * 涉及【菜品表】 ，【菜品口味表】
     *
     * @param id
     * @return
     */
    @Override
    public DishDTO getDishAndFlavorById(Long id) {

        //根据id查询菜品信息
        Dish dish = this.getById(id);
        //对象拷贝，将Dish拷贝到DishDTO中
        DishDTO dishDTO = new DishDTO();
        BeanUtils.copyProperties(dish,dishDTO);
        //根据菜品id查询口味
        //组装条件
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId, id);

        List<DishFlavor> flavors = dishFlavorService.list(queryWrapper);
        //为DishDTO的flavors赋值
        dishDTO.setFlavors(flavors);


        return dishDTO;
    }

    /**
     * 用于修改菜品信息
     *
     * 涉及【菜品表】，【菜品口味表】 需要加入事务管理
     *
     * @param dishDTO
     * @return
     */
    @Transactional
    @Override
    public Boolean updateDishAndFlavor(DishDTO dishDTO) {

        //更新菜品表【dish】的信息
        boolean updateDishResult = super.updateById(dishDTO);
        //先清理原口味信息
        LambdaQueryWrapper<DishFlavor> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(DishFlavor::getDishId,dishDTO.getId());
        //执行清理原口味信息sql
        boolean removeResult = dishFlavorService.remove(queryWrapper);

        //定义保存新增结果，扩大作用域
        boolean saveBatchResult = false;

        if (removeResult ){
            //增加要修改的口味数据
            List<DishFlavor> flavors = dishDTO.getFlavors();
            //设置dishId
            List<DishFlavor> flavorList = flavors.stream().map((item) -> {
                item.setDishId(dishDTO.getId());
                return item;
            }).collect(Collectors.toList());

            saveBatchResult = dishFlavorService.saveBatch(flavorList);
        }
        return updateDishResult && saveBatchResult;
    }

}




