package com.loveunited.tmall_b_backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.loveunited.tmall_b_backend.entity.Commodity;
import com.loveunited.tmall_b_backend.entity.CommoditySaleVolume;
import com.loveunited.tmall_b_backend.entity.CommoditySaleVolumeByBrand;

/**
 * @author LiuWenshuo
 * Created on 2022-03-08
 */
@Mapper
@Component
public interface CommodityMapper {
    public List<Commodity> queryCommodityByConditionByPage(@Param("list") List<Integer> categories, @Param("brandId") Integer brandId,
            @Param("propK") String propK, @Param("propV") String propV,
            @Param("priceLow") Double priceLow, @Param("priceHigh") Double priceHigh,
            @Param("sortedBy") String sortedBy, @Param("sortDesc") Boolean sortDesc, @Param("onlyOnSale") Boolean onlyOnSale,
            @Param("offset") Integer offset,
            @Param("pageSize") Integer pageSize);
    public Integer queryCommodityByConditionTotalNum(@Param("list") List<Integer> categories, @Param("brandId") Integer brandId,
            @Param("priceLow") Double priceLow, @Param("priceHigh") Double priceHigh,
            @Param("propK") String propK, @Param("propV") String propV, @Param("onlyOnSale") Boolean onlyOnSale);

    public Commodity queryCommodityById(Integer id);
    // 品牌ID筛选
    public List<Commodity> queryCommodityByBrandIdByPage(@Param("brandId") Integer brandId,
            @Param("offset") Integer offset, @Param("pageSize") Integer pageSize);
    public Integer queryCommodityByBrandIdTotalNum(Integer brandId);

    public String queryCommodityPropsById(Integer id);
    // 状态筛选
    public List<Commodity> queryCommodityListByStatusByPage(@Param("status") Integer status,
            @Param("offset") Integer offset, @Param("pageSize") Integer pageSize);
    public Integer queryCommodityListByStatusTotalNum(Integer status);
    // 品类ID筛选
    public List<Commodity> batchQueryCommodityByCategoryIdByPage(@Param("list") List<Integer> categories,
            @Param("offset") Integer offset, @Param("pageSize") Integer pageSize);
    public Integer batchQueryCommodityByCategoryIdTotalNum(List<Integer> categories);
    // 品类ID&品牌ID筛选
    public List<Commodity> queryCommodityByCategoryIdAndBrandIdByPage
            (@Param("list") List<Integer> categories, @Param("branchId") Integer brandId,
                    @Param("offset") Integer offset, @Param("pageSize") Integer pageSize);
    public Integer queryCommodityByCategoryIdAndBrandIdTotalNum(@Param("list") List<Integer> categories,
            @Param("branchId") Integer brandId);
    // 查询所有
    public List<Commodity> queryAllByPage(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);
    public Integer queryAllTotalNum();

    public List<CommoditySaleVolume> getTopNCategories(@Param("topN") Integer topN);
    public List<Commodity> getTopNCommodities(@Param("topN") Integer topN);
    public List<CommoditySaleVolumeByBrand> getTopNBrands(@Param("topN") Integer topN);

    public Integer insertCommodity(Commodity commodity);
    public Integer updateCommodityProperties(@Param("id") Integer id, @Param("props") String props);
    public Integer updateCommodityStatus(@Param("id") Integer id, @Param("status") Integer status);
    public Integer updateCommodityPrice(@Param("id") Integer id, @Param("newPrice") Integer newPrice);
    public Integer increaseOrDecreaseInventory(@Param("id") Integer id, @Param("stockNum") Integer stockNum);
}
