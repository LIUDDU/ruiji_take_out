package com.liu.boot.pojo.dto;

import com.liu.boot.pojo.Setmeal;
import com.liu.boot.pojo.SetmealDish;
import lombok.Data;

import java.util.List;

/**
 * @Author
 * @Date 2023/9/7 10:53
 * @Description 用于接收前端传递来的套餐数据，以及套餐内菜品的数据
 */
@Data
public class SetmealDTO extends Setmeal {
    /**
     * 由于接收套餐菜品，集合
     */
    private List<SetmealDish> setmealDishes;

    private String categoryName;
}
