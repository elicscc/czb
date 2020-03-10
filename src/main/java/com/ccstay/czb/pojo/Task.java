package com.ccstay.czb.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@TableName("task")
public class Task implements Serializable {
    @TableId
    private   String  id;
    private   String creator;
    private   String  iconText;
    private   Boolean isFull;
    private   String  name;
    private   String  peopleNum;
    private   String   taskPrice;
    private   String   timeText;
    private   String  welfare;
    private   String  workAddress;
}
