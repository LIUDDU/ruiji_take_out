package com.liu.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.boot.pojo.AddressBook;
import com.liu.boot.service.AddressBookService;
import com.liu.boot.mapper.AddressBookMapper;
import org.springframework.stereotype.Service;

/**
* @author liududu
* @description 针对表【address_book(地址管理)】的数据库操作Service实现
* @createDate 2023-09-08 15:57:21
*/
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook>
    implements AddressBookService{

}




