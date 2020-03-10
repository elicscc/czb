package com.ccstay.czb.pojo;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDetail implements Serializable {
    @TableId
    private  String id;
    private String mobile;
    private String name;
    private Boolean isAuthentication;
    //private  String idCard;
    //private  String userName;
    //private  String password;
    private  String headIcon;
    private List<JSONObject> serviceProviders;


}
