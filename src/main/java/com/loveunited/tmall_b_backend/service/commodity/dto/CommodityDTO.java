package com.loveunited.tmall_b_backend.service.commodity.dto;

/**
 * @author LiuWenshuo
 * Created on 2022-03-08
 */

import com.loveunited.tmall_b_backend.entity.Brand;
import com.loveunited.tmall_b_backend.entity.Category;
import com.loveunited.tmall_b_backend.entity.Commodity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
@NoArgsConstructor
public class CommodityDTO {
    private Integer id;
    private String name;
    private Category category;// 所属品类ID
    private Brand brand;// 所属品牌ID
    private Double price;// 商品售价
    private String props;// 商品其他属性，json字符串
    private Integer status;// 商品状态,已创建为(0)，已上架为1，已下架为2

    public CommodityDTO(Commodity commodity) {
        this.id = commodity.getId();
        this.name = commodity.getName();
        this.price = commodity.getPrice();
        this.props = commodity.getProps();
        this.status = commodity.getStatus();
    }
}
