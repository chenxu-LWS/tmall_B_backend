package com.loveunited.tmall_b_backend.controller.brand;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loveunited.tmall_b_backend.common.ReturnObject;
import com.loveunited.tmall_b_backend.common.constants.ErrInfo;
import com.loveunited.tmall_b_backend.common.exception.BizException;
import com.loveunited.tmall_b_backend.service.brand.BrandService;

/**
 * @author LiuWenshuo
 * Created on 2022-03-07
 */
@Controller
@RequestMapping("/api/brand")
public class BrandController {
    @Autowired
    BrandService brandService;

    @RequestMapping("/queryBrandById")
    @ResponseBody
    public ReturnObject queryBrandById(Integer id) {
        if (id == null) {
            return new ReturnObject(false, null,
                    ErrInfo.PARAMETER_ERROR.getCode(), ErrInfo.PARAMETER_ERROR.getMessage());
        }
        return new ReturnObject(true, brandService.queryBrandById(id), 0);
    }

    @RequestMapping("/insertBrand")
    @ResponseBody
    public ReturnObject insertBrand(String name) {
        if (name == null || name.length() > 50) {
            return new ReturnObject(false, null,
                    ErrInfo.PARAMETER_ERROR.getCode(), ErrInfo.PARAMETER_ERROR.getMessage());
        }
        try {
            final Integer result = brandService.insertBrand(name);
            return new ReturnObject(true, result, 0);
        } catch (BizException e) {
            return new ReturnObject(false, null, e.getCode(), e.getMessage());
        }
    }

    @RequestMapping("/deleteBrandById")
    @ResponseBody
    public ReturnObject deleteBrandById(Integer id) {
        if (id == null) {
            return new ReturnObject(false, null,
                    ErrInfo.PARAMETER_ERROR.getCode(), ErrInfo.PARAMETER_ERROR.getMessage());
        }
        try {
            final Integer result = brandService.deleteBrandById(id);
            return new ReturnObject(true, result, 0);
        } catch (BizException e) {
            return new ReturnObject(false, null, e.getCode(), e.getMessage());
        }
    }
}
