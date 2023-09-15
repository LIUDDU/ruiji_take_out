package com.liu.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.boot.pojo.OrderDetail;
import com.liu.boot.service.OrderDetailService;
import com.liu.boot.mapper.OrderDetailMapper;
import org.springframework.stereotype.Service;

/**
* @author liududu
* @description 针对表【order_detail(订单明细表)】的数据库操作Service实现
* @createDate 2023-09-09 15:00:51
*/
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail>
    implements OrderDetailService{

}




