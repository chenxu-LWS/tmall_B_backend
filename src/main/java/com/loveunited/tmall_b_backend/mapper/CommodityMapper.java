package com.loveunited.tmall_b_backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
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
    public List<Commodity> queryCommodityListByStatus(Integer status);
    public List<Commodity> batchQueryCommodityByCategoryId(List<Integer> categories);
    public List<Commodity> queryCommodityByCategoryIdAndBrandId(@Param("list") List<Integer> categories, @Param("branchId") Integer brandId);

    public Integer insertCommodity(Commodity commodity);
    public Integer updateCommodityProperties(@Param("id") Integer id, @Param("props") String props);
    public Integer updateCommodityStatus(@Param("id") Integer id, @Param("status") Integer status);
    public Integer updateCommodityPrice(@Param("id") Integer id, @Param("newPrice") Integer newPrice);
    public Integer increaseOrDecreaseInventory(@Param("id") Integer id, @Param("stockNum") Integer stockNum);
}
