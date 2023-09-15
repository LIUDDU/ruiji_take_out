package com.liu.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.boot.pojo.DishFlavor;
import com.liu.boot.service.DishFlavorService;
import com.liu.boot.mapper.DishFlavorMapper;
import org.springframework.stereotype.Service;

/**
* @author liududu
* @description 针对表【dish_flavor(菜品口味关系表)】的数据库操作Service实现
* @createDate 2023-09-06 10:38:19
*/
@Service
public class DishFlavorServiceImpl extends ServiceImpl<DishFlavorMapper, DishFlavor>
    implements DishFlavorService{

}




