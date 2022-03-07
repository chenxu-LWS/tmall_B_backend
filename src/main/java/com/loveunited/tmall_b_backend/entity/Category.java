package com.loveunited.tmall_b_backend.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author LiuWenshuo
 * Created on 2022-03-05
 */
@Getter
@Setter
@ToString
public class Category {
    private String id;
    private String name; // 类型名
    private String parentCategoryID; // 父类型ID
    private Integer level; // 类型所属的层级
    private Integer commodityNum; // 当前种类下的商品数量
}
