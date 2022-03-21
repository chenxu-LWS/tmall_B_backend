package com.loveunited.tmall_b_backend.controller.order_info;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loveunited.tmall_b_backend.common.ReturnObject;
import com.loveunited.tmall_b_backend.common.ReturnPageObject;
import com.loveunited.tmall_b_backend.common.constants.ErrInfo;
import com.loveunited.tmall_b_backend.common.exception.BizException;
import com.loveunited.tmall_b_backend.common.page.PageBean;
import com.loveunited.tmall_b_backend.controller.order_info.dto.QueryOrderInfoByCustomerNameByPageDTO;
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

    @PostMapping("/queryByCustomerNameByPage")
    @ResponseBody
    public ReturnPageObject<OrderInfoDTO> queryOrderInfoByCustomerNameByPage(@RequestBody QueryOrderInfoByCustomerNameByPageDTO dto) {
        if (dto.getCustomerName() == null || dto.hasNull()) {
            return new ReturnPageObject<>(ErrInfo.PARAMETER_ERROR);
        }
        final PageBean<OrderInfoDTO> orderInfoDTOPageBean = orderInfoService
                .queryOrderInfoByCustomerNameByPage(dto.getCustomerName(), dto.getPageNo(), dto.getPageSize());
        return new ReturnPageObject<>(true, orderInfoDTOPageBean, 0);
    }
}
