package com.loveunited.tmall_b_backend.service.statistics;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loveunited.tmall_b_backend.entity.Commodity;
import com.loveunited.tmall_b_backend.entity.CommoditySaleVolume;
import com.loveunited.tmall_b_backend.entity.CommoditySaleVolumeByBrand;
import com.loveunited.tmall_b_backend.mapper.BrandMapper;
import com.loveunited.tmall_b_backend.mapper.CategoryMapper;
import com.loveunited.tmall_b_backend.mapper.CommodityMapper;
import com.loveunited.tmall_b_backend.service.commodity.dto.CommodityDTO;

/**
 * @author LiuWenshuo
 * Created on 2022-04-08
 */
@Service
public class StatisticsService {
    @Autowired
    CommodityMapper commodityMapper;
    @Autowired
    BrandMapper brandMapper;
    @Autowired
    CategoryMapper categoryMapper;

    /**
     * 根据分类聚合，给出每种分类的总销售额topN
     * @param topN
     * @return
     */
    public List<CommoditySaleVolume> getTopNCategories(Integer topN) {
        return commodityMapper.getTopNCategories(topN);
    }

    /**
     * 获取topN销量的商品
     * @param topN
     * @return
     */
    public List<CommodityDTO> getTopNCommodities(Integer topN) {
        final List<Commodity> topNCommodities = commodityMapper.getTopNCommodities(topN);
        return getCommodityDTOList(new ArrayList<>(), topNCommodities);
    }
    private List<CommodityDTO> getCommodityDTOList(List<CommodityDTO> result, List<Commodity> commodities) {
        for (Commodity commodity : commodities) {
            CommodityDTO dto = new CommodityDTO(commodity);
            dto.setBrand(brandMapper.queryBrandById(commodity.getBrandID()));
            dto.setCategory(categoryMapper.queryCategoryById(commodity.getCategoryID()));
            result.add(dto);
        }
        return result;
    }

    /**
     * 根据品牌聚合，给出每种品牌的总销售额topN
     * @param topN
     * @return
     */
    public List<CommoditySaleVolumeByBrand> getTopNBrands(Integer topN) {
        return commodityMapper.getTopNBrands(topN);
    }
}
