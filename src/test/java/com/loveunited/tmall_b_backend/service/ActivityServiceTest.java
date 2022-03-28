package com.loveunited.tmall_b_backend.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.loveunited.tmall_b_backend.service.activity.ActivityService;

/**
 * @author LiuWenshuo
 * Created on 2022-03-27
 */
@SpringBootTest
public class ActivityServiceTest {

    @Autowired
    ActivityService service;

    @Test
    public void test() {
        service.insertActivity("111", "Coupon 20 [商品ID]==14 || [品类ID]==5 && [品牌ID]==4 || [品类ID]==5 Start 2021-03-27-00:00:00 End 2021-03-27-00:00:00");
    }
}
