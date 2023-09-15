package com.liu.boot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liu.boot.pojo.Category;
import com.liu.boot.pojo.Dish;
import com.liu.boot.pojo.Setmeal;
import com.liu.boot.pojo.SetmealDish;
import com.liu.boot.pojo.dto.SetmealDTO;
import com.liu.boot.service.CategoryService;
import com.liu.boot.service.SetmealDishService;
import com.liu.boot.service.SetmealService;
import com.liu.boot.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author
 * @Date 2023/9/5 9:10
 * @Description 套餐控制类
 */
@Slf4j
@RestController
@RequestMapping("/setmeal")
public class SetmealController {

    @Autowired
    private SetmealService setmealService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private SetmealDishService setmealDishService;

    /**
     * 添加套餐数据，包括套餐内菜品的数据
     * @param setmealDTO
     * @return
     */
    @PostMapping
    public R<String> insertSetmeal(@RequestBody SetmealDTO setmealDTO){


        log.info("setmealDTO:{}",setmealDTO);
        //执行添加套餐的方法
        Boolean saveResult = setmealService.saveSetmealAndDish(setmealDTO);

        if (!saveResult){
            return R.error("添加失败");
        }

        return R.success("添加成功");
    }

    /**
     * 分页查询，用于套餐页面的展示
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page<SetmealDTO>> selectSetmealList(Integer page,Integer pageSize,String name){

        log.info("page:{},pageSize:{},name:{}",page,pageSize,name);

        //分页参数
        Page<Setmeal> pageInfo = new Page<>();
        //创建一个Page<SetmealDTO>，将Page<Setmeal>的数据拷贝到Page<SetmealDTO>
        //用于返回给前端数据
        Page<SetmealDTO> setmealDTOPage = new Page<>();

        //组装条件
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        //按修改时间进行排序
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);
        //若套餐名不为空时，组装条件
        queryWrapper.like(StringUtils.isNotEmpty(name),Setmeal::getName,name);
        Page<Setmeal> pageList = setmealService.page(pageInfo, queryWrapper);

        //对象拷贝,排除分页查询的套餐数据
        BeanUtils.copyProperties(pageList,setmealDTOPage,"records");

        //获取套餐信息
        List<Setmeal> records = pageInfo.getRecords();
        //处理records
        List<SetmealDTO> list = records.stream().map((item) -> {
            //将pageInfo中的数据拷贝到setmealDTO
            SetmealDTO setmealDTO = new SetmealDTO();

            BeanUtils.copyProperties(item, setmealDTO);
            //获取分类id
            Long categoryId = item.getCategoryId();
            //通过分类id查询分类名
            Category category = categoryService.getById(categoryId);

            if (category != null) {
                //分类名
                String categoryName = category.getName();
                //将分类名赋给setmealDTO
                setmealDTO.setCategoryName(categoryName);
            }
            //返回setmealDTO
            return setmealDTO;
        }).collect(Collectors.toList());

        //给setmealDTOPage赋值，
        setmealDTOPage.setRecords(list);

        log.info("分页数据pageList:{}",pageList);

        log.info("setmealDTOPage:{}",setmealDTOPage);
        return R.success(setmealDTOPage);
    }


    /**
     * 用于改变套餐的的状态
     *
     * 状态 0:停用 1:启用
     *
     * @param status
     * @param ids
     * @return
     */
    @PostMapping("/status/{status}")
    public R<String> updateStatus(@PathVariable("status") Integer status,
                                  @RequestParam("ids") List<Long> ids){

        log.info("要修改的状态：{}",status);
        log.info("要修改状态的套餐ids:{}",ids);

        ArrayList<Setmeal> setmeals = new ArrayList<>();
        //为每个Setmeal赋值，
        ids.forEach((id) -> {
            Setmeal setmeal = new Setmeal();
            setmeal.setStatus(status);
            setmeal.setId(id);
            setmeals.add(setmeal);
        });

        //执行修改
        boolean updateResult = setmealService.updateBatchById(setmeals);

        if (!updateResult){
            return R.error("状态修改失败");
        }

        return R.success("状态修改成功");
    }

    /**
     * 根据ids删除套餐
     * @param ids
     * @return
     */
    @DeleteMapping
    public R<String> delete(@RequestParam("ids") List<Long> ids){

        log.info("要删除的ids:{}",ids);

        //删除套餐信息
        Boolean removeResult = setmealService.removeSetmealWithDish(ids);

        if (!removeResult){
            return R.error("删除失败");
        }
        return R.success("删除成功");
    }

    /**
     * 用户套餐修改页面的回显
     * @param id
     * @return
     */
    @GetMapping("{id}")
    public R<SetmealDTO> selectSetmealById(@PathVariable("id") Long id){

        log.info("要回显的套餐id:{}",id);

        //执行套餐信息，以及套餐内的菜品信息
        SetmealDTO setmealAndDishById = setmealService.getSetmealAndDishById(id);

        if (setmealAndDishById == null ){
            return R.error("套餐回显失败");
        }
        log.info("setmealDishes:{}",setmealAndDishById);
        return R.success(setmealAndDishById);
    }

    /**
     * 用于套餐信息修改
     * @param setmealDTO
     * @return
     */
    @PutMapping
    public R<String> update(@RequestBody SetmealDTO setmealDTO){
        log.info("SetmealDTO:{}",setmealDTO);

        //执行修改
        Boolean update = setmealService.update(setmealDTO);

        if (!update){
            return R.error("套餐修改失败");
        }

        return R.success("套餐修改成功");
    }

    /**
     * 用于移动端套餐的显示
     * @param setmeal
     * @return
     */
    @GetMapping("/list")
    public R<List<SetmealDTO>> list(Setmeal setmeal){

        log.info("setmeal:{}",setmeal);
        //根据分类id查询套餐

        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        //组装条件
        queryWrapper.eq(setmeal.getCategoryId() != null ,Setmeal::getCategoryId,setmeal.getCategoryId());
        //同时状态要使起售状态的 状态 0:停用 1:启用
        queryWrapper.eq(setmeal.getStatus() != null ,Setmeal::getStatus,1);
        //根据修改条件进行排序
        queryWrapper.orderByDesc(Setmeal::getUpdateTime);
        List<Setmeal> list = setmealService.list(queryWrapper);

        //将 List<Setmeal> 处理成 List<SetmealDTO> 返回

        List<SetmealDTO> setmealDTOS = list.stream().map((item) -> {
            SetmealDTO setmealDTO = new SetmealDTO();

            BeanUtils.copyProperties(item, setmealDTO);

            //用套餐id查询菜品
            Long setmealId = item.getId();

            LambdaQueryWrapper<SetmealDish> wrapper = new LambdaQueryWrapper<>();
            wrapper.eq(SetmealDish::getSetmealId, setmealId);
            List<SetmealDish> setmealDishList = setmealDishService.list(wrapper);

            setmealDTO.setSetmealDishes(setmealDishList);
            return setmealDTO;
        }).collect(Collectors.toList());
        return R.success(setmealDTOS);
    }

    /**
     * 用于移动端套餐菜品的查看
     * @param setmealId
     * @return
     */
    @GetMapping("/dish/{setmealId}")
    public R<SetmealDTO> getDishBySetmealId(@PathVariable("setmealId") Long setmealId){

        LambdaQueryWrapper<SetmealDish> queryWrapper = new LambdaQueryWrapper<>();
        //根据套餐id查菜品
        queryWrapper.eq(SetmealDish::getSetmealId,setmealId);
        List<SetmealDish> list = setmealDishService.list(queryWrapper);

        //根据根据套餐id查套餐信息
        Setmeal setmeal = setmealService.getById(setmealId);

        SetmealDTO setmealDTO = new SetmealDTO();
        BeanUtils.copyProperties(setmeal,setmealDTO);
        setmealDTO.setSetmealDishes(list);
        return R.success(setmealDTO);
    }
}
