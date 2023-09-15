package com.liu.boot.service;

import com.liu.boot.pojo.Setmeal;
import com.baomidou.mybatisplus.extension.service.IService;
import com.liu.boot.pojo.dto.SetmealDTO;

import java.util.List;

/**
* @author liududu
* @description 针对表【setmeal(套餐)】的数据库操作Service
* @createDate 2023-09-05 09:09:30
*/
public interface SetmealService extends IService<Setmeal> {

    /**
     * 用于添加套餐信息，以及套餐内菜品信息
     * @param setmealDTO
     * @return
     */
    Boolean saveSetmealAndDish(SetmealDTO setmealDTO);

    /**
     * 用于删除套餐信息，以及套餐内菜品信息
     * @param ids
     * @return
     */
    Boolean removeSetmealWithDish(List<Long> ids);

    /**
     * 用于套餐修改时的页面回显
     * @param id
     * @return
     */
    SetmealDTO getSetmealAndDishById(Long id);

    /**
     * 用于套餐信息的修改
     * @param setmealDTO
     * @return
     */
    Boolean update(SetmealDTO setmealDTO);

}
