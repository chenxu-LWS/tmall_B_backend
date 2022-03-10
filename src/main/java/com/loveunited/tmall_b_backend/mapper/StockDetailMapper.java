package com.loveunited.tmall_b_backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.loveunited.tmall_b_backend.entity.StockDetail;

/**
 * @author LiuWenshuo
 * Created on 2022-03-09
 */
@Mapper
@Component
public interface StockDetailMapper {
    public StockDetail queryStockDetailById(Integer id);
    public List<StockDetail> queryStockDetailByCommodityId(Integer commodityId);

    public Integer insertStockDetail(StockDetail stockDetail);
}
