package com.loveunited.tmall_b_backend.service.category;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loveunited.tmall_b_backend.common.constants.ErrInfo;
import com.loveunited.tmall_b_backend.entity.Category;
import com.loveunited.tmall_b_backend.common.exception.BizException;
import com.loveunited.tmall_b_backend.mapper.CategoryMapper;
import com.loveunited.tmall_b_backend.service.category.dto.CategoryDTO;

/**
 * @author LiuWenshuo
 * Created on 2022-03-06
 */
@Service
public class CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

    /**
     * 递归查询整个品类的分层结构
     */
    public List<CategoryDTO> queryCategoryMap() {
        List<CategoryDTO> result = new ArrayList<>();
        List<Category> allCategory = categoryMapper.queryAllCategory();
        for (Category category : allCategory) {
            if (category.getParentCategoryID() == 0) {
                CategoryDTO categoryDTO = new CategoryDTO(category);
                getSubCategoryRecurse(categoryDTO, allCategory);
                result.add(categoryDTO);
            }
        }
        return result;
    }
    private CategoryDTO getSubCategoryRecurse(CategoryDTO parent, List<Category> allCategory) {
        for (Category category : allCategory) {
            if (category.getParentCategoryID().equals(parent.getId())) {
                if (parent.getChildren() == null) {
                    parent.setChildren(new ArrayList<>());
                }
                parent.getChildren().add(getSubCategoryRecurse(new CategoryDTO(category), allCategory));
            }
        }
        return parent;
    }

    /**
     * 查询一个品类的所有子品类
     * @param id
     * @return
     */
    public List<Category> querySubCategoryById(Integer id) throws BizException{
        if(categoryMapper.queryCategoryById(id) == null) {
            throw new BizException(ErrInfo.CATEGORY_ID_NOT_EXISTS);
        }
        return categoryMapper.querySubCategoryById(id);
    }

    /**
     * 查询一个品类的父品类
     * @param id
     * @return
     */
    public Category getParentCategoryById(Integer id) throws BizException{
        if(categoryMapper.queryCategoryById(id) == null) {
            throw new BizException(ErrInfo.CATEGORY_ID_NOT_EXISTS);
        }
        return categoryMapper.queryParentCategoryById(id);
    }

    /**
     * 插入一个新的品类
     * @param name
     * @param parentId
     * @return
     */
    public Integer insertCategory(String name, Integer parentId) throws BizException{
        final List<Category> allCategory = categoryMapper.queryAllCategory();
        Integer maxLevel = 0;
        for (Category category : allCategory) {
            if (category.getName().equals(name)) {
                throw new BizException(ErrInfo.INSERT_CATEGORY_NAME_EXISTS);
            }
            if (category.getLevel() > maxLevel) {
                maxLevel = category.getLevel();
            }
        }
        Integer level = 0;
        if (parentId != 0) {
            Category parentCategory = categoryMapper.queryCategoryById(parentId);
            if (parentCategory == null) {
                throw new BizException(ErrInfo.PARENT_CATEGORY_NOT_EXISTS);
            }
            level = parentCategory.getLevel();
        }
        return categoryMapper.insertCategory(name, parentId, level + 1);
    }

    /**
     * 删除一个品类
     * @param id
     * @return
     */
    public Integer deleteCategory(Integer id) throws BizException{
        if (categoryMapper.queryCategoryById(id) == null) {
            throw new BizException(ErrInfo.CATEGORY_ID_NOT_EXISTS);
        }
        final List<Category> categories = categoryMapper.querySubCategoryById(id);
        if (categories!=null && !categories.isEmpty()) {
            throw new BizException(ErrInfo.DELETE_CATEGORY_FAILED);
        }
        return categoryMapper.deleteCategory(id);
    }

    /**
     * 给一个品类的商品数+1,这里做了CAS防并发
     * @param id
     * @return
     */
    public Integer increaseOrDecreaseCategoryCommodityNum(Integer id, Integer num) {
        return categoryMapper.increaseOrDecreaseCategoryCommodityNum(id, num);
    }
}
