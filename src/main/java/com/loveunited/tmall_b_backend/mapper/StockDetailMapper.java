package com.loveunited.tmall_b_backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.loveunited.tmall_b_backend.entity.StockDetail;

/**
 * @author LiuWenshuo
 * Created on 2022-03-09
 */
@Mapper
@Component
public interface StockDetailMapper {
    public List<StockDetail> queryStockDetailAllByPage(@Param("offset") Integer offset,
            @Param("pageSize") Integer pageSize);
    public Integer queryStockDetailAllTotalNum();

    public StockDetail queryStockDetailById(Integer id);
    public List<StockDetail> queryStockDetailByCommodityIdByPage(
            @Param("commodityId") Integer commodityId, @Param("offset") Integer offset,
            @Param("pageSize") Integer pageSize);
    public Integer queryStockDetailByCommodityIdTotalNum(Integer commodityId);

    public Integer insertStockDetail(StockDetail stockDetail);
}
