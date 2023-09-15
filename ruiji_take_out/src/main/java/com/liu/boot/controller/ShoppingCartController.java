package com.liu.boot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liu.boot.pojo.ShoppingCart;
import com.liu.boot.service.ShoppingCartService;
import com.liu.boot.utils.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author
 * @Date 2023/9/8 17:58
 * @Description 购物车控制类
 */
@RestController
@Slf4j
@RequestMapping("/shoppingCart")
public class ShoppingCartController {

    @Autowired
    private ShoppingCartService shoppingCartService;



    /**
     * 添加购物车功能
     * @param shoppingCart
     * @return
     */
    @PostMapping("/add")
    public R<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart, HttpServletRequest request){
        log.info("shoppingCart:{}",shoppingCart);

        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("user");
        //设置userId
        shoppingCart.setUserId(userId);
        //检查购物车内菜品和套餐是否存在，若存在，只需要将数量number+1,若不存在，默认数量为1
        Long dishId = shoppingCart.getDishId();
        //判断加入购物车的是菜品还是套餐，dishId不为空就是添加的菜品，否则为套餐
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        //封装条件
        //根据userId
        queryWrapper.eq(ShoppingCart::getUserId,userId);
        if (dishId != null){
            //添加的为菜品
            //根据菜品id
            queryWrapper.eq(ShoppingCart::getDishId,dishId);

        } else {
            //添加的为套餐
            queryWrapper.eq(ShoppingCart::getSetmealId,shoppingCart.getSetmealId());
        }

        ShoppingCart cartServiceOne = shoppingCartService.getOne(queryWrapper);

        if (cartServiceOne != null){
            //存在，在原来的基础上number + 1
            cartServiceOne.setNumber(cartServiceOne.getNumber() + 1);
            shoppingCartService.updateById(cartServiceOne);
        } else {
            //不存在，将number设置为1，添加到数据库中
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());
            shoppingCartService.save(shoppingCart);

            cartServiceOne = shoppingCart;
        }

        return R.success(cartServiceOne);
    }

    /**
     * 用于展示移动端页面
     *
     * 查看购物车
     *
     * @param request
     * @return
     */
    @GetMapping("/list")
    public R<List<ShoppingCart>> selectList(HttpServletRequest request){

        log.info("查看购物车");

        //获取userid
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("user");


        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,userId);
        queryWrapper.orderByDesc(ShoppingCart::getCreateTime);
        List<ShoppingCart> list = shoppingCartService.list(queryWrapper);
        return R.success(list);
    }

    /**
     * 清空购物车
     * @param request
     * @return
     */
    @DeleteMapping("/clean")
    public R<String> clean(HttpServletRequest request){

        log.info("清空购物车");
        //获取userid
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("user");

        //根据userid清除购物车
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId,userId);

        boolean b = shoppingCartService.remove(queryWrapper);
        return b ? R.success("清空购物车完成") : R.error("清空购物车失败");
    }

    /**
     * 修改 减少购物车内菜品数量
     * @param shoppingCart
     * @return
     */
    @PostMapping("/sub")
    public R<String> sub(@RequestBody ShoppingCart shoppingCart,HttpServletRequest request){

        log.info("要减少的套餐id：{}，菜品id:{}",shoppingCart.getSetmealId(),shoppingCart.getDishId());

        //获取用户id
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("user");

        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();

        queryWrapper.eq(ShoppingCart::getUserId,userId);

        //判断是套餐还是，菜品
        if (shoppingCart.getSetmealId() != null){
            //是套餐
            queryWrapper.eq(ShoppingCart::getSetmealId,shoppingCart.getSetmealId());
        } else {
            //是菜品
            queryWrapper.eq(ShoppingCart::getDishId,shoppingCart.getDishId());
        }

        //判断购物车中还存在几份， 若大于一份则数量number - 1,
        ShoppingCart cartServiceOne = shoppingCartService.getOne(queryWrapper);
        Integer number = cartServiceOne.getNumber();
        if (number > 1){
            cartServiceOne.setNumber(number - 1);
            shoppingCartService.updateById(cartServiceOne);
        }
        //若只有一份则删除该菜品或套餐
        if (number <= 1){
            shoppingCartService.remove(queryWrapper);
        }
        return R.success("减少成功");
    }
}
