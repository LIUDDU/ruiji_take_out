package com.liu.boot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.liu.boot.pojo.Category;
import com.liu.boot.pojo.DishFlavor;
import com.liu.boot.pojo.dto.DishDTO;
import com.liu.boot.pojo.Dish;
import com.liu.boot.service.CategoryService;
import com.liu.boot.service.DishFlavorService;
import com.liu.boot.utils.R;
import com.liu.boot.service.DishService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author
 * @Date 2023/9/4 18:09
 * @Description 菜品控制类
 */
@RestController
@Slf4j
@RequestMapping("/dish")
public class DishController {

    @Autowired
    private DishService dishService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private DishFlavorService dishFlavorService;

    /**
     * 用于添加菜品
     * @param dishDTO
     * @return
     */
    @PostMapping
    public R<String> insertDish(@RequestBody DishDTO dishDTO){

        log.info("dishDTO数据：{}",dishDTO);

        Boolean insertDishResult = dishService.saveDishAndDishFlavor(dishDTO);

        if (!insertDishResult){
            return R.error("添加失败");
        }
        return R.success("添加成功");
    }


    /**
     * 菜品管理 分页查询
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page> selectDishList(Integer page,Integer pageSize,String name){

        log.info("page:{},pageSize:{},name:{}",page,pageSize,name);
        //分页参数
        Page<Dish> pageInfo = new Page<>(page,pageSize);
        //创建一个 Page<DishDTO> 用于返回数据给前端，包括分类名
        Page<DishDTO> dishDTOPage = new Page<>();
        //构建条件
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotEmpty(name),Dish::getName,name);
        queryWrapper.orderByDesc(Dish::getUpdateTime);

        //执行sql
        Page<Dish> dishPage = dishService.page(pageInfo, queryWrapper);

        //对象拷贝,不拷贝列表数据records
        BeanUtils.copyProperties(dishPage,dishDTOPage,"records");
        //获取列表数据
        List<Dish> records = pageInfo.getRecords();
        //处理列表数据
        List<DishDTO> list = records.stream().map((item) -> {

            DishDTO dishDTO = new DishDTO();
            //将pageInfo的数据拷贝到dishDTOPage中
            BeanUtils.copyProperties(item, dishDTO);
            //获取分类id
            Long categoryId = item.getCategoryId();
            //通过分类id查询分类信息
            Category category = categoryService.getById(categoryId);
            if (category != null){
                //获取分类名
                String categoryName = category.getName();
                //为dishDTO中的categoryName赋值
                dishDTO.setCategoryName(categoryName);
            }

            return dishDTO;
        }).collect(Collectors.toList());

        dishDTOPage.setRecords(list);

        log.info("dishPage:{}",dishPage.toString());

        log.info("dishDTOPage:{}",dishDTOPage.toString());
        return R.success(dishDTOPage);
    }

    /**
     * 根据菜品id查询菜品信息，用于修改菜品时的回显
     * @return
     */
    @GetMapping("/{id}")
    public R<DishDTO> selectDishById(@PathVariable Long id){

//        log.info("要修改的菜品id:{}",id);
//        Dish dish = dishService.getById(id);
//        if (dish == null){
//            return R.error("失败");
//        }
//
//        return R.success(dish);
        DishDTO dishDTO = dishService.getDishAndFlavorById(id);

        if (dishDTO == null){
            return R.error("回显失败");
        }
        log.info("dishDTO:{}",dishDTO);
        return R.success(dishDTO);
    }

    /**
     * 用于菜品的修改
     * @param dishDTO
     * @return
     */
    @PutMapping
    public R<String> updateDishAndFlavor(@RequestBody DishDTO dishDTO){

        log.info("disDTO:{}",dishDTO.toString());

        Boolean updateResult = dishService.updateDishAndFlavor(dishDTO);

        if (!updateResult){
            return R.error("修改菜品失败");
        }
        return R.success("修改菜品成功");
    }

    /**
     * 用于修改起售，停售
     *
     * 0 停售 1 起售
     *
     * @param ids
     * @return
     */
    @PostMapping("/status/{status}")
    public R<String> updateStatus(@PathVariable Integer status,
                                  @RequestParam("ids") List<Long> ids){

        log.info("要修改的状态,0 停售 1 起售:{}",status);
        log.info("要修改状态的菜品ids:{}",ids);

        ArrayList<Dish> dishes = new ArrayList<>();
        ids.forEach((id) -> {
            Dish dish = new Dish();
            dish.setStatus(status);
            dish.setId(id);
            dishes.add(dish);
        });
        boolean updateBatchByIdResult = dishService.updateBatchById(dishes);

        if (!updateBatchByIdResult){
            R.error("状态修改失败");
        }
        return R.success("状态修改成功");
    }

    /**
     * 根据ids删除菜品
     * @param ids
     * @return
     */
    @DeleteMapping
    public R<String> delete(@RequestParam("ids") List<Long> ids){
        log.info("要删除的ids:{}",ids);


        boolean removeResult = dishService.removeByIds(ids);

        if (!removeResult){
            return R.error("删除失败");
        }
        return R.success("删除成功");
    }

    /**
     * 根据分类id查询菜品信息，用于套餐增加页面，套餐菜品的回显
     * @param dish
     * @return
     */
    @GetMapping("/list")
    public R<List<DishDTO>> selectDishByCategoryId(Dish dish){

//        log.info("分类id:categoryId:{}",dish.getCategoryId());
//        //根据分类id查询菜品
//        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
//        //组装条件，根据分类id查询菜品
//        queryWrapper.eq(dish.getCategoryId() != null ,Dish::getCategoryId,dish.getCategoryId());
//        //按照修改时间进行排序
//        queryWrapper.orderByDesc(Dish::getUpdateTime);
//        //名字不为空时组装条件
//        queryWrapper.like(StringUtils.isNotEmpty(dish.getName()),Dish::getName,dish.getName());
//
//        //组装条件，查询起售的菜品,停售的菜品不展示在套餐选择菜品页面
//        queryWrapper.eq(Dish::getStatus,1);
//
//        List<Dish> list = dishService.list(queryWrapper);
//
//        log.info("分类id为" + dish.getCategoryId() + "的菜品有" + list.toString());
//
//        if (list == null){
//            R.error("没有相关菜品");
//        }
//        return R.success(list);

        //基于后台菜品，改造移动端也可以使用，移动端需要dishFlavor
        log.info("分类id:categoryId:{}",dish.getCategoryId());
        //根据分类id查询菜品
        LambdaQueryWrapper<Dish> queryWrapper = new LambdaQueryWrapper<>();
        //组装条件，根据分类id查询菜品
        queryWrapper.eq(dish.getCategoryId() != null ,Dish::getCategoryId,dish.getCategoryId());
        //按照修改时间进行排序
        queryWrapper.orderByDesc(Dish::getUpdateTime);
        //名字不为空时组装条件
        queryWrapper.like(StringUtils.isNotEmpty(dish.getName()),Dish::getName,dish.getName());

        //组装条件，查询起售的菜品,停售的菜品不展示在套餐选择菜品页面
        queryWrapper.eq(Dish::getStatus,1);

        List<Dish> list = dishService.list(queryWrapper);

        //处理List<Dish>，返回List<DishDTO>供管理端和移动端使用

        List<DishDTO> dishDTOS = list.stream().map((item) -> {

            DishDTO dishDTO = new DishDTO();
            //对象拷贝
            BeanUtils.copyProperties(item, dishDTO);
            //获取dishId,用于查询菜品口味
            Long dishId = item.getId();

            LambdaQueryWrapper<DishFlavor> lambdaQueryWrapper = new LambdaQueryWrapper<>();
            lambdaQueryWrapper.eq(DishFlavor::getDishId, dishId);
            List<DishFlavor> dishFlavorList = dishFlavorService.list(lambdaQueryWrapper);
            //将查询到的口味信息，放入DishDTO中
            dishDTO.setFlavors(dishFlavorList);
            return dishDTO;
        }).collect(Collectors.toList());

        return R.success(dishDTOS);
    }

}
