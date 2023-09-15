package com.liu.boot.mapper;

import com.liu.boot.pojo.Employee;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.springframework.stereotype.Repository;

/**
* @author liududu
* @description 针对表【employee(员工信息)】的数据库操作Mapper
* @createDate 2023-09-03 15:39:43
* @Entity com.liu.boot.pojo.Employee
*/
@Repository
public interface EmployeeMapper extends BaseMapper<Employee> {

}




