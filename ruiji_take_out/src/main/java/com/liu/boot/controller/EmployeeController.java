package com.liu.boot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liu.boot.pojo.Employee;
import com.liu.boot.utils.R;
import com.liu.boot.service.EmployeeService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.time.LocalDateTime;


/**
 * @Author
 * @Date 2023/9/3 15:52
 * @Description 员工控制类
 */
@Slf4j
@RestController
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * 员工登录
     *
     * @param request
     * @param employee
     * @return
     */
    @RequestMapping("/login")
    public R<Employee> login(HttpServletRequest request, @RequestBody Employee employee) {

        //将提交的密码进行MD5加密
        String password = employee.getPassword();
        password = DigestUtils.md5DigestAsHex(password.getBytes());
        //根据用户名查询数据库
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(Employee::getUsername, employee.getUsername());
        Employee emp = employeeService.getOne(queryWrapper);

        //若没有查询到用户，则返回登陆失败
        if (emp == null) {
            return R.error("用户名为空，登录失败");
        }

        //若账号密码比对不正确，则返回登录失败
        if (!password.equals(emp.getPassword())) {
            return R.error("账号或密码不正确，登陆失败");
        }

        //查看状态是否是可用状态,0:为禁用状态， 1:为正常状态
        if(emp.getStatus() == 0){
            return R.error("账号以禁用");
        }

        //将登陆成功的用户保存到session中
        HttpSession session = request.getSession();
        session.setAttribute("employee",emp.getId());

        return R.success(emp);
    }

    /**
     * 员工退出登录
     * @param request
     * @return
     */
    @PostMapping("/logout")
    public R<String> logout( HttpServletRequest request){

        HttpSession session = request.getSession();

        session.removeAttribute("employee");
        return R.success("退出登录成功");
    }

    /**
     * 新增员工信息
     * @param request
     * @param employee
     * @return
     */
    @PostMapping
    public R<String> inertEmployee(HttpServletRequest request, @RequestBody Employee employee){

        log.info("新增员工信息：{}",employee);
        //增添默认密码123456,加密保存
        String password = DigestUtils.md5DigestAsHex("123456".getBytes());
        employee.setPassword(password);

//        //增加创建时间
//        employee.setCreateTime(LocalDateTime.now());
//        //增加更新时间
//        employee.setUpdateTime(LocalDateTime.now());
//
//        //增加创建人
//        Long userId = (Long)request.getSession().getAttribute("employee");
//        employee.setCreateUser(userId);
//        //增加更新人
//        employee.setUpdateUser(userId);

        boolean save = employeeService.save(employee);

        log.info(save ? "添加成功" : "添加失败");

        return R.success(save ? "添加成功" : "添加失败");
    }

    /**
     * 分页查询 员工信息
     * @param page
     * @param pageSize
     * @param name
     * @return
     */
    @GetMapping("/page")
    public R<Page<Employee>> selectEmployeeList(Integer page,Integer pageSize,String name){

        log.info("page:{},pageSize:{},name:{}",page,pageSize,name);
        //分页数据
        Page<Employee> pageInfo = new Page<>(page,pageSize);
        //构建条件
        LambdaQueryWrapper<Employee> queryWrapper = new LambdaQueryWrapper<>();
        //有就拼接name，没有就不拼接
        queryWrapper.like(StringUtils.isNotEmpty(name), Employee::getName,name);
        //增加排序
        queryWrapper.orderByDesc(Employee::getCreateTime);
        //执行查询
        Page<Employee> empPage = employeeService.page(pageInfo,queryWrapper);

        log.info(empPage.toString());
        return R.success(empPage);
    }


    /**
     * 修改用户信息，改变员工状态
     * @param request
     * @param employee
     * @return
     */
    @PutMapping
    public R<String> update(HttpServletRequest request,@RequestBody Employee employee){

        log.info("要改变状态的账号id:{},状态:{}",employee.getId(),employee.getStatus());

//        //设置更新的时间
//        employee.setUpdateTime(LocalDateTime.now());
//        //设置更新人id
//        Long updateUser = (Long) request.getSession().getAttribute("employee");
//        employee.setUpdateUser(updateUser);

        //执行sql
        boolean b = employeeService.updateById(employee);

        return b ? R.success("状态更新成功") : R.error("状态更新失败");
    }

    /**
     * 根据员工id查询用户信息
     * @return
     */
    @GetMapping("/{id}")
    public R<Employee> selectEmployeeById(@PathVariable("id") Long id){

        Employee emp = employeeService.getById(id);

        if (emp == null){
            return R.error("该用户不存在");
        }

        return R.success(emp);
    }


}
