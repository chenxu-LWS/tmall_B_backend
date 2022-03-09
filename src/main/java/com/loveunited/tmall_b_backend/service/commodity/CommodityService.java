package com.loveunited.tmall_b_backend.service.commodity;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.LinkedBlockingDeque;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONException;
import com.loveunited.tmall_b_backend.common.constants.ErrInfo;
import com.loveunited.tmall_b_backend.common.exception.BizException;
import com.loveunited.tmall_b_backend.entity.Category;
import com.loveunited.tmall_b_backend.entity.Commodity;
import com.loveunited.tmall_b_backend.mapper.BrandMapper;
import com.loveunited.tmall_b_backend.mapper.CategoryMapper;
import com.loveunited.tmall_b_backend.mapper.CommodityMapper;
import com.loveunited.tmall_b_backend.mapper.CommodityPictureMapper;
import com.loveunited.tmall_b_backend.service.category.CategoryService;
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
    @Autowired
    CommodityPictureMapper pictureMapper;

    @Autowired
    CategoryService categoryService;

    /**
     * 插入一条商品信息
     * @param categoryID
     * @param brandID
     * @param price
     * @param props
     * @return
     * @throws BizException
     */
    public Integer insert(Integer categoryID, String name, Integer brandID, Double price,
            String props, String detail) throws BizException {
        if (categoryMapper.queryCategoryById(categoryID) == null) {
            throw new BizException(ErrInfo.CATEGORY_ID_NOT_EXISTS);
        } else if (brandMapper.queryBrandById(brandID) == null) {
            throw new BizException(ErrInfo.BRAND_ID_NOT_EXISTS);
        }
        try {
            // 强转传入的props参数，校验合法性
            Map<String, Object> propMap = JSON.parseObject(props);
            System.out.println(propMap);
        } catch (JSONException e) {
            throw new BizException(ErrInfo.PARAMETER_ERROR_CANNOT_CAST_TO_JSON);
        }
        // 插入商品表,默认状态为已创建
        Commodity commodity = new Commodity(null, name, categoryID, brandID, price, props, detail, null, null);
        commodityMapper.insertCommodity(commodity);
        return commodity.getId();
    }

    /**
     * 根据状态查询某些商品
     * @return
     */
    public List<CommodityDTO> queryCommodityListByStatus(Integer status) {
        List<CommodityDTO> result = new ArrayList<>();
        final List<Commodity> commodities = commodityMapper.queryCommodityListByStatus(status);
        return getCommodityDTOList(result, commodities);
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
     * 根据ID查询商品基本信息
     * @param id
     * @return
     */
    public CommodityDTO queryById(Integer id) throws BizException {
        final Commodity commodity = commodityMapper.queryCommodityById(id);
        if (commodity == null) {
            throw new BizException(ErrInfo.COMMODITY_ID_NOT_EXISTS);
        }
        CommodityDTO result = new CommodityDTO(commodity);
        result.setCategory(categoryMapper.queryCategoryById(commodity.getCategoryID()));
        result.setBrand(brandMapper.queryBrandById(commodity.getBrandID()));
        return result;
    }

    /**
     * 类别ID作为筛选条件,筛选出产品
     * @param categoryId
     * @return
     */
    public List<CommodityDTO> queryByCategoryId(Integer categoryId) throws BizException{
        // 参数校验
        if (categoryMapper.queryCategoryById(categoryId) == null) {
            throw new BizException(ErrInfo.CATEGORY_ID_NOT_EXISTS);
        }
        // 先查询有没有子类别,放到一个list里，层序遍历图
        List<Integer> categories = new ArrayList<>();
        Queue<Integer> temp = new LinkedBlockingDeque<>();
        temp.add(categoryId);
        while (!temp.isEmpty()) {
            final Integer curr = temp.remove();
            categories.add(curr);
            final List<Category> subs = categoryMapper.querySubCategoryById(curr);
            for (Category sub : subs) {
                temp.add(sub.getId());
            }
        }
        // 查询所有子类别的商品
        final List<Commodity> commodities = commodityMapper.batchQueryCommodityByCategoryId(categories);
        return getCommodityDTOList(new ArrayList<>(), commodities);
    }

    /**
     * 根据品牌ID筛选商品
     * @param brandId
     * @return
     * @throws BizException
     */
    public List<CommodityDTO> queryByBrandId(Integer brandId) throws BizException {
        if (brandMapper.queryBrandById(brandId) == null) {
            throw new BizException(ErrInfo.BRAND_ID_NOT_EXISTS);
        }
        final List<Commodity> commodities = commodityMapper.queryCommodityByBrandId(brandId);
        return getCommodityDTOList(new ArrayList<>(), commodities);
    }

    /**
     * 根据品牌ID、品类ID分别筛选物品
     * @param brandId
     * @param categoryId
     * @return
     * @throws BizException
     */
    public List<CommodityDTO> queryByBrandIdAndCategoryId(Integer brandId,
            Integer categoryId) throws BizException {
        if (categoryMapper.queryCategoryById(categoryId) == null) {
            throw new BizException(ErrInfo.CATEGORY_ID_NOT_EXISTS);
        }
        if (brandMapper.queryBrandById(brandId) == null) {
            throw new BizException(ErrInfo.BRAND_ID_NOT_EXISTS);
        }
        // 先查询有没有子类别,放到一个list里，层序遍历图
        List<Integer> categories = new ArrayList<>();
        Queue<Integer> temp = new LinkedBlockingDeque<>();
        temp.add(categoryId);
        while (!temp.isEmpty()) {
            final Integer curr = temp.remove();
            categories.add(curr);
            final List<Category> subs = categoryMapper.querySubCategoryById(curr);
            for (Category sub : subs) {
                temp.add(sub.getId());
            }
        }
        final List<Commodity> commodities = commodityMapper.queryCommodityByCategoryIdAndBrandId(categories, brandId);
        return getCommodityDTOList(new ArrayList<>(), commodities);
    }

    /**
     * 将商品上架
     * @param id
     * @return
     */
    public Integer putOnSale(Integer id) throws BizException{
        final Commodity commodity = commodityMapper.queryCommodityById(id);
        if (commodity == null) {
            throw new BizException(ErrInfo.COMMODITY_ID_NOT_EXISTS);
        }
        if (commodity.getStatus() == 1) {
            throw new BizException(ErrInfo.COMMODITY_ALREADY_ON_SALE);
        }
        // 其对应品类的商品数量+1
        categoryService.increaseOrDecreaseComNumToRoot(commodity.getCategoryID(), 1);
        // 改变商品状态
        return commodityMapper.updateCommodityStatus(id, 1);
    }

    /**
     * 将商品下架
     * @param id
     * @return
     */
    public Integer putOffLine(Integer id) throws BizException{
        final Commodity commodity = commodityMapper.queryCommodityById(id);
        if (commodity == null) {
            throw new BizException(ErrInfo.COMMODITY_ID_NOT_EXISTS);
        }
        if (commodity.getStatus() != 1) {
            throw new BizException(ErrInfo.COMMODITY_STATUS_ERROR);
        }
        // 其对应品类的商品数量-1
        categoryService.increaseOrDecreaseComNumToRoot(commodity.getCategoryID(), -1);
        return commodityMapper.updateCommodityStatus(id, 2);
    }

    /**
     * 更新筛选标签
     * @param id
     * @param prop
     * @return
     * @throws BizException
     */
    public Integer updateProp(Integer id, String prop) throws BizException{
        try {
            final Map<String, Object> result = JSON.parseObject(prop);
            System.out.println(result);
        } catch (JSONException e) {
            throw new BizException(ErrInfo.PARAMETER_ERROR_CANNOT_CAST_TO_JSON);
        }
        if (commodityMapper.queryCommodityById(id) == null) {
            throw new BizException(ErrInfo.COMMODITY_ID_NOT_EXISTS);
        }
        return commodityMapper.updateCommodityProperties(id, prop);
    }
}
