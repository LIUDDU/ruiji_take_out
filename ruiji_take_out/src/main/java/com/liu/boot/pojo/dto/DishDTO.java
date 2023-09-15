package com.liu.boot.pojo.dto;

import com.liu.boot.pojo.Dish;
import com.liu.boot.pojo.DishFlavor;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

/**
 * 用于接收前端传递来的菜品数据，包括口味
 */

@Data
public class DishDTO extends Dish {

    /**
     * 用于接送菜品口味
     */
    private List<DishFlavor> flavors = new ArrayList<>();

    /**
     * 分类名
     */
    private String categoryName;

    private Integer copies;
}
