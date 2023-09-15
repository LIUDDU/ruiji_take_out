package com.liu.boot.service;

import com.liu.boot.pojo.Category;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * @author liududu
 * @description 针对表【category(菜品及套餐分类)】的数据库操作Service
 * @createDate 2023-09-04 16:22:11
 */
public interface CategoryService extends IService<Category> {

    /**
     * 删除分类
     * @param id
     * @return
     */
    Boolean remove(Long id);
}
