package com.liu.boot;

import com.liu.boot.mapper.CategoryMapper;
import com.liu.boot.mapper.EmployeeMapper;
import com.liu.boot.pojo.Category;
import com.liu.boot.pojo.Employee;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.DigestUtils;

import java.time.LocalDateTime;

@SpringBootTest
class RuijiTakeOutApplicationTests {

    @Autowired
    private EmployeeMapper employeeMapper;

    @Autowired
    private CategoryMapper categoryMapper;

    @Test
    void contextLoads() {
    }

    /**
     * 批量插入员工信息
     */
    @Test
    public void insertEmp(){

        for (int i = 0; i < 10; i++) {
            Employee employee = new Employee();
            employee.setName("test" + i);
            employee.setUsername("test" + i);
            employee.setPassword(DigestUtils.md5DigestAsHex("123456".getBytes()));
            employee.setPhone("13111111111");
            employee.setSex("1");
            employee.setIdNumber("123456123456123456");
            employee.setCreateTime(LocalDateTime.now());
            employee.setUpdateTime(LocalDateTime.now());
            employee.setCreateUser(1l);
            employee.setUpdateUser(1l);
            employeeMapper.insert(employee);
        }

    }

    @Test
    public void insertCategory(){
        for (int i = 0; i < 10; i++) {
            Category category = new Category();
            category.setType(1);
            category.setName("徽菜" + i);
            category.setSort(i + 1);
            category.setCreateTime(LocalDateTime.now());
            category.setUpdateTime(LocalDateTime.now());
            category.setCreateUser(1l);
            category.setUpdateUser(1l);
            categoryMapper.insert(category);
        }
    }

}
