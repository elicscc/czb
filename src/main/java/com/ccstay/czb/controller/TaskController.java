package com.ccstay.czb.controller;


import com.alibaba.fastjson.JSONObject;

import com.ccstay.czb.pojo.Result;
import com.ccstay.czb.pojo.StatusCode;

import com.ccstay.czb.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;


@CrossOrigin
@RequestMapping("/api/Task")
@RestController
public class TaskController {

@Autowired
private TaskService taskService;

    @GetMapping("/GetNewTaskList")
    public Result findAll(@RequestParam("page") int page, @RequestParam("rows") int rows,@RequestParam("userId") String userId) {


        return new Result(System.currentTimeMillis(), StatusCode.OK, "成功", taskService.selectPage(page,rows).getRecords());
    }
    @GetMapping("/GetTaskList")
    public Result getTaskList(@RequestParam("page") int page, @RequestParam("rows") int rows,@RequestParam("sidx") String sidx,@RequestParam("sord") String sord,@RequestParam("userId") String userId
            ,@RequestParam("taskType") String taskType,@RequestParam("address") String address
    ) {
        return new Result(System.currentTimeMillis(), StatusCode.OK, "成功", taskService.selectByAll(page,
        rows,
        sidx,
        sord,
        userId,
        taskType,
        address ).getRecords());
    }
     @GetMapping("/GetRecruitList")
    public Result getRecruitList(@RequestParam("page") int page, @RequestParam("rows") int rows,@RequestParam("sidx") String sidx,@RequestParam("sord") String sord,@RequestParam("userId") String userId
            ,@RequestParam("taskType") String taskType,@RequestParam("address") String address
    ) {
        return new Result(System.currentTimeMillis(), StatusCode.OK, "成功", taskService.selectByAll(page,
        rows,
        sidx,
        sord,
        userId,
        taskType,
        address ).getRecords());
    }

    /**
     * 任务管理模块
     * @param page
     * @param rows
     * @param userId
     * @param status 0是已报名 1是已接单 2是已完成
     * @return
     */
 @GetMapping("/GetMyTaskList")
    public Result getMyTaskList(@RequestParam("page") int page, @RequestParam("rows") int rows,@RequestParam("userId") String userId
            ,@RequestParam("status") String status
    ) {
        return new Result(System.currentTimeMillis(), StatusCode.OK, "成功", null);
    }

 @GetMapping("/GetUnFinishedTaskCount")
    public Result taskCount(@RequestParam("userId") String userId) {
     JSONObject result = new JSONObject();
     result.put("count", 0);

        return new Result(System.currentTimeMillis(), StatusCode.OK, "成功",result );
    }

@GetMapping("/GetDetail")
    public Result getDetail(@RequestParam("userId") String userId,@RequestParam("id") String id) {


        return new Result(System.currentTimeMillis(), StatusCode.OK, "成功",taskService.selectDetail(id));
    }
@GetMapping("/GetTaskType")
    public Result getTaskType() {
    List<String> re=new ArrayList<>();
    re.add("服务外包");    re.add("服务分包");    re.add("岗位外包");    re.add("劳务派遣");    re.add("代理"); re.add("工作分包");

        return new Result(System.currentTimeMillis(), StatusCode.OK, "成功",re);
    }
@GetMapping("/GetTaskInfo")
    public Result getTaskInfo() {
    JSONObject result = new JSONObject();
    result.put("finishedNum", 0);result.put("signedNum", 0);result.put("takedNum", 0);

        return new Result(System.currentTimeMillis(), StatusCode.OK, "成功",result);
    }

@GetMapping("/GetRecruitType")
    public Result getRecruitType() {
    List<String> re=new ArrayList<>();
    re.add("正式工");    re.add("实习生");    re.add("长期兼职");    re.add("短期兼职");    re.add("日结工");

        return new Result(System.currentTimeMillis(), StatusCode.OK, "成功",re);
    }


}
