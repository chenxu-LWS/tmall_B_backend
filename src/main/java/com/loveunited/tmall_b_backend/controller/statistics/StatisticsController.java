package com.loveunited.tmall_b_backend.controller.statistics;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.loveunited.tmall_b_backend.service.statistics.StatisticsService;

/**
 * @author LiuWenshuo
 * Created on 2022-04-08
 */
@Controller
@RequestMapping("/api/statistics")
public class StatisticsController {
    @Autowired
    StatisticsService statisticsService;

    // 根据不同level分类聚合，给出每种分类的总销售额top10

    // 筛选总销量top10的商品

    // 根据时间聚合，每日销量top10，每周销量top10，每月销量top10的商品

}
