package com.loveunited.tmall_b_backend.service.brand;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loveunited.tmall_b_backend.common.constants.ErrInfo;
import com.loveunited.tmall_b_backend.common.exception.BizException;
import com.loveunited.tmall_b_backend.entity.Brand;
import com.loveunited.tmall_b_backend.mapper.BrandMapper;

/**
 * @author LiuWenshuo
 * Created on 2022-03-07
 */
@Service
public class BrandService {
    @Autowired
    BrandMapper brandMapper;

    public Brand queryBrandById(Integer id) {
        return brandMapper.queryBrandById(id);
    }

    public Integer insertBrand(String name) throws BizException{
        final List<Brand> brands = brandMapper.queryAllBrand();
        for (Brand brand : brands) {
            if (name.equals(brand.getName())) {
                throw new BizException(ErrInfo.INSERT_BRAND_NAME_EXISTS);
            }
        }
        Brand brand = new Brand(null, name);
        brandMapper.insertBrand(brand);
        return brand.getId();
    }

    public Integer deleteBrandById(Integer id) throws BizException{
        if (brandMapper.queryBrandById(id) == null) {
            throw new BizException(ErrInfo.BRAND_ID_NOT_EXISTS);
        }
        return brandMapper.deleteBrandById(id);
    }
}
