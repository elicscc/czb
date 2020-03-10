package com.ccstay.czb.controller;


import com.ccstay.czb.pojo.Result;
import com.ccstay.czb.pojo.StatusCode;
import com.ccstay.czb.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@CrossOrigin
@RequestMapping("/api/Article")
@RestController
public class LabelController {

@Autowired
private ArticleService articleService;

    @GetMapping("GetBanner")
    public Result findAll() {


        return new Result(System.currentTimeMillis(), StatusCode.OK, "成功", articleService.findAll());
    }


}
