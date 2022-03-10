package com.loveunited.tmall_b_backend.controller.stock.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author LiuWenshuo
 * Created on 2022-03-10
 */
@Getter
@Setter
@ToString
public class InsertStockDetailDTO {
    private Integer commodityID;
    private Double stockPrice;
    private Integer stockNum;
}
