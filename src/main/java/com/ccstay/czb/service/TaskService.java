package com.ccstay.czb.service;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ccstay.czb.pojo.Task;




public interface TaskService {
    Page<Task> selectPage(Integer page,Integer rows);

    JSONObject selectDetail(String id);


    IPage<Task> selectByAll(int page, int rows, String sidx, String sord, String userId, String taskType, String address);
}
