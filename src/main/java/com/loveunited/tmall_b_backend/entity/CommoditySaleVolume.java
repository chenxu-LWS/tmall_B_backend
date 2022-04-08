package com.loveunited.tmall_b_backend.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiuWenshuo
 * Created on 2022-04-08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommoditySaleVolume {
    private Integer categoryId; // 种类ID
    private Integer saleVolume; // 当前种类ID的销量
}
