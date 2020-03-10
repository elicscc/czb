package com.ccstay.czb.service.impl;

import com.ccstay.czb.mapper.BannerMapper;
import com.ccstay.czb.pojo.Banner;
import com.ccstay.czb.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private BannerMapper bannerMapper;
    @Override
    public List<Banner> findAll() {
        return bannerMapper.selectList(null);
    }
}
