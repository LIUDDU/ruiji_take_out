package com.liu.boot.pojo;

import com.baomidou.mybatisplus.annotation.*;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 购物车
 * @TableName shopping_cart
 */
@TableName(value ="shopping_cart")
@Data
public class ShoppingCart implements Serializable {
    /**
     * 主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId
    private Long id;

    /**
     * 名称
     */
    private String name;

    /**
     * 图片
     */
    private String image;

    /**
     * 主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long userId;

    /**
     * 菜品id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long dishId;

    /**
     * 套餐id
     */
    @JsonSerialize(using = ToStringSerializer.class)
    private Long setmealId;

    /**
     * 口味
     */
    private String dishFlavor;

    /**
     * 数量
     */
    private Integer number;

    /**
     * 金额
     */
    private BigDecimal amount;

    /**
     * 创建时间
     */
    @JsonFormat(locale = "zh",timezone = "GMT+8",pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private LocalDateTime createTime;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}