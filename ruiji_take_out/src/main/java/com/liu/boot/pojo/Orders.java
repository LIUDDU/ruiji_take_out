package com.liu.boot.pojo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * 订单表
 * @TableName orders
 */
@TableName(value ="orders")
@Data
public class Orders implements Serializable {


    /**
     * 主键
     */
    @JsonSerialize(using = ToStringSerializer.class)
    @TableId
    private Long id;

    //订单号
    private String number;

    //订单状态 1待付款，2待派送，3已派送，4已完成，5已取消
    private Integer status;


    //下单用户id
    private Long userId;

    //地址id
    private Long addressBookId;


    //下单时间
    @JsonFormat(locale = "zh", timezone = "GMT+8" ,pattern = "yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat
    private LocalDateTime orderTime;


    //结账时间
    private LocalDateTime checkoutTime;


    //支付方式 1微信，2支付宝
    private Integer payMethod;


    //实收金额
    private BigDecimal amount;

    //备注
    private String remark;

    //用户名
    private String userName;

    //手机号
    private String phone;

    //地址
    private String address;

    //收货人
    private String consignee;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}