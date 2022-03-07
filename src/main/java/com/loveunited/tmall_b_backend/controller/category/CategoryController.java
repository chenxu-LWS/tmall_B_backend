package com.loveunited.tmall_b_backend.controller.category;

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
import com.loveunited.tmall_b_backend.controller.category.dto.InsertCategoryDTO;
import com.loveunited.tmall_b_backend.entity.Category;
import com.loveunited.tmall_b_backend.service.category.CategoryService;

/**
 * @author LiuWenshuo
 * Created on 2022-03-06
 */
@Controller
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    CategoryService categoryService;

    @RequestMapping("/queryCategoryMap")
    @ResponseBody
    public ReturnListObject queryCategoryMap() {
        return new ReturnListObject(true, new ArrayList<>(categoryService.queryCategoryMap()), 0);
    }

    @RequestMapping("/querySubCategoryById")
    @ResponseBody
    public ReturnListObject querySubCategoryById(Integer id) {
        if (id == null) {
            return new ReturnListObject(false, null,
                    ErrInfo.PARAMETER_ERROR.getCode(), ErrInfo.PARAMETER_ERROR.getMessage());
        }
        try {
            List<Category> subCategoryById = categoryService.querySubCategoryById(id);
            return new ReturnListObject(true, new ArrayList<>(subCategoryById), 0);
        } catch (BizException e) {
            return new ReturnListObject(false, null, e.getCode(), e.getMessage());
        }
    }

    @RequestMapping("/queryParentCategoryById")
    @ResponseBody
    public ReturnObject queryParentCategoryById(Integer id) {
        if (id == null) {
            return new ReturnObject(false, null,
                    ErrInfo.PARAMETER_ERROR.getCode(), ErrInfo.PARAMETER_ERROR.getMessage());
        }
        try {
            final Category parentCategory = categoryService.getParentCategoryById(id);
            return new ReturnObject(true, parentCategory, 0);
        } catch (BizException e) {
            return new ReturnObject(false, null, e.getCode(), e.getMessage());
        }
    }

    @RequestMapping("/insertCategory")
    @ResponseBody
    public ReturnObject insertCategory(@RequestBody InsertCategoryDTO dto) {
        if (dto == null || dto.getName() == null || dto.getParentId() == null
         || dto.getName().length() > 50) {
            return new ReturnObject(false, null,
                    ErrInfo.PARAMETER_ERROR.getCode(), ErrInfo.PARAMETER_ERROR.getMessage());
        }
        try {
            final Integer result = categoryService.insertCategory(dto.getName(), dto.getParentId());
            return new ReturnObject(true, result, 0);
        } catch (BizException e) {
            return new ReturnObject(false, null, e.getCode(), e.getMessage());
        }
    }

    @RequestMapping("/deleteCategory")
    @ResponseBody
    public ReturnObject deleteCategory(Integer id) {
        if (id == null) {
            return new ReturnObject(false, null,
                    ErrInfo.PARAMETER_ERROR.getCode(), ErrInfo.PARAMETER_ERROR.getMessage());
        }
        try {
            return new ReturnObject(true, categoryService.deleteCategory(id), 0);
        } catch (BizException e) {
            return new ReturnObject(false, null, e.getCode(), e.getMessage());
        }
    }
}
