package com.loveunited.tmall_b_backend.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author LiuWenshuo
 * Created on 2022-03-09
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class StockDetail {
    private Integer id;
    private Integer commodityID;
    private Double stockPrice;
    private Integer stockNum;
}
