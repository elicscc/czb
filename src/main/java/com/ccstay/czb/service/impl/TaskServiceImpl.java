package com.ccstay.czb.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ccstay.czb.mapper.TaskMapper;

import com.ccstay.czb.pojo.Task;
import com.ccstay.czb.pojo.TaskDetail;
import com.ccstay.czb.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@Service
public class TaskServiceImpl implements TaskService {
@Autowired
private  TaskMapper taskMapper;

    @Override
    public Page<Task> selectPage(Integer page, Integer rows) {

        Page<Task> pageResult=new Page<>(page,rows);
        return taskMapper.selectPage(pageResult,null);
    }

    @Override
    public JSONObject selectDetail(String id) {
        Task task = taskMapper.selectById(id);
        TaskDetail taskDetail=new TaskDetail();
        taskDetail.setCreator(task.getCreator());
        taskDetail.setIconText(task.getIconText());
        taskDetail.setName(task.getName());
        //taskDetail.setPeopleNum(task.getPeopleNum());
        taskDetail.setTaskPrice(task.getTaskPrice());
        taskDetail.setTimeText(task.getTimeText());
        taskDetail.setWelfare(task.getWelfare());
      // taskDetail.setWorkAddress(task.getWorkAddress());
        taskDetail.setSignStr("不限");
        taskDetail.setFavorite(false);
        taskDetail.setSigned(false);
        taskDetail.setFirstLevel("服务/招聘");
        List <JSONObject> info = new ArrayList<>();
        JSONObject p = new JSONObject();
        p.put("name","工作地点");
        p.put("value",task.getWorkAddress());
        info.add(p);
        p = new JSONObject();
        p.put("name","工作内容");
        p.put("value","无数据");
        info.add(p);
        p = new JSONObject();
        p.put("name","人数要求");
        p.put("value",task.getPeopleNum());
        info.add(p);
        p = new JSONObject();
        p.put("name","薪资福利");
        p.put("value",task.getWelfare());
        info.add(p);
        p = new JSONObject();
        p.put("name","其他");
        p.put("value","无数据");
        info.add(p);

//        info.put("工作内容","无数据");
//        info.put("人数要求",task.getPeopleNum());
//        info.put("薪资福利",task.getWelfare());
//        info.put("其他","无数据");
        JSONObject result = new JSONObject();
        result.put("task", taskDetail);
        result.put("info", info);
        return result;
    }

    @Override
    public IPage<Task> selectByAll(int page, int rows, String sidx, String sord, String userId, String taskType, String address) {
//        QueryWrapper<OauthOrganization> queryWrapper =  new QueryWrapper<>();
//        queryWrapper.orderByDesc("id");
        Page<Task> pageResult=new Page<>(page,rows);
        return taskMapper.selectPage(pageResult,null);
    }


}
