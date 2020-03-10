package com.ccstay.czb.controller;

import com.alibaba.fastjson.JSONObject;
import com.ccstay.czb.config.IdWorker;
import com.ccstay.czb.config.PasswordEncoder;
import com.ccstay.czb.pojo.Reg;
import com.ccstay.czb.pojo.Result;
import com.ccstay.czb.pojo.StatusCode;
import com.ccstay.czb.pojo.User;
import com.ccstay.czb.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@RequestMapping("/api/User")
@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    @Autowired
    private IdWorker idWorker;
    @Autowired
    private PasswordEncoder passwordEncoder;

    /**
     * 注册短信
     *
     * @param idCard
     * @param mobile
     * @return
     */
    @PostMapping ("/SendSmsForRegist")
    public Result sms( @RequestBody User user) {
        if (userService.findByMobile(user.getMobile()) != null) {
            return new Result(System.currentTimeMillis(), 404, "该手机号已存在", null);
        }
        if (!userService.IsMobilePhone(user.getMobile())) {
            return new Result(System.currentTimeMillis(), StatusCode.ERROR, "手机号不正确", null);
        }
        userService.sendRegistSms(user.getMobile());

        return new Result(System.currentTimeMillis(), StatusCode.OK, "已发送", null);
    }

    /**
     * 注册
     *
     * @param idCard
     * @param mobile
     * @param userName
     * @param password
     * @param code
     * @return
     */
    @PostMapping("/Regist")
    public Result regist(@RequestBody Reg reg ) {
        if (userService.findByMobile(reg.getMobile()) != null) {
            return new Result(System.currentTimeMillis(), 404, "该手机号已注册请登录", null);
        }
        if (!userService.IsMobilePhone(reg.getMobile())) {
            return new Result(System.currentTimeMillis(), StatusCode.ERROR, "手机号不正确", null);
        }
        String checkcodeRedis = redisTemplate.opsForValue().get("sms.checkcode" + reg.getMobile());
        //判断验证码是否为空
        if (checkcodeRedis == null || checkcodeRedis.isEmpty()) {
            return new Result(System.currentTimeMillis(), StatusCode.ERROR, "请先获取手机验证码", null);
        }
        if (!checkcodeRedis.equals((reg.getCode()))) {
            return new Result(System.currentTimeMillis(), StatusCode.ERROR, "验证码不正确", null);
        }
        if (StringUtils.isEmpty(reg.getIdCard())) {
            return new Result(System.currentTimeMillis(), StatusCode.ERROR, "身份证不能为空", null);
        }
        if (StringUtils.isEmpty(reg.getUserName())) {
            return new Result(System.currentTimeMillis(), StatusCode.ERROR, "姓名不能为空", null);
        }
        if (StringUtils.isEmpty(reg.getPassword())) {
            return new Result(System.currentTimeMillis(), StatusCode.ERROR, "密码不能为空", null);
        }
        User user = new User();
        user.setMobile(reg.getMobile());
        user.setName(reg.getUserName());
        user.setSource("小程序注册");
        user.setCreateTime(new Date());
        user.setId(idWorker.nextId() + "");
        user.setIdCard(reg.getIdCard());
        user.setStatus(1);
        user.setIsAuthentication(false);
        user.setAuthenticationCount(0);
        user.setPassword(passwordEncoder.encode(reg.getPassword()));
        userService.saveUser(user);
        return new Result(System.currentTimeMillis(), StatusCode.OK, "注册成功", null);
    }

    /**
     * 登录
     *
     * @param code             不知道
     * @param mobile           手机
     * @param verificationCode 密码
     * @return
     */
    @PostMapping("/WeChatLogin")
    public Result weChatLogin(@RequestBody Reg reg) {
        if(StringUtils.isEmpty(reg.getMobile())||StringUtils.isEmpty(reg.getVerificationCode())){
            return new Result(System.currentTimeMillis(), 405, "账号或密码错误", null);
        }
        if (userService.findByMobile(reg.getMobile()) == null) {
            return new Result(System.currentTimeMillis(), 413, "用户不存在", null);
        }

       JSONObject jsonObject =userService.login(reg.getMobile(),reg.getVerificationCode());
        //  {"status":405,"message":"账号或密码错误","server_time":1583769592,"data":{}}
        // {"status":200,"message":"成功","server_time":1583769620,"data":{"userId":"218AD4CA623D43C49895CA07AE96E529","mobile":"15905533321"}}
        if(null!=jsonObject){
            //登录成功
            //签发token
//            String token = jwtUtil.createJWT(user.getId(), user.getNickname(), "user");
//            //构建Map，用来封装token（最终转json）
//            Map map=new HashMap();
//            map.put("token",token);
//            //用户昵称
//            map.put("name",user.getNickname());
//            //用户头像
//            map.put("avatar",user.getAvatar());
            return new Result(System.currentTimeMillis(),StatusCode.OK,"登录成功",jsonObject);
        }else{
            //登录失败
            return new Result(System.currentTimeMillis(), 407, "账号或密码错误", null);
        }
    }

    /**
     * 查询用户信息
     *
     * @param id
     * @return
     */
    @GetMapping("/GetUser")
    public Result getUser(@RequestParam String id) {

        return new Result(System.currentTimeMillis(), StatusCode.OK, "成功", userService.findById(id));
    }

    @GetMapping("/GetPersonalInfo")
    public Result GetPersonalInfo(@RequestParam String id) {

        return new Result(System.currentTimeMillis(), StatusCode.OK, "成功", userService.findInfo(id));
    }


}
