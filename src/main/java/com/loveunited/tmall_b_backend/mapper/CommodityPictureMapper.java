package com.loveunited.tmall_b_backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.loveunited.tmall_b_backend.entity.CommodityPicture;

/**
 * @author LiuWenshuo
 * Created on 2022-03-08
 */
@Mapper
@Component
public interface CommodityPictureMapper {
    public List<CommodityPicture> queryMainPicByCommodityId(Integer commodityId);
    public List<CommodityPicture> queryDetailPicByCommodityId(Integer commodityId);
    public Integer insertPic(CommodityPicture picture);
}
