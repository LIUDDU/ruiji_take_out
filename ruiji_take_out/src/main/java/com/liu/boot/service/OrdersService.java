package com.liu.boot.service;

import com.liu.boot.pojo.Orders;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.servlet.http.HttpServletRequest;

/**
* @author liududu
* @description 针对表【orders(订单表)】的数据库操作Service
* @createDate 2023-09-09 14:58:04
*/
public interface OrdersService extends IService<Orders> {

    /**
     * 用户下单
     * @param orders
     */
    Boolean submit(Orders orders, HttpServletRequest request);

}
