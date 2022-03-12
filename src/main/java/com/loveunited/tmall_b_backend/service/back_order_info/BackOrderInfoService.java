package com.loveunited.tmall_b_backend.service.back_order_info;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loveunited.tmall_b_backend.common.constants.ErrInfo;
import com.loveunited.tmall_b_backend.common.exception.BizException;
import com.loveunited.tmall_b_backend.entity.BackOrderInfo;
import com.loveunited.tmall_b_backend.mapper.BackOrderInfoMapper;
import com.loveunited.tmall_b_backend.mapper.CommodityMapper;
import com.loveunited.tmall_b_backend.mapper.OrderInfoMapper;
import com.loveunited.tmall_b_backend.service.back_order_info.dto.BackOrderInfoDTO;
import com.loveunited.tmall_b_backend.service.commodity.CommodityService;
import com.loveunited.tmall_b_backend.service.order_info.OrderInfoService;

/**
 * @author LiuWenshuo
 * Created on 2022-03-11
 */
@Service
public class BackOrderInfoService {
    @Autowired
    BackOrderInfoMapper backOrderInfoMapper;
    @Autowired
    CommodityMapper commodityMapper;
    @Autowired
    OrderInfoMapper orderInfoMapper;

    @Autowired
    CommodityService commodityService;
    @Autowired
    OrderInfoService orderInfoService;

    public BackOrderInfoDTO queryBackOrderInfoById(Integer id) throws BizException {
        final BackOrderInfo backOrderInfo = backOrderInfoMapper.queryBackOrderInfoById(id);
        if (backOrderInfo == null) {
            throw new BizException(ErrInfo.BACKORDERINFO_ID_NOT_EXISTS);
        }
        BackOrderInfoDTO result = new BackOrderInfoDTO(backOrderInfo);
        result.setCommodityDTO(commodityService.queryById(backOrderInfo.getCommodityId()));
        result.setOrderInfoDTO(orderInfoService.queryOrderInfoById(backOrderInfo.getOrderInfoId()));
        return result;
    }

    public Integer insertBackOrderInfo(
            Integer orderInfoId,
            Integer commodityId,
            Integer backNum,
            Double price
    ) throws BizException {
        if (orderInfoMapper.queryOrderInfoById(orderInfoId) == null) {
            throw new BizException(ErrInfo.ORDERINFO_ID_NOT_EXISTS);
        }
        if (commodityMapper.queryCommodityById(commodityId) == null) {
            throw new BizException(ErrInfo.COMMODITY_ID_NOT_EXISTS);
        }
        final BackOrderInfo backOrderInfo = new BackOrderInfo(null, orderInfoId,
                commodityId, backNum, price, null);
        backOrderInfoMapper.insertBackOrderInfo(backOrderInfo);
        return backOrderInfo.getId();
    }
}
