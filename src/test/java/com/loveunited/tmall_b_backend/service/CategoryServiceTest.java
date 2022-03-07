package com.loveunited.tmall_b_backend.service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.loveunited.tmall_b_backend.service.category.CategoryService;
import com.loveunited.tmall_b_backend.service.category.dto.CategoryDTO;

/**
 * @author LiuWenshuo
 * Created on 2022-03-07
 */
@SpringBootTest
public class CategoryServiceTest {
    @Autowired
    CategoryService categoryService;
    @Test
    void testQueryCategoryMap() {
        final List<CategoryDTO> categoryDTOS = categoryService.queryCategoryMap();
        for (CategoryDTO categoryDTO : categoryDTOS) {
            System.out.println(categoryDTO);
        }
    }
}
