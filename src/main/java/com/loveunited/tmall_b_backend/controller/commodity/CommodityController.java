package com.loveunited.tmall_b_backend.controller.commodity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loveunited.tmall_b_backend.common.ReturnObject;
import com.loveunited.tmall_b_backend.common.ReturnPageObject;
import com.loveunited.tmall_b_backend.common.constants.ErrInfo;
import com.loveunited.tmall_b_backend.common.exception.BizException;
import com.loveunited.tmall_b_backend.common.page.PageBean;
import com.loveunited.tmall_b_backend.controller.commodity.dto.InsertCommodityDTO;
import com.loveunited.tmall_b_backend.controller.commodity.dto.QueryByBrandIdAndCategoryIdDTO;
import com.loveunited.tmall_b_backend.controller.commodity.dto.QueryByBrandIdByPageDTO;
import com.loveunited.tmall_b_backend.controller.commodity.dto.QueryByCategoryIdByPageDTO;
import com.loveunited.tmall_b_backend.controller.commodity.dto.QueryByStatusByPageDTO;
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

    @PostMapping("/queryByStatusByPage")
    @ResponseBody
    public ReturnPageObject<CommodityDTO> queryByStatusByPage(@RequestBody QueryByStatusByPageDTO dto) {
        if (dto == null || dto.getStatus() == null || dto.hasNull() || dto.getStatus() <0 || dto.getStatus() > 2) {
            return new ReturnPageObject<>(ErrInfo.PARAMETER_ERROR);
        }
        final PageBean<CommodityDTO> commodityDTOPageBean =
                commodityService.queryCommodityListByStatusByPage(dto.getStatus(), dto.getPageNo(), dto.getPageSize());
        return new ReturnPageObject<>(true, commodityDTOPageBean, 0);
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

    @PostMapping("/queryByCategoryIdByPage")
    @ResponseBody
    public ReturnPageObject<CommodityDTO> queryByCategoryIdByPage(@RequestBody QueryByCategoryIdByPageDTO dto) {
        if (dto == null || dto.getCategoryId() == null ||dto.getCategoryId() <=0) {
            return new ReturnPageObject<>(ErrInfo.PARAMETER_ERROR);
        }
        try {
            final PageBean<CommodityDTO> commodityDTOPageBean = commodityService.queryByCategoryId(dto.getCategoryId(),
                    dto.getPageNo(), dto.getPageSize());
            return new ReturnPageObject<>(true, commodityDTOPageBean, 0);
        } catch (BizException e) {
            return new ReturnPageObject<>(e);
        }
    }

    @PostMapping("/queryByBrandIdByPage")
    @ResponseBody
    public ReturnPageObject<CommodityDTO> queryByBrandIdByPage(@RequestBody QueryByBrandIdByPageDTO dto) {
        if (dto == null || dto.getBrandId() == null) {
            return new ReturnPageObject<>(ErrInfo.PARAMETER_ERROR);
        }
        try {
            return new ReturnPageObject<>(true, commodityService.
                    queryByBrandIdByPage(dto.getBrandId(), dto.getPageNo(), dto.getPageSize()), 0);
        } catch (BizException e) {
            return new ReturnPageObject<>(e);
        }
    }

    @PostMapping("/queryByBrandIdAndCategoryIdByPage")
    @ResponseBody
    public ReturnPageObject<CommodityDTO> queryByBrandIdAndCategoryIdByPage(@RequestBody QueryByBrandIdAndCategoryIdDTO dto) {
        if (dto == null || dto.getBrandId() == null || dto.getCategoryId() == null || dto.hasNull()) {
            return new ReturnPageObject<>(ErrInfo.PARAMETER_ERROR);
        }
        try {
            final PageBean<CommodityDTO> commodityDTOPageBean = commodityService.
                    queryByBrandIdAndCategoryIdByPage(dto.getBrandId(), dto.getCategoryId(),
                            dto.getPageNo(), dto.getPageSize());
            return new ReturnPageObject<>(true, commodityDTOPageBean, 0);
        } catch (BizException e) {
            return new ReturnPageObject<>(e);
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
