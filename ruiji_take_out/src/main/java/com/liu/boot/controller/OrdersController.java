package com.liu.boot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liu.boot.pojo.Orders;
import com.liu.boot.service.OrdersService;
import com.liu.boot.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @Author
 * @Date 2023/9/9 15:03
 * @Description 订单控制类
 */

@RestController
@Slf4j
@RequestMapping("/order")
public class OrdersController {

    @Autowired
    private OrdersService ordersService;

    /**
     * 用户下单
     * @param orders
     * @return
     */
    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders,HttpServletRequest request){

        log.info("订单数据：{}",orders);
        Boolean submit = ordersService.submit(orders, request);
        return submit ? R.success("下单成功") : R.error( "下单失败");
    }

    /**
     *
     * 用于管理端订单的分页查询
     *
     * @param page
     * @param pageSize
     * @param number 订单号
     * @param beginTime 支付时间开始时间
     * @param endTime 支付时间结束时间
     * @return
     */
    @GetMapping("/page")
    public R<Page> selectOrderDetail(Integer page,
                                     Integer pageSize,
                                     Long number,
                                     @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime beginTime,
                                     @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss") LocalDateTime endTime){
        Page<Orders> ordersPage = new Page<>(page, pageSize);

        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(number != null, Orders::getNumber,number);
        queryWrapper.between(beginTime != null || endTime != null, Orders::getCheckoutTime,beginTime,endTime);
        queryWrapper.orderByDesc(Orders::getCheckoutTime);
        Page<Orders> ordersList = ordersService.page(ordersPage, queryWrapper);


        return R.success(ordersList);
    }

    /**
     * 用于修改 订单状态 1待付款，2待派送，3已派送，4已完成，5已取消
     * @param orders
     * @return
     */
    @PutMapping
    public R<String> updateStatus(@RequestBody Orders orders){

        boolean update = ordersService.updateById(orders);

        return update ? R.success("状态修改成功") : R.error("状态修改失败") ;
    }

    /**
     * 用于移动端订单的的分页展示
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/userPage")
    public R<Page> selectOrder(Integer page,Integer pageSize){

        Page<Orders> ordersPage = new Page<>(page, pageSize);

        Page<Orders> pageInfo = ordersService.page(ordersPage);

        return R.success(pageInfo);
    }
}
