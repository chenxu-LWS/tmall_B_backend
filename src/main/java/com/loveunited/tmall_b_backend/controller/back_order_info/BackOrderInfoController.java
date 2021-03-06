package com.loveunited.tmall_b_backend.controller.back_order_info;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loveunited.tmall_b_backend.common.ReturnObject;
import com.loveunited.tmall_b_backend.common.ReturnPageObject;
import com.loveunited.tmall_b_backend.common.constants.ErrInfo;
import com.loveunited.tmall_b_backend.common.exception.BizException;
import com.loveunited.tmall_b_backend.controller.PageBaseDTO;
import com.loveunited.tmall_b_backend.service.back_order_info.BackOrderInfoService;
import com.loveunited.tmall_b_backend.service.back_order_info.dto.BackOrderInfoDTO;
import com.loveunited.tmall_b_backend.service.commodity.CommodityService;
import com.loveunited.tmall_b_backend.service.order_info.OrderInfoService;

/**
 * @author LiuWenshuo
 * Created on 2022-03-12
 */
@Controller
@RequestMapping("/api/backOrderInfo")
public class BackOrderInfoController {

    @Autowired
    BackOrderInfoService backOrderInfoService;
    @Autowired
    OrderInfoService orderInfoService;
    @Autowired
    CommodityService commodityService;

    @RequestMapping("/queryById")
    @ResponseBody
    public ReturnObject queryBackOrderInfoById(Integer id) {
        try {
            return new ReturnObject(true,
                    backOrderInfoService.queryBackOrderInfoById(id), 0);
        } catch (BizException e) {
            return new ReturnObject(e);
        }
    }

    @RequestMapping("/queryAllByPage")
    @ResponseBody
    public ReturnPageObject<BackOrderInfoDTO> queryAllByPage(@RequestBody PageBaseDTO dto) {
        if (dto.hasNull()) {
            return new ReturnPageObject<>(ErrInfo.PARAMETER_ERROR);
        }
        try {
            return new ReturnPageObject<>(true,
                    backOrderInfoService.queryAllByPage(dto.getPageNo(), dto.getPageSize()), 0);
        } catch (BizException e) {
            return new ReturnPageObject<>(e);
        }
    }

//    @RequestMapping("/insert")
//    @ResponseBody
//    public ReturnObject insertBackOrderInfo(@RequestBody InsertBackOrderInfoDTO dto) {
//        try {
//            // ????????????????????????????????????
//            orderInfoService.updateCommodityStatusInOrderInfo(dto.getOrderInfoId(),
//                    dto.getCommodityId(), 3);
//            // ?????????????????????
//            commodityService.increaseOrDecreaseInventory(dto.getCommodityId(), dto.getBackNum());
//            // ?????????????????????
//            final Integer result = backOrderInfoService.insertBackOrderInfo(
//                    dto.getOrderInfoId(), dto.getCommodityId(), dto.getBackNum(), dto.getPrice());
//            return new ReturnObject(true, result, 0);
//        } catch (BizException e) {
//            return new ReturnObject(e);
//        }
//    }
}
