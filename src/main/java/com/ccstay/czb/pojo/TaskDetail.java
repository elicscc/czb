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

public class TaskDetail implements Serializable {

   // private   String  id;
    private   String creator;
    private   String  iconText;
  //  private   boolean isFull;
    private   String  name;
   private   String  peopleNum;
    private   String   taskPrice;
    private   String   timeText;
    private   String  welfare;
    private   String  workAddress;
    private   boolean isSigned;
    private   boolean isFavorite;
    private  String firstLevel;
    private String signStr;
}
