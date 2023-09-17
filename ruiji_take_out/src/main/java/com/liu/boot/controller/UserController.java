package com.liu.boot.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.liu.boot.pojo.User;
import com.liu.boot.pojo.dto.UserDTO;
import com.liu.boot.service.UserService;
import com.liu.boot.utils.R;
import com.liu.boot.utils.SMSUtils;
import com.liu.boot.utils.ValidateCodeUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @Author
 * @Date 2023/9/8 14:29
 * @Description
 */
@RestController
@Slf4j
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate redisTemplate;
    /**
     * 用于发送短信
     *
     *  这里是模拟，没有完成使用阿里云短信服务发送短信
     *
     * @param user
     * @param request
     * @return
     */
    @PostMapping("/sendMsg")
    public R<String> sendMsg(@RequestBody User user, HttpServletRequest request){
        log.info("phone:{}",user.getPhone());
        //1.获取手机号码
        String phone = user.getPhone();

        if (StringUtils.isNotEmpty(phone)) {
            //2.生成随机验证码
            String code = ValidateCodeUtils.generateValidateCode(4).toString();

            log.info("随机验证码为：{}", code);

            //3.使用阿里云的短信服务发送验证码
            //这是使用阿里云发送短信服务,这里就不做了
            //SMSUtils.sendMessage();

//            //4.将生成的短信验证码放入session中
//            HttpSession session = request.getSession();
//            session.setAttribute(phone, code);

            //优化，验证码放入redis中保存,并且有效时间为5min,登录成功后删除验证码
            ValueOperations valueOperations = redisTemplate.opsForValue();
            //保存在redis中，有效期为5min
            valueOperations.set(phone,code,5, TimeUnit.MINUTES);

            return R.success("短信发送成功，验证码：" + code);
        }
        return R.error("短信发送失败");
    }


    /**
     * 用户登录 移动端
     * @param userDTO
     * @param request
     * @return
     */
    @PostMapping("/login")
    public R<UserDTO> login(@RequestBody UserDTO userDTO, HttpServletRequest request){

        log.info("userDTO；{}",userDTO);

        //判断对应的短信验证码是否正确
        String phone = userDTO.getPhone();
        String code = userDTO.getCode().toString();

        HttpSession session = request.getSession();
//        //取出在session中的短信验证码
//        Object sessionCode = session.getAttribute(phone);

        //优化，取出在redis中缓存的验证码比对
        ValueOperations valueOperations = redisTemplate.opsForValue();
        String loginCode = (String) valueOperations.get(phone);

        //比对
        if (loginCode != null && loginCode.equals(code)){
            log.info("验证码正确");
            LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(User::getPhone,userDTO.getPhone());
            User user = userService.getOne(queryWrapper);
            if (user == null){
                //判断用户的手机号是否在user中，若不在则为新用户，
                //将用户存到user表中
                userDTO.setStatus(1);
                userService.save(userDTO);
            }
            //将登陆成功的用户放入session中
            session.setAttribute("user",user.getId());

            //比对成功,则表示登录成功,删除缓存的验证码
            redisTemplate.delete(phone);
            return R.success(userDTO);
        }

        return R.error("登录失败");
    }

    /**
     * 员工退出登录
     * @param request
     * @return
     */
    @PostMapping("/loginout")
    public R<String> loginOut( HttpServletRequest request){

        HttpSession session = request.getSession();

        session.removeAttribute("user");
        return R.success("退出登录成功");
    }


}
