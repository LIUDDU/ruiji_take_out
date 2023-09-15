package com.liu.boot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;

import com.liu.boot.pojo.AddressBook;
import com.liu.boot.service.AddressBookService;
import com.liu.boot.utils.R;
import com.sun.prism.impl.BaseContext;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * 地址簿管理
 */
@Slf4j
@RestController
@RequestMapping("/addressBook")
public class AddressBookController {

    @Autowired
    private AddressBookService addressBookService;

    /**
     * 新增
     */
    @PostMapping
    public R<AddressBook> save(@RequestBody AddressBook addressBook, HttpServletRequest request) {
        log.info("addressBook:{}",addressBook);
        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("user");
        addressBook.setUserId(userId);
        log.info("addressBook:{}", addressBook);
        addressBookService.save(addressBook);
        return R.success(addressBook);
    }

    /**
     * 设置默认地址
     */
    @PutMapping("default")
    public R<AddressBook> setDefault(@RequestBody AddressBook addressBook,HttpServletRequest request) {
        log.info("addressBook:{}", addressBook);

        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("user");

        LambdaUpdateWrapper<AddressBook> wrapper = new LambdaUpdateWrapper<>();
        wrapper.eq(AddressBook::getUserId, userId);
        wrapper.set(AddressBook::getIsDefault, 0);
        //SQL:update address_book set is_default = 0 where user_id = ?
        addressBookService.update(wrapper);

        addressBook.setIsDefault(1);
        //SQL:update address_book set is_default = 1 where id = ?
        addressBookService.updateById(addressBook);
        return R.success(addressBook);
    }

    /**
     * 根据id查询地址
     */
    @GetMapping("/{id}")
    public R get(@PathVariable Long id) {
        AddressBook addressBook = addressBookService.getById(id);
        if (addressBook != null) {
            return R.success(addressBook);
        } else {
            return R.error("没有找到该对象");
        }
    }

    /**
     * 查询默认地址
     */
    @GetMapping("default")
    public R<AddressBook> getDefault(HttpServletRequest request) {

        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("user");

        LambdaQueryWrapper<AddressBook> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AddressBook::getUserId, userId);
        queryWrapper.eq(AddressBook::getIsDefault, 1);

        //SQL:select * from address_book where user_id = ? and is_default = 1
        AddressBook addressBook = addressBookService.getOne(queryWrapper);

        if (null == addressBook) {
            return R.error("没有找到该对象");
        } else {
            return R.success(addressBook);
        }
    }

    /**
     * 查询指定用户的全部地址
     */
    @GetMapping("/list")
    public R<List<AddressBook>> list(AddressBook addressBook , HttpServletRequest request) {

        HttpSession session = request.getSession();
        Long userId = (Long) session.getAttribute("user");

        addressBook.setUserId(userId);
        log.info("addressBook:{}", addressBook);

        //条件构造器
        LambdaQueryWrapper<AddressBook> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(null != addressBook.getUserId(), AddressBook::getUserId, addressBook.getUserId());
        queryWrapper.orderByDesc(AddressBook::getUpdateTime);

        //SQL:select * from address_book where user_id = ? order by update_time desc
        return R.success(addressBookService.list(queryWrapper));
    }

    /**
     * 用于地址的修改
     * @param request
     * @param addressBook
     * @return
     */
    @PutMapping
    public R<String> update(HttpServletRequest request,@RequestBody AddressBook addressBook){

        log.info("id:{},userId:{}",addressBook.getId(),addressBook.getUserId());

        LambdaQueryWrapper<AddressBook> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(AddressBook::getUserId,addressBook.getUserId());
        queryWrapper.eq(AddressBook::getId,addressBook.getId());
        boolean update = addressBookService.update(addressBook,queryWrapper);

        return update ? R.success("地址修改成功") : R.error("地址修改失败");
    }

    /**
     * 用于地址删除
     * @param ids
     * @return
     */
    @DeleteMapping
    public R<String> delete(Long ids){

        log.info("要删除的地址id:{}",ids);

        boolean b = addressBookService.removeById(ids);
        return b ? R.success("地址删除成功") : R.error("地址删除失败");
    }
}
