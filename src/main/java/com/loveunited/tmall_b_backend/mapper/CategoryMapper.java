package com.loveunited.tmall_b_backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.loveunited.tmall_b_backend.entity.Category;

/**
 * @author LiuWenshuo
 * Created on 2022-03-05
 */
@Mapper
@Component
public interface CategoryMapper {
    public Category queryCategoryById(Integer id);
    public List<Category> querySubCategoryById(Integer id);
    public Category queryParentCategoryById(Integer id);

    public Integer insertCategory(String name, Integer parentId, Integer level);
    public Integer deleteCategory(Integer id);
    public Integer increaseCategoryCommodityNum(Integer id);
}
