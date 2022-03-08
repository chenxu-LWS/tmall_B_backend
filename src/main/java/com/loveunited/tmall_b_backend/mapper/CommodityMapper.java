package com.loveunited.tmall_b_backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.loveunited.tmall_b_backend.entity.Commodity;

/**
 * @author LiuWenshuo
 * Created on 2022-03-08
 */
@Mapper
@Component
public interface CommodityMapper {
    public Commodity queryCommodityById(Integer id);
    public List<Commodity> queryCommodityByBrandId(Integer brandID);
    public String queryCommodityPropsById(Integer id);

    public Integer insertCommodity(Integer categoryID, Integer brandID,
            Double price, String props, String detail);
    public Integer updateCommodityProperties(Integer id, String props);
    public Integer updateCommodityStatus(Integer id, Integer status);
    public Integer updateCommodityPrice(Integer id, Integer newPrice);
}
