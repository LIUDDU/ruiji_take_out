package com.liu.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.boot.exception.CustomServiceException;
import com.liu.boot.pojo.Setmeal;
import com.liu.boot.pojo.SetmealDish;
import com.liu.boot.pojo.dto.SetmealDTO;
import com.liu.boot.service.SetmealDishService;
import com.liu.boot.service.SetmealService;
import com.liu.boot.mapper.SetmealMapper;
import com.liu.boot.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
* @author liududu
* @description 针对表【setmeal(套餐)】的数据库操作Service实现
* @createDate 2023-09-05 09:09:30
*/
@Service
@Slf4j
public class SetmealServiceImpl extends ServiceImpl<SetmealMapper, Setmeal>
    implements SetmealService{

    @Autowired
    private SetmealDishService setmealDishService;

    /**
     * 由于添加套餐信息，以及套餐内菜品信息，
     * 由于操作【setmeal】,【setmenal_dish】两张表
     * 加入事务
     *
     * @param setmealDTO
     * @return
     */
    @Transactional
    @Override
    public Boolean saveSetmealAndDish(SetmealDTO setmealDTO) {

        //向setmeal表添加数据
        boolean saveResult = this.save(setmealDTO);

        //获取套餐id,setmeal_id用于添加套餐菜品是，为setMealId赋值
        Long setmealDisnId = setmealDTO.getId();
        //获取setmealDTO中setmealDishes的数据
        List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
        //处理setmealDishesdes的数据，为setmealDisnId赋值
        setmealDishes = setmealDishes.stream().map((item) -> {
            item.setSetmealId(setmealDisnId);
            return item;
        }).collect(Collectors.toList());
        log.info("套餐内菜品信息：{}",setmealDishes);
        //向setmeal_dish表添加数据

        boolean saveBatchResult = setmealDishService.saveBatch(setmealDishes);

        return saveResult && saveBatchResult;
    }

    /**
     * 用于删除套餐信息，以及套餐内菜品信息
     *
     * 操作【setmeal】表
     *
     * 【setmeal_dish】表
     *
     * @param ids
     * @return
     */
    @Transactional
    @Override
    public Boolean removeSetmealWithDish(List<Long> ids) {
        //select count(*) from setmeal where id in (?,?,?) and status = ?
        //1.先查询套餐是否属于停售状态，停售状态下不可以删除
        //状态 0:停用 1:启用
        LambdaQueryWrapper<Setmeal> queryWrapper = new LambdaQueryWrapper<>();
        //组装条件
        queryWrapper.in(Setmeal::getId,ids);
        queryWrapper.eq(Setmeal::getStatus,1);
        int count = this.count(queryWrapper);

        //2.若处于停售状态下，抛出业务异常
        if (count > 0){
            throw new CustomServiceException("套餐处于售卖中，不能删除");
        }

        //3.移除套餐内菜品信息
        //delete from setmeal_dish where setmeal_id in(?,?,?)
        LambdaQueryWrapper<SetmealDish> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(SetmealDish::getSetmealId,ids);
        boolean removeSetmealWithDishResult = setmealDishService.remove(wrapper);

        //4.移除套餐信息
        boolean removeSetmealResult = super.removeByIds(ids);

        return removeSetmealWithDishResult && removeSetmealResult;
    }


    /**
     * 用于套餐修改页面的回显
     * @param id
     * @return
     */
    @Override
    public SetmealDTO getSetmealAndDishById(Long id) {

        //查询套餐信息【setmeal】表
        Setmeal setmeal = this.getById(id);

        //对象拷贝
        SetmealDTO setmealDTO = new SetmealDTO();
        BeanUtils.copyProperties(setmeal,setmealDTO);

        //根据套餐id查询套餐内的菜品
        //组装条件
        LambdaQueryWrapper<SetmealDish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SetmealDish::getSetmealId,id);
        //套餐内的菜品信息【setmeal_dish】表
        List<SetmealDish> setmealDishList = setmealDishService.list(queryWrapper);
        //为setmealDTO的setmealDishes赋值
        setmealDTO.setSetmealDishes(setmealDishList);
        return setmealDTO;
    }

    /**
     * 用于套餐信息的修改
     *
     * 涉及【setmeal】表
     *
     * 【setmeal_dish】表
     *
     * @param setmealDTO
     * @return
     */
    @Transactional
    @Override
    public Boolean update(SetmealDTO setmealDTO) {

        //1.更新套餐信息
        boolean updateResult = this.updateById(setmealDTO);
        //2.更新套餐内菜品信息
        //先清理原套餐内菜品信息
        LambdaQueryWrapper<SetmealDish> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(SetmealDish::getSetmealId,setmealDTO.getId());
        //执行删除原套餐内菜品信息
        boolean remove = setmealDishService.remove(queryWrapper);
        //若移除执行成功，将修改的套餐内菜品信息，重新加入【setmeal_diah】表
        //保存添加结果
        boolean saveBatch = false;

        if (remove){
            //获取setmealDTO中的setmealDishes（菜品信息）
            List<SetmealDish> setmealDishes = setmealDTO.getSetmealDishes();
            //设置【setmeal_diah】表的套餐id【setmeal_id】
            List<SetmealDish> setmealDishList = setmealDishes.stream().map((item) -> {
                item.setSetmealId(setmealDTO.getId());
                return item;
            }).collect(Collectors.toList());

            saveBatch = setmealDishService.saveBatch(setmealDishList);
        }

        return updateResult && saveBatch;
    }


}




