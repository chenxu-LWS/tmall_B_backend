package com.loveunited.tmall_b_backend.service.stock.dto;

import java.util.Date;

import com.loveunited.tmall_b_backend.entity.StockDetail;
import com.loveunited.tmall_b_backend.service.commodity.dto.CommodityDTO;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * @author LiuWenshuo
 * Created on 2022-03-10
 */
@Getter
@Setter
@ToString
@NoArgsConstructor
public class StockDetailDTO {
    private Integer id;
    private CommodityDTO commodityDTO;
    private Double stockPrice;
    private Integer stockNum;
    private Date stockTime;

    public StockDetailDTO(StockDetail detail) {
        this.id = detail.getId();
        this.stockPrice = detail.getStockPrice();
        this.stockNum = detail.getStockNum();
        this.stockTime = detail.getStockTime();
    }
}
