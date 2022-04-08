package com.loveunited.tmall_b_backend.service.statistics;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loveunited.tmall_b_backend.service.category.CategoryService;
import com.loveunited.tmall_b_backend.service.category.dto.CategoryDTO;
import com.loveunited.tmall_b_backend.service.commodity.CommodityService;

/**
 * @author LiuWenshuo
 * Created on 2022-04-08
 */
@Service
public class StatisticsService {
    @Autowired
    CommodityService commodityService;
    @Autowired
    CategoryService categoryService;

    // 根据不同level分类聚合，给出每种分类的总销售额top10
    public List<CategoryDTO> getTop10Categories(Integer level) {
        return null;
    }

    // 筛选总销量top10的商品

    // 根据时间聚合，每日销量top10，每周销量top10，每月销量top10的商品
}
