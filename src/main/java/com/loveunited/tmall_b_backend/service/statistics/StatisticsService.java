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
import com.loveunited.tmall_b_backend.service.statistics.dto.CommoditySaleVolumeByBrandDTO;
import com.loveunited.tmall_b_backend.service.statistics.dto.CommoditySaleVolumeDTO;

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
    public List<CommoditySaleVolumeDTO> getTopNCategories(Integer topN) {
        final List<CommoditySaleVolume> topNCategories = commodityMapper.getTopNCategories(topN);
        List<CommoditySaleVolumeDTO> result = new ArrayList<>();
        topNCategories.forEach(category -> {
            final CommoditySaleVolumeDTO commoditySaleVolumeDTO = new CommoditySaleVolumeDTO();
            commoditySaleVolumeDTO.setCategory(categoryMapper.queryCategoryById(category.getCategoryId()));
            commoditySaleVolumeDTO.setTotalPrice(category.getTotalPrice());
            result.add(commoditySaleVolumeDTO);
        });
        return result;
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
    public List<CommoditySaleVolumeByBrandDTO> getTopNBrands(Integer topN) {
        final List<CommoditySaleVolumeByBrand> topNBrands = commodityMapper.getTopNBrands(topN);
        List<CommoditySaleVolumeByBrandDTO> result = new ArrayList<>();
        topNBrands.forEach(brand -> {
            final CommoditySaleVolumeByBrandDTO dto = new CommoditySaleVolumeByBrandDTO();
            dto.setBrand(brandMapper.queryBrandById(brand.getBrandId()));
            dto.setTotalPrice(brand.getTotalPrice());
            result.add(dto);
        });
        return result;
    }
}
