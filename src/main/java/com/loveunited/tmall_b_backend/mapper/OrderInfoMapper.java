package com.loveunited.tmall_b_backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.loveunited.tmall_b_backend.entity.OrderInfo;

/**
 * @author LiuWenshuo
 * Created on 2022-03-11
 */
@Mapper
@Component
public interface OrderInfoMapper {
    public OrderInfo queryOrderInfoById(Integer id);
    public List<OrderInfo> queryOrderInfoByCustomerName(String customerName);

    public Integer insertOrderInfo(OrderInfo orderInfo);
    public Integer updateCommodityStatusInOrderInfo(OrderInfo orderInfo);
}
