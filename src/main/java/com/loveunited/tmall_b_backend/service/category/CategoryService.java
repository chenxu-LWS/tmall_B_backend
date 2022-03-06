package com.loveunited.tmall_b_backend.service.category;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loveunited.tmall_b_backend.mapper.CategoryMapper;

/**
 * @author LiuWenshuo
 * Created on 2022-03-06
 */
@Service
public class CategoryService {
    @Autowired
    CategoryMapper categoryMapper;

}
