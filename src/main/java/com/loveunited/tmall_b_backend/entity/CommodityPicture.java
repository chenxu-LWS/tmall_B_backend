package com.loveunited.tmall_b_backend.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author LiuWenshuo
 * Created on 2022-03-08
 */
@Getter
@Setter
@ToString
public class CommodityPicture {
    private Integer id;
    private Integer commodityID;// 所属品类ID
    private Integer pictureType;// 图片类型(主图片0,详情页图片1)
    private Integer pictureSequence;// 图片顺序(1、2...)
    private String pictureURL;// OSS地址
}
