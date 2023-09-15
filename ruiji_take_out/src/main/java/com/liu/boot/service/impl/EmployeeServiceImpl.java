package com.liu.boot.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liu.boot.pojo.Employee;
import com.liu.boot.service.EmployeeService;
import com.liu.boot.mapper.EmployeeMapper;
import org.springframework.stereotype.Service;

/**
* @author liududu
* @description 针对表【employee(员工信息)】的数据库操作Service实现
* @createDate 2023-09-03 15:39:43
*/
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee>
    implements EmployeeService{

}




