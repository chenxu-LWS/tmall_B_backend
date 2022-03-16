package com.loveunited.tmall_b_backend.controller.commodity_picture;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loveunited.tmall_b_backend.common.ReturnListObject;
import com.loveunited.tmall_b_backend.common.exception.BizException;
import com.loveunited.tmall_b_backend.service.commodity_picture.CommodityPictureService;

/**
 * @author LiuWenshuo
 * Created on 2022-03-16
 */
@Controller
@RequestMapping("/api/commoditypic")
public class CommodityPicController {
    @Autowired
    CommodityPictureService pictureService;
    @RequestMapping("/queryMainPicByCommodityId")
    @ResponseBody
    public ReturnListObject queryMainPicByCommodityId (Integer commodityId) {
        try {
            final List<String> pictures = pictureService.queryMainPicByCommodityId(commodityId);
            return new ReturnListObject(true,
                    new ArrayList<>(pictures), 0);
        }catch (BizException e) {
            return new ReturnListObject(e);
        }
    }

    @RequestMapping("/queryDetailPicByCommodityId")
    @ResponseBody
    public ReturnListObject queryDetailPicByCommodityId (Integer commodityId) {
        try {
            final List<String> pictures = pictureService.queryDetailPicByCommodityId(commodityId);
            return new ReturnListObject(true,
                    new ArrayList<>(pictures), 0);
        }catch (BizException e) {
            return new ReturnListObject(e);
        }
    }
}
