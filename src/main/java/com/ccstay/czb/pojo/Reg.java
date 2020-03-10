package com.ccstay.czb.pojo;

import lombok.Data;


import java.io.Serializable;

@Data
public class Reg implements Serializable {

    private String idCard;
    private String mobile;
    private String userName;
    private String password;
    private String code;
    private String  verificationCode;
}
