package com.loveunited.tmall_b_backend.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.loveunited.tmall_b_backend.service.order_info.OrderInfoService;

/**
 * @author LiuWenshuo
 * Created on 2022-03-12
 */
@SpringBootTest
public class OrderInfoServiceTest {
    @Autowired
    OrderInfoService orderInfoService;

    @Test
    public void testUpdateCommodityStatusInOrderInfo() {
        orderInfoService.updateCommodityStatusInOrderInfo(3, 6, 1);
    }
}
