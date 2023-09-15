package com.liu.boot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.liu.boot.pojo.Category;
import com.liu.boot.utils.R;
import com.liu.boot.service.CategoryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;
import java.util.List;

/**
 * @Author
 * @Date 2023/9/4 16:23
 * @Description 分类控制类
 */
@Slf4j
@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    /**
     * 用于分页查询
     * @param page
     * @param pageSize
     * @return
     */
    @GetMapping("/page")
    public R<Page<Category>> selectCategory(@RequestParam("page") Integer page,
                                            @RequestParam("pageSize") Integer pageSize){

        log.info("/category/page------GetMapping------请求了");
        //分页数据
        Page<Category> pageInfo = new Page<>(page,pageSize);
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.orderByAsc(Category::getSort);
        //执行sql
        Page<Category> categoryPage = categoryService.page(pageInfo,queryWrapper);

        if (categoryPage == null){
            return R.error("没有数据");
        }
        return R.success(categoryPage);
    }

    /**
     * 用于增加分类
     * @param request
     * @param category
     * @return
     */
    @PostMapping
    public R<String> insertDishCategory(HttpServletRequest request, @RequestBody Category category){

        log.info("/category------PostMapping------请求了");
        log.info("category:{}",category);
        //id=null, type=1, name=主食, sort=12, createTime=null,
        // updateTime=null, createUser=null, updateUser=null
        //补充余下的属性
//        category.setCreateTime(LocalDateTime.now());
//        category.setUpdateTime(LocalDateTime.now());
//
//        Long empId = (Long)request.getSession().getAttribute("employee");
//        category.setCreateUser(empId);
//        category.setUpdateUser(empId);

        //执行sql
        boolean save = categoryService.save(category);

        return save ? R.success("增加成功") : R.error("增加失败");
    }

    /**
     * 用于修改分类
     * @param category
     * @return
     */
    @PutMapping
    public R<String> update(HttpServletRequest request,@RequestBody Category category){

        log.info("category:{}",category);
        //id=1397844303408574465, type=null, name=川菜, sort=2,
        // createTime=null, updateTime=null, createUser=null, updateUser=null
//        Long empId = (Long)request.getSession().getAttribute("employee");
//        category.setUpdateTime(LocalDateTime.now());
//        category.setUpdateUser(empId);
        boolean b = categoryService.updateById(category);
        return b ? R.success("修改成功") : R.error("修改失败");
    }

    /**
     * 用于删除分类 通过id
     * @param id
     * @return
     */
    @DeleteMapping
    public R<String> deleteCategoryById(@RequestParam("ids") Long id){

        log.info("要删除的分类id:{}",id);
//        boolean b = categoryService.removeById(id);
//
//        log.info( b ? "删除成功" : "删除失败" );
//        return b ? R.success("删除成功") : R.error("删除失败");

        //删除分类
        Boolean remove = categoryService.remove(id);

        return remove ? R.success("分类信息删除成功") : R.error("分类信息删除失败");
    }

    /**
     * 用于查询分类列表
     * @param category
     * @return
     */
    @GetMapping("/list")
    public R<List<Category>> selectCategoryList(Category category){

        //组装条件
        LambdaQueryWrapper<Category> queryWrapper = new LambdaQueryWrapper<>();
        //排序条件
        queryWrapper.orderByAsc(Category::getUpdateTime);
        queryWrapper.eq(category.getType()!= null,Category::getType,category.getType());

        List<Category> list = categoryService.list(queryWrapper);
        return R.success(list);
    }
}
