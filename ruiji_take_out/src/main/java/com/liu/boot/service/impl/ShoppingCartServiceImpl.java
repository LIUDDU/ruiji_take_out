package com.liu.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.boot.pojo.ShoppingCart;
import com.liu.boot.service.ShoppingCartService;
import com.liu.boot.mapper.ShoppingCartMapper;
import org.springframework.stereotype.Service;

/**
* @author liududu
* @description 针对表【shopping_cart(购物车)】的数据库操作Service实现
* @createDate 2023-09-08 17:57:42
*/
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart>
    implements ShoppingCartService{

}




