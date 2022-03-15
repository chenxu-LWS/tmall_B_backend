package com.loveunited.tmall_b_backend.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.loveunited.tmall_b_backend.service.commodity.CommodityService;

/**
 * @author LiuWenshuo
 * Created on 2022-03-09
 */
@SpringBootTest
public class CommodityServiceTest {
    @Autowired
    CommodityService commodityService;

    @Test
    public void testQueryByCategoryId() {
//        commodityService.queryByCategoryId(2);
    }

    @Test
    public void testQueryByBrandIdAndCategoryId() {
//        System.out.println(commodityService.queryByBrandIdAndCategoryIdByPage(3, 1));
    }
}
