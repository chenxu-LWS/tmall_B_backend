package com.loveunited.tmall_b_backend.controller.stock.dto;

import com.loveunited.tmall_b_backend.controller.PageBaseDTO;

import lombok.Data;

/**
 * @author LiuWenshuo
 * Created on 2022-03-15
 */
@Data
public class QueryStockDetailByCommodityIdDTO extends PageBaseDTO {
    private Integer commodityId;
}
