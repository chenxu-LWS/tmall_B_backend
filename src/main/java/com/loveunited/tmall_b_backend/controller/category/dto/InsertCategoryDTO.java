package com.loveunited.tmall_b_backend.controller.category.dto;

import java.io.Serializable;

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
public class InsertCategoryDTO implements Serializable {
    String name;
    Integer parentId;
}
