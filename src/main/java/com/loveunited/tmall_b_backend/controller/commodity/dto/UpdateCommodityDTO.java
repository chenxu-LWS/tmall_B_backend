package com.loveunited.tmall_b_backend.controller.commodity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiuWenshuo
 * Created on 2022-04-10
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateCommodityDTO {
    private Integer id;
    private String name;// 商品名
    private Integer categoryID;// 所属品类ID
    private Integer brandID;// 所属品牌ID
    private Double price;// 商品售价
    private String detail;// 商品详情
}
