package com.liu.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.boot.pojo.User;
import com.liu.boot.service.UserService;
import com.liu.boot.mapper.UserMapper;
import org.springframework.stereotype.Service;

/**
* @author liududu
* @description 针对表【user(用户信息)】的数据库操作Service实现
* @createDate 2023-09-08 14:07:04
*/
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
    implements UserService{

}




