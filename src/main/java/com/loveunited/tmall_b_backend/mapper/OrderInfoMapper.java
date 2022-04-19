package com.loveunited.tmall_b_backend.mapper;

import java.sql.Timestamp;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
    public List<OrderInfo> queryOrderInfoByCustomerNameByPage(
            @Param("customerName") String customerName,
            @Param("offset") Integer offset, @Param("pageSize") Integer pageSize);
    public Integer queryOrderInfoByCustomerNameTotalNum(String customerName);

    public List<OrderInfo> queryAllByPage(
            @Param("offset") Integer offset, @Param("pageSize") Integer pageSize);
    public Integer queryAllTotalNum();

    public List<OrderInfo> queryByTimeByPage(@Param("startTime") Timestamp startTime,
            @Param("endTime") Timestamp endTime,
            @Param("offset") Integer offset, @Param("pageSize") Integer pageSize);
    public Integer queryByTimeTotalNum(@Param("startTime") Timestamp startTime,
            @Param("endTime") Timestamp endTime);

    public Integer insertOrderInfo(OrderInfo orderInfo);
    public Integer updateCommodityStatusInOrderInfo(OrderInfo orderInfo);
}
