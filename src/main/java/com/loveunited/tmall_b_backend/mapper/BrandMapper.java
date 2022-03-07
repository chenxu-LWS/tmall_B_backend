package com.loveunited.tmall_b_backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.loveunited.tmall_b_backend.entity.Brand;

/**
 * @author LiuWenshuo
 * Created on 2022-03-07
 */
@Mapper
@Component
public interface BrandMapper {
    public Brand queryBrandById(Integer id);
    public List<Brand> queryAllBrand();

    public Integer insertBrand(@Param("name") String name);
    public Integer deleteBrandById(@Param("id") Integer id);
}
