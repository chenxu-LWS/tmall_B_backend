package com.loveunited.tmall_b_backend.controller.Commodity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loveunited.tmall_b_backend.common.ReturnObject;
import com.loveunited.tmall_b_backend.service.commodity.CommodityService;

/**
 * @author LiuWenshuo
 * Created on 2022-03-08
 */
@Controller
@RequestMapping("/api/commodity")
public class CommodityController {
    @Autowired
    CommodityService commodityService;

    @RequestMapping("/insert")
    @ResponseBody
    public ReturnObject insert() {
        return null;
    }

    @RequestMapping("/addProp")
    @ResponseBody
    public ReturnObject addProp() {
        return null;
    }

    @RequestMapping("/queryById")
    @ResponseBody
    public ReturnObject queryById() {
        return null;
    }

    @RequestMapping("/queryByBrandId")
    @ResponseBody
    public ReturnObject queryByBrandId() {
        return null;
    }

    @RequestMapping("/queryByCategoryId")
    @ResponseBody
    public ReturnObject queryByCategoryId() {
        return null;
    }
}
