package com.loveunited.tmall_b_backend.service.commodity;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.loveunited.tmall_b_backend.common.constants.ErrInfo;
import com.loveunited.tmall_b_backend.common.exception.BizException;
import com.loveunited.tmall_b_backend.entity.Commodity;
import com.loveunited.tmall_b_backend.mapper.BrandMapper;
import com.loveunited.tmall_b_backend.mapper.CategoryMapper;
import com.loveunited.tmall_b_backend.mapper.CommodityMapper;
import com.loveunited.tmall_b_backend.service.commodity.dto.CommodityDTO;

/**
 * @author LiuWenshuo
 * Created on 2022-03-08
 */
@Service
public class CommodityService {
    @Autowired
    CommodityMapper commodityMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    BrandMapper brandMapper;

    /**
     * 插入一条商品信息
     * @param categoryID
     * @param brandID
     * @param price
     * @param props
     * @return
     * @throws BizException
     */
    public Integer insert(Integer categoryID, Integer brandID, Double price, String props, String detail) throws BizException {
        if (categoryMapper.queryCategoryById(categoryID) == null) {
            throw new BizException(ErrInfo.CATEGORY_ID_NOT_EXISTS);
        } else if (brandMapper.queryBrandById(brandID) == null) {
            throw new BizException(ErrInfo.BRAND_ID_NOT_EXISTS);
        }
        try {
            Map<String, Object> result = JSON.parseObject(props);
            System.out.println(result);
        } catch (JSONException e) {
            throw new BizException(ErrInfo.PARAMETER_ERROR_CANNOT_CAST_TO_JSON);
        }
        return commodityMapper.insertCommodity(categoryID, brandID, price, props, detail);
    }

    /**
     * 给指定产品增加一条筛选属性
     * @param id
     * @param key
     * @param value
     * @return
     */
    public Integer addProp(Integer id, String key, String value) {
        try {
            String propsJson = commodityMapper.queryCommodityPropsById(id);
            Map<String, Object> result = JSON.parseObject(propsJson);
            result.put(key, value);
            return commodityMapper.updateCommodityProperties(id, JSON.toJSONString(result));
        } catch (JSONException e) {
            throw new BizException(ErrInfo.PARAMETER_ERROR_CANNOT_CAST_TO_JSON);
        }
    }

    /**
     * 根据ID查询商品基本信息
     * @param id
     * @return
     */
    public CommodityDTO queryById(Integer id) {
        final Commodity commodity = commodityMapper.queryCommodityById(id);
        CommodityDTO result = new CommodityDTO(commodity);
        result.setCategory(categoryMapper.queryCategoryById(commodity.getCategoryID()));
        result.setBrand(brandMapper.queryBrandById(commodity.getBrandID()));
        return result;
    }
}
