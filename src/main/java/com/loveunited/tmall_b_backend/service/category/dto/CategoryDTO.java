package com.loveunited.tmall_b_backend.service.category.dto;

import java.util.List;

import com.loveunited.tmall_b_backend.entity.Category;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author LiuWenshuo
 * Created on 2022-03-07
 */
@Getter
@Setter
@ToString
public class CategoryDTO {
    private String id;
    private String name; // 类型名
    private String parentCategoryID; // 父类型ID
    private List<CategoryDTO> children; // 子类型
    private Integer level; // 类型所属的层级
    private Integer commodityNum; // 当前种类下的商品数量

    public CategoryDTO() {}

    public CategoryDTO(Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.parentCategoryID = category.getParentCategoryID();
        this.level = category.getLevel();
        this.commodityNum = category.getCommodityNum();
    }
}
