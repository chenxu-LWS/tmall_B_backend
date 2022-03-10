package com.loveunited.tmall_b_backend.controller.commodity;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loveunited.tmall_b_backend.common.ReturnListObject;
import com.loveunited.tmall_b_backend.common.ReturnObject;
import com.loveunited.tmall_b_backend.common.constants.ErrInfo;
import com.loveunited.tmall_b_backend.common.exception.BizException;
import com.loveunited.tmall_b_backend.controller.commodity.dto.InsertCommodityDTO;
import com.loveunited.tmall_b_backend.controller.commodity.dto.UpdateCommodityPropDTO;
import com.loveunited.tmall_b_backend.service.category.CategoryService;
import com.loveunited.tmall_b_backend.service.commodity.CommodityService;
import com.loveunited.tmall_b_backend.service.commodity.dto.CommodityDTO;
import com.loveunited.tmall_b_backend.service.commodity_picture.CommodityPictureService;

/**
 * @author LiuWenshuo
 * Created on 2022-03-08
 */
@Controller
@RequestMapping("/api/commodity")
public class CommodityController {
    @Autowired
    CommodityService commodityService;
    @Autowired
    CommodityPictureService pictureService;
    @Autowired
    CategoryService categoryService;

    @RequestMapping("/insert")
    @ResponseBody
    public ReturnObject insert(@RequestBody InsertCommodityDTO dto) {
        // 参数校验
        if (dto == null || dto.getPrice() == null || dto.getDetail() == null
                || dto.getProps() == null || dto.getCategoryID() == null
                || dto.getBrandID() == null || dto.getMainPicBase64() == null
                || dto.getMainPicBase64().isEmpty() || dto.getDetailPicBase64() == null
        || dto.getDetailPicBase64().isEmpty()) {
            return new ReturnObject(ErrInfo.PARAMETER_ERROR);
        }
        try {
            // 记录在商品表
            Integer result = commodityService.insert(dto.getCategoryID(), dto.getName(), dto.getBrandID(),
                    dto.getPrice(), dto.getProps(), dto.getDetail());
            // TODO: 图片记录在图片表
            return new ReturnObject(true, result, 0);
        } catch (BizException e) {
            return new ReturnObject(e);
        }
    }

    @RequestMapping("/queryByStatus")
    @ResponseBody
    public ReturnListObject queryByStatus(Integer status) {
        if (status <0 || status > 2) {
            return new ReturnListObject(ErrInfo.PARAMETER_ERROR);
        }
        return new ReturnListObject(true,
                new ArrayList<>(commodityService.queryCommodityListByStatus(status)), 0);
    }

    @RequestMapping("/queryById")
    @ResponseBody
    public ReturnObject queryById(Integer id) {
        try {
            final CommodityDTO dto = commodityService.queryById(id);
            return new ReturnObject(true, dto, 0);
        } catch (BizException e) {
            return new ReturnObject(e);
        }
    }

    @RequestMapping("/queryByCategoryId")
    @ResponseBody
    public ReturnObject queryByCategoryId(Integer categoryId) {
        if (categoryId == null || categoryId <=0) {
            return new ReturnObject(ErrInfo.PARAMETER_ERROR);
        }
        try {
            final List<CommodityDTO> commodityDTOS = commodityService.queryByCategoryId(categoryId);
            return new ReturnObject(true, commodityDTOS, 0);
        } catch (BizException e) {
            return new ReturnObject(e);
        }
    }

    @RequestMapping("/queryByBrandId")
    @ResponseBody
    public ReturnObject queryByBrandId(Integer brandId) {
        try {
            final List<CommodityDTO> commodityDTOS = commodityService.queryByBrandId(brandId);
            return new ReturnObject(true, commodityDTOS, 0);
        } catch (BizException e) {
            return new ReturnObject(e);
        }
    }

    @RequestMapping("/queryByBrandIdAndCategoryId")
    @ResponseBody
    public ReturnObject queryByBrandIdAndCategoryId(Integer brandId, Integer categoryId) {
        try {
            final List<CommodityDTO> commodityDTOS = commodityService.queryByBrandIdAndCategoryId(brandId, categoryId);
            return new ReturnObject(true, commodityDTOS, 0);
        } catch (BizException e) {
            return new ReturnObject(e);
        }
    }

    @RequestMapping("/putOnSaleById")
    @ResponseBody
    public ReturnObject putOnSaleById(Integer id) {
        try {
            final Integer result = commodityService.putOnSale(id);
            return new ReturnObject(true, result, 0);
        } catch (BizException e) {
            return new ReturnObject(e);
        }
    }

    @RequestMapping("/putOfflineById")
    @ResponseBody
    public ReturnObject putOfflineById(Integer id) {
        try {
            final Integer result = commodityService.putOffLine(id);
            return new ReturnObject(true, result, 0);
        } catch (BizException e) {
            return new ReturnObject(e);
        }
    }


    @RequestMapping("/updateProp")
    @ResponseBody
    public ReturnObject updateProp(@RequestBody UpdateCommodityPropDTO dto) {
        try {
            final Integer result = commodityService.updateProp(dto.getId(), dto.getNewProps());
            return new ReturnObject(true, result, 0);
        } catch (BizException e) {
            return new ReturnObject(e);
        }
    }
}
