package com.ccstay.czb.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("user")
public class User implements Serializable {
    @TableId
    private  String id;
    private String mobile;
    private String name;
    private Boolean isAuthentication;
    private  String idCard;
    private  String userName;
    private  String password;
    private  String code;
    private  String headIcon;
    private Date createTime;
    private Date updateTime;
    private Date lastLoginTime;
    private Date firstWorkDate;
    private  String createUser;
    private  String updateUser;
    private  String jobIntention;
    private  String department;
    private  String authenticationParam;
    private  String authenticationCallBack;
    private  Integer authenticationCount;
    private  String idCardFImage;
    private  String idCardZImage;
    private  String bankNo;
    private  String bank;
    private  String socialArea;
    private  String socialCity;
    private  String socialProvince;
    private  String source;
    private  String socialNumber;
    private  String industry;
    private  String major;
    private  String education;
    private  String liveAddress;
    private  String liveArea;
    private  String liveCity;
    private  String liveProvince;
    private  String registerAddress;
    private  String registerArea;
    private  String registerCity;
    private  String registerProvince;
    private  Integer gender;
    private  Integer status;
    private  Integer age;
    private  String workNumber;





}
