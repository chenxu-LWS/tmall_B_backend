package com.loveunited.tmall_b_backend.controller.statistics;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loveunited.tmall_b_backend.common.ReturnListObject;
import com.loveunited.tmall_b_backend.service.commodity.dto.CommodityDTO;
import com.loveunited.tmall_b_backend.service.statistics.StatisticsService;
import com.loveunited.tmall_b_backend.service.statistics.dto.CommoditySaleVolumeByBrandDTO;
import com.loveunited.tmall_b_backend.service.statistics.dto.CommoditySaleVolumeDTO;

/**
 * @author LiuWenshuo
 * Created on 2022-04-08
 */
@Controller
@RequestMapping("/api/statistics")
public class StatisticsController {
    @Autowired
    StatisticsService statisticsService;

    // 根据分类聚合，给出每种分类的总销售额topN
    @RequestMapping("/getTopNCategories")
    @ResponseBody
    public ReturnListObject getTopNCategories(Integer topN) {
        final List<CommoditySaleVolumeDTO> topNCategories = statisticsService.getTopNCategories(topN);
        return new ReturnListObject(true, new ArrayList<>(topNCategories), 0);
    }
    // 筛选总销量topN的商品
    @RequestMapping("/getTopNCommodities")
    @ResponseBody
    public ReturnListObject getTopNCommodities(Integer topN) {
        final List<CommodityDTO> topNCommodities = statisticsService.getTopNCommodities(topN);
        return new ReturnListObject(true, new ArrayList<>(topNCommodities), 0);
    }
    // 根据品牌聚合，给出每种品牌的总销售额topN
    @RequestMapping("/getTopNBrands")
    @ResponseBody
    public ReturnListObject getTopNBrands(Integer topN) {
        final List<CommoditySaleVolumeByBrandDTO> topNBrands = statisticsService.getTopNBrands(topN);
        return new ReturnListObject(true, new ArrayList<>(topNBrands), 0);
    }
}
