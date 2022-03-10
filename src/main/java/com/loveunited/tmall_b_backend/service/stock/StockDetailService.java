package com.loveunited.tmall_b_backend.service.stock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loveunited.tmall_b_backend.common.constants.ErrInfo;
import com.loveunited.tmall_b_backend.common.exception.BizException;
import com.loveunited.tmall_b_backend.entity.Commodity;
import com.loveunited.tmall_b_backend.entity.StockDetail;
import com.loveunited.tmall_b_backend.mapper.BrandMapper;
import com.loveunited.tmall_b_backend.mapper.CategoryMapper;
import com.loveunited.tmall_b_backend.mapper.CommodityMapper;
import com.loveunited.tmall_b_backend.mapper.StockDetailMapper;
import com.loveunited.tmall_b_backend.service.commodity.dto.CommodityDTO;
import com.loveunited.tmall_b_backend.service.stock.dto.StockDetailDTO;

/**
 * @author LiuWenshuo
 * Created on 2022-03-10
 */
@Service
public class StockDetailService {
    @Autowired
    StockDetailMapper stockDetailMapper;
    @Autowired
    CommodityMapper commodityMapper;
    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    BrandMapper brandMapper;

    /**
     * 通过id查询进货信息
     * @param id
     * @return
     * @throws BizException
     */
    public StockDetailDTO queryStockDetailById(Integer id) {
        final StockDetail stockDetail =
                stockDetailMapper.queryStockDetailById(id);
        StockDetailDTO result = new StockDetailDTO(stockDetail);
        final Commodity commodity = commodityMapper.queryCommodityById(stockDetail.getCommodityID());
        result.setCommodityDTO(getCommodityDTOFromCom(commodity));
        return result;
    }

    /**
     * 通过商品ID查询其相关的所有进货信息
     * @param commodityId
     * @return
     * @throws BizException
     */
    public List<StockDetailDTO> queryStockDetailByCommodityId(Integer commodityId) throws BizException{
        final List<StockDetail> stockDetails = stockDetailMapper.queryStockDetailByCommodityId(commodityId);
        final Commodity commodity = commodityMapper.queryCommodityById(commodityId);
        if (commodity == null) {
            throw new BizException(ErrInfo.COMMODITY_ID_NOT_EXISTS);
        }
        CommodityDTO commodityDTO = getCommodityDTOFromCom(commodity);

        List<StockDetailDTO> result = new ArrayList<>();
        for (StockDetail stockDetail : stockDetails) {
            StockDetailDTO stockDetailDTO = new StockDetailDTO(stockDetail);
            stockDetailDTO.setCommodityDTO(commodityDTO);
            result.add(stockDetailDTO);
        }
        return result;
    }
    private CommodityDTO getCommodityDTOFromCom(Commodity commodity) {
        CommodityDTO dto = new CommodityDTO(commodity);
        dto.setCategory(categoryMapper.queryCategoryById(commodity.getCategoryID()));
        dto.setBrand(brandMapper.queryBrandById(commodity.getBrandID()));
        return dto;
    }

    /**
     * 插入一条进货信息
     * @return
     */
    public Integer insertStockDetail(Integer commodityId, Double stockPrice,
            Integer stockNum) throws BizException {
        if (commodityMapper.queryCommodityById(commodityId) == null) {
            throw new BizException(ErrInfo.COMMODITY_ID_NOT_EXISTS);
        }
        // 插入进货信息表
        StockDetail stockDetail = new StockDetail(null, commodityId, stockPrice, stockNum, null);
        stockDetailMapper.insertStockDetail(stockDetail);
        // 更新商品个数
        commodityMapper.increaseOrDecreaseCommodityNum(commodityId, stockNum);
        return stockDetail.getId();
    }
}
