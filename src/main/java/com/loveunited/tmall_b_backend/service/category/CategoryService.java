package com.loveunited.tmall_b_backend.service.category;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loveunited.tmall_b_backend.common.constants.ErrInfo;
import com.loveunited.tmall_b_backend.common.exception.BizException;
import com.loveunited.tmall_b_backend.common.page.PageBean;
import com.loveunited.tmall_b_backend.entity.Category;
import com.loveunited.tmall_b_backend.mapper.CategoryMapper;
import com.loveunited.tmall_b_backend.service.category.dto.CategoryDTO;
import com.loveunited.tmall_b_backend.service.commodity.CommodityService;
import com.loveunited.tmall_b_backend.service.commodity.dto.CommodityDTO;

/**
 * @author LiuWenshuo
 * Created on 2022-03-06
 */
@Service
public class CategoryService {

    Logger logger = Logger.getLogger(CategoryService.class);

    @Autowired
    CategoryMapper categoryMapper;
    @Autowired
    CommodityService commodityService;

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
     * 通过层级查询层级下所有的品类
     * @param level
     * @return
     */
    public List<Category> queryCategoryByLevel(Integer level) {
        return categoryMapper.queryCategoryByLevel(level);
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
        logger.info("正在插入：" + name);
        for (Category category : allCategory) {
            logger.info(category.getName());
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
        Category category = new Category(null, name, parentId, level + 1, 0);
        categoryMapper.insertCategory(category);
        return category.getId();
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
        final PageBean<CommodityDTO> commodityDTOPageBean =
                commodityService.queryByCategoryId(id, 0, 2);
        if (commodityDTOPageBean.getTotalNum() != 0) {
            throw new BizException(ErrInfo.DELETE_CATEGORY_FAILED_COMMODITY_BIND);
        }
        return categoryMapper.deleteCategory(id);
    }

    /**
     * 上架/下架一个商品时，品类自底向上的商品数量都变化
     * @return
     */
    public Integer increaseOrDecreaseComNumToRoot(Integer id, Integer num) {
        categoryMapper.increaseOrDecreaseCategoryCommodityNum(id, num);
        // 只要还没到根结点,就继续
        if (id == 0) {
            return 0;
        }
        return increaseOrDecreaseComNumToRoot(categoryMapper.queryCategoryById(id).getParentCategoryID(), num);
    }
}
