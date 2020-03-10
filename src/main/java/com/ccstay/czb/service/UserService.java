package com.ccstay.czb.service;


import com.alibaba.fastjson.JSONObject;
import com.ccstay.czb.pojo.User;
import com.ccstay.czb.pojo.UserDetail;

public interface UserService {
   UserDetail findById(String id);
   User findInfo(String id);

    User findByMobile(String mobile);

    boolean IsMobilePhone(String mobile);

    void sendRegistSms(String mobile);

    void saveUser(User user);

    JSONObject login(String mobile, String password);
}
