package com.liu.boot.pojo.dto;

import com.liu.boot.pojo.User;
import lombok.Data;

/**
 * @Author
 * @Date 2023/9/8 15:05
 * @Description
 */
@Data
public class UserDTO extends User {

    /**
     * 短信验证码
     */
    private Integer code;
}
