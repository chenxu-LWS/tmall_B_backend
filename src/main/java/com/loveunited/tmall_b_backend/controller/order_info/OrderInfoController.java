package com.loveunited.tmall_b_backend.controller.order_info;

import java.util.ArrayList;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSON;
import com.loveunited.tmall_b_backend.common.ReturnListObject;
import com.loveunited.tmall_b_backend.common.ReturnObject;
import com.loveunited.tmall_b_backend.common.constants.ErrInfo;
import com.loveunited.tmall_b_backend.common.exception.BizException;
import com.loveunited.tmall_b_backend.controller.order_info.dto.InsertOrderInfoDTO;
import com.loveunited.tmall_b_backend.service.commodity.CommodityService;
import com.loveunited.tmall_b_backend.service.order_info.OrderInfoService;
import com.loveunited.tmall_b_backend.service.order_info.dto.OrderInfoDTO;

/**
 * @author LiuWenshuo
 * Created on 2022-03-11
 */
@Controller
@RequestMapping("/api/orderInfo")
public class OrderInfoController {
    @Autowired
    OrderInfoService orderInfoService;
    @Autowired
    CommodityService commodityService;

    @RequestMapping("/queryById")
    @ResponseBody
    public ReturnObject queryById(Integer id) {
        try {
            final OrderInfoDTO orderInfoDTO = orderInfoService.queryOrderInfoById(id);
            return new ReturnObject(true, orderInfoDTO, 0);
        } catch (BizException e) {
            return new ReturnObject(e);
        }
    }

    @RequestMapping("/queryByCustomerName")
    @ResponseBody
    public ReturnListObject queryOrderInfoByCustomerName(String customerName) {
        return new ReturnListObject(true,
                new ArrayList<>(orderInfoService.queryOrderInfoByCustomerName(customerName)), 0);
    }

    @RequestMapping("/insert")
    @ResponseBody
    public ReturnObject insertOrderInfo(@RequestBody InsertOrderInfoDTO dto) {
        if (dto == null || dto.getCustomerName() == null
                || dto.getDetail() == null || dto.getOrderPrice() == null) {
            return new ReturnObject(ErrInfo.PARAMETER_ERROR);
        }
        try {
            // 解析订单detail
            Map<String, Object> detailJSON = JSON.parseObject(dto.getDetail());
            detailJSON.forEach((k, v) -> {
                try {
                    Integer.parseInt(k);
                } catch (Exception e) {
                    throw new BizException(ErrInfo.ORDERINFO_DETAIL_FORMAT_ERROR);
                }
                Map<String, Object> objV = (Map<String, Object>) v;
                if(!objV.containsKey("number") || !objV.containsKey("price")) {
                    throw new BizException(ErrInfo.ORDERINFO_DETAIL_FORMAT_ERROR);
                }
                // 默认状态为进行中(1)
                objV.put("status", 1);
                detailJSON.put(k, objV);
                // 减少商品库存
                commodityService.increaseOrDecreaseInventory(Integer.parseInt(k), - (Integer) objV.get("number"));
            });
            // 写入订单信息库
            return new ReturnObject(true, orderInfoService.insertOrderInfo(dto.
                    getCustomerName(), JSON.toJSONString(detailJSON), dto.getOrderPrice()), 0);
        } catch (BizException e) {
            return new ReturnObject(e);
        }
    }
}
