package com.ccstay.czb.service.impl;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.aliyuncs.exceptions.ClientException;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ccstay.czb.config.PasswordEncoder;
import com.ccstay.czb.config.SmsUtil;
import com.ccstay.czb.mapper.UserMapper;
import com.ccstay.czb.pojo.User;
import com.ccstay.czb.pojo.UserDetail;
import com.ccstay.czb.service.UserService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.regex.Pattern;


@Service
public class UserServiceImpl implements UserService {
@Autowired
private UserMapper userMapper;
@Autowired
private PasswordEncoder passwordEncoder;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    @Value("${aliyun.sms.template_code}")
    private  String template_code;

    @Value("${aliyun.sms.sign_name}")
    private  String sign_name ;

    @Override
    public UserDetail findById(String id) {
        User user = userMapper.selectById(id);
        UserDetail userDetail=new UserDetail();
        userDetail.setId( user.getId());
        userDetail.setHeadIcon(user.getHeadIcon());
        userDetail.setIsAuthentication(user.getIsAuthentication());
        userDetail.setMobile(user.getMobile());
        userDetail.setName(user.getName());
        List<JSONObject> list=new ArrayList<>();
        JSONObject j=new JSONObject();
        j.put("id","085070FBB32B4F779F22D8D0E621261A");   j.put("name","小象众包平台");
        list.add(j);
        userDetail.setServiceProviders(list);
        return userDetail;


    }

    @Override
    public User findInfo(String id) {
        return userMapper.selectById(id);
    }

    @Override
    public User findByMobile(String mobile) {
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        return userMapper.selectOne(wrapper);
    }

    @Override
    public boolean IsMobilePhone(String mobile) {
        String reg = "^(((13[0-9])|(15([0-3]|[5-9]))|(18[0,5-9]))\\d{8})|(0\\d{2}-\\d{8})|(0\\d{3}-\\d{7})$";
        return Pattern.matches(reg, mobile);
    }
    @Autowired
    private SmsUtil smsUtil;
    @Override
    public void sendRegistSms(String mobile) {
        String checkcode = RandomStringUtils.randomNumeric(6);

        redisTemplate.opsForValue().set("sms.checkcode" + mobile, checkcode, 5, TimeUnit.MINUTES);
       // System.err.println(checkcode);
        try {
            smsUtil.sendSms(mobile, sign_name, template_code, "{\"code\":" + checkcode + "}");
        }catch (
                ClientException e){
            e.printStackTrace();
        }

    }

    @Override
    public void saveUser(User user) {
    userMapper.insert(user);
//用户注册成功后，清除redis中的验证码
        redisTemplate.delete("sms.checkcode" + user.getMobile());
    }

    @Override
    public JSONObject login(String mobile, String password) {
        QueryWrapper<User> wrapper=new QueryWrapper<>();
        wrapper.eq("mobile",mobile);
        User user=userMapper.selectOne(wrapper);
        if (user!=null){
            if (passwordEncoder.matches(password,user.getPassword())){
                JSONObject jsonObject=new JSONObject();
                jsonObject.put("userId",user.getId());
                jsonObject.put("mobile",user.getMobile());
                return jsonObject;
            }
        }

        return null;
    }
}
