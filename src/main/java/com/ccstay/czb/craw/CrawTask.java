package com.ccstay.czb.craw;


import com.alibaba.fastjson.JSONArray;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ccstay.czb.mapper.TaskMapper;
import com.ccstay.czb.pojo.Task;
import net.sf.json.JSONObject;
import org.jsoup.Jsoup;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


@Service
public class CrawTask extends ServiceImpl<TaskMapper, Task> implements CrawTaskService{

    @Autowired
    private CrawTask crawTask;

    public void crawTask() throws IOException {
        String text = Jsoup.connect("https://www.xiangyaowant.com/api/Task/GetNewTaskList?page=1&rows=10000&userId=218AD4CA623D43C49895CA07AE96E529").ignoreContentType(true).userAgent("Mozilla/5.0 (Windows; U; Windows NT 5.1; zh-CN; rv:1.9.2.15)").get().text();
        JSONObject jsonObject = JSONObject.fromObject(text);
        String data = jsonObject.getString("data");
        List<Task> list = new ArrayList<>();
        list = JSONArray.parseArray(data, Task.class);
        //System.err.println(list);
       crawTask.saveBatch(list);
    }

}
