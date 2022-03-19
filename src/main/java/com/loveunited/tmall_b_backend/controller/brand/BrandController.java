package com.loveunited.tmall_b_backend.controller.brand;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loveunited.tmall_b_backend.common.ReturnListObject;
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

    @RequestMapping("/queryAll")
    @ResponseBody
    public ReturnListObject queryAll() {
        return new ReturnListObject(true, new ArrayList<>(brandService.queryAll()), 0);
    }

    @RequestMapping("/queryById")
    @ResponseBody
    public ReturnObject queryBrandById(Integer id) {
        if (id == null) {
            return new ReturnObject(ErrInfo.PARAMETER_ERROR);
        }
        return new ReturnObject(true, brandService.queryBrandById(id), 0);
    }

    @RequestMapping("/insert")
    @ResponseBody
    public ReturnObject insertBrand(String name) {
        if (name == null || name.length() > 50) {
            return new ReturnObject(ErrInfo.PARAMETER_ERROR);
        }
        try {
            final Integer result = brandService.insertBrand(name);
            return new ReturnObject(true, result, 0);
        } catch (BizException e) {
            return new ReturnObject(e);
        }
    }

    @RequestMapping("/deleteById")
    @ResponseBody
    public ReturnObject deleteBrandById(Integer id) {
        if (id == null) {
            return new ReturnObject(ErrInfo.PARAMETER_ERROR);
        }
        try {
            final Integer result = brandService.deleteBrandById(id);
            return new ReturnObject(true, result, 0);
        } catch (BizException e) {
            return new ReturnObject(e);
        }
    }
}
