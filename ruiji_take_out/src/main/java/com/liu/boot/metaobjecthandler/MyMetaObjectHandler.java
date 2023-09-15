package com.liu.boot.metaobjecthandler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.time.LocalDateTime;

/**
 * @Author
 * @Date 2023/9/4 22:17
 * @Description 自定义元数据处理
 *
 * 处理公共字段填充
 *
 */
@Component
@Slf4j
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Resource
    private HttpServletRequest request;

    /**
     * 执行添加方法是自动添加公共字段
     * @param metaObject
     */
    @Override
    public void insertFill(MetaObject metaObject) {
        log.info("公共字段自动填充【Insert】");
        log.info(metaObject.toString());
        Long empId = (Long) request.getSession().getAttribute("employee");
        log.info("empId:{}",empId);

        Long userId = (Long) request.getSession().getAttribute("user");
        log.info("userId:{}",userId);
        if (empId != null) {
            //创建时间
            metaObject.setValue("createTime", LocalDateTime.now());
            //更新时间
            metaObject.setValue("updateTime", LocalDateTime.now());
            //创建人id
            metaObject.setValue("createUser", empId);
            //更新人id
            metaObject.setValue("updateUser", empId);
        }
        if (userId != null){
            //创建时间
            metaObject.setValue("createTime", LocalDateTime.now());
            //更新时间
            metaObject.setValue("updateTime", LocalDateTime.now());
            //创建人id
            metaObject.setValue("createUser", userId);
            //更新人id
            metaObject.setValue("updateUser", userId);
        }
    }

    /**
     * 执行更新方法时自动填充公共字段
     * @param metaObject
     */
    @Override
    public void updateFill(MetaObject metaObject) {
        log.info("公共字段自动填充【update】");
        log.info(metaObject.toString());
        Long empId = (Long) request.getSession().getAttribute("employee");
        log.info("empId:{}",empId);

        Long userId = (Long) request.getSession().getAttribute("user");
        log.info("userId:{}",userId);
        if (empId != null) {
            //更新时间
            metaObject.setValue("updateTime", LocalDateTime.now());
            //更新人id
            metaObject.setValue("updateUser", empId);
        }

        if (userId != null){
            //更新时间
            metaObject.setValue("updateTime", LocalDateTime.now());
            //更新人id
            metaObject.setValue("updateUser", userId);
        }
    }
}
