package com.liu.boot.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.IdWorker;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.boot.exception.CustomServiceException;
import com.liu.boot.pojo.*;
import com.liu.boot.service.*;
import com.liu.boot.mapper.OrdersMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

/**
* @author liududu
* @description 针对表【orders(订单表)】的数据库操作Service实现
* @createDate 2023-09-09 14:58:04
*/
@Service
public class OrdersServiceImpl extends ServiceImpl<OrdersMapper, Orders>
    implements OrdersService{

    @Autowired
    private ShoppingCartService shoppingCartService;

    @Autowired
    private UserService userService;

    @Autowired
    private AddressBookService addressBookService;

    @Autowired
    private OrderDetailService orderDetailService;
    /**
     * 用户下单
     *
     * 操作多张表加入事务
     *
     * @param orders
     */
    @Transactional
    @Override
    public Boolean submit(Orders orders,HttpServletRequest request) {
        //1.获得用户id,userId
        Long userId = (Long) request.getSession().getAttribute("user");
        //2.通过用户id查询购物车信息
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,userId);
        List<ShoppingCart> shoppingCarts = shoppingCartService.list(queryWrapper);
        //判断购物车是否为空
        if (shoppingCarts == null && shoppingCarts.size() == 0){
            throw new CustomServiceException("购物车为空，不能下单");
        }
        //查询用户信息
        User user = userService.getById(userId);
        //查询地址信息
        AddressBook addressBook = addressBookService.getById(orders.getAddressBookId());
        //判断地址为空抛异常
        if (addressBook == null){
            throw new CustomServiceException("地址为空，无法下单");
        }

        //订单号
        long orderId = IdWorker.getId();
        //用于总金额的计算，原子操作，可以在多线程，高并发时保证计算的正确性
        AtomicInteger amount = new AtomicInteger(0);

        //为订单明细表赋值
        List<OrderDetail> orderDetails = shoppingCarts.stream().map((item) -> {
            OrderDetail orderDetail = new OrderDetail();
            orderDetail.setName(item.getName());//菜品或套餐名称
            orderDetail.setOrderId(orderId);//订单号
            orderDetail.setDishId(item.getDishId());//菜品id
            orderDetail.setSetmealId(item.getSetmealId());//套餐id
            orderDetail.setDishFlavor(item.getDishFlavor());//口味
            orderDetail.setNumber(item.getNumber());//数量
            orderDetail.setImage(item.getImage());//图片
            orderDetail.setAmount(item.getAmount());//菜品或套餐金额
            //总金额
            amount.addAndGet(item.getAmount().multiply(new BigDecimal(item.getNumber())).intValue());
            return orderDetail;
        }).collect(Collectors.toList());
        //为订单表赋值
        orders.setId(orderId);//主键id
        orders.setOrderTime(LocalDateTime.now());//下单时间
        orders.setCheckoutTime(LocalDateTime.now());//结账时间，这里没有开发支付功能，用系统当前时间
        orders.setStatus(2);//订单状态 1待付款，2待派送，3已派送，4已完成，5已取消
        orders.setAmount(new BigDecimal(amount.get()));//总金额
        orders.setUserId(userId);//下单用户id
        orders.setNumber(String.valueOf(orderId));//订单号
        orders.setUserName(user.getName());//用户名
        orders.setConsignee(addressBook.getConsignee());//收货人
        orders.setPhone(addressBook.getPhone());//手机号
        //收货地址
        orders.setAddress((addressBook.getProvinceName() == null ? "" : addressBook.getProvinceName())
                + (addressBook.getCityName() == null ? "" : addressBook.getCityName())
                + (addressBook.getDistrictName() == null ? "" : addressBook.getDistrictName())
                + (addressBook.getDetail() == null ? "" : addressBook.getDetail()));
        //3.向订单表插入数据，一条数据
        boolean save = this.save(orders);
        //4.想订单明细表中插入数据
        boolean saveBatch = orderDetailService.saveBatch(orderDetails);
        //5.清空购物车信息
        boolean remove = shoppingCartService.remove(queryWrapper);

        return save && saveBatch && remove;
    }
}




