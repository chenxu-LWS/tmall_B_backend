package com.loveunited.tmall_b_backend.controller.commodity.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author LiuWenshuo
 * Created on 2022-03-09
 */
@Getter
@Setter
@ToString
public class UpdateCommodityPropDTO {
    private Integer id;
    private String newProps;
}
