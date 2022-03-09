package com.loveunited.tmall_b_backend.service.commodity_picture;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loveunited.tmall_b_backend.entity.CommodityPicture;
import com.loveunited.tmall_b_backend.mapper.CommodityPictureMapper;

/**
 * @author LiuWenshuo
 * Created on 2022-03-09
 */
@Service
public class CommodityPictureService {
    @Autowired
    CommodityPictureMapper pictureMapper;

    public Integer insertPic(CommodityPicture picture) {
        return null;
    }

    public List<CommodityPicture> queryMainPicByCommodityId(Integer commodityId) {
        return null;
    }

    public List<CommodityPicture> queryDetailPicByCommodityId(Integer commodityId) {
        return null;
    }
}
