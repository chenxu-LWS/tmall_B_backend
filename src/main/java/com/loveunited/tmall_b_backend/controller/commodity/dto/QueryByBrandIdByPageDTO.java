package com.loveunited.tmall_b_backend.controller.commodity.dto;

import com.loveunited.tmall_b_backend.controller.PageBaseDTO;

import lombok.Data;

/**
 * @author LiuWenshuo
 * Created on 2022-03-15
 */
@Data
public class QueryByBrandIdByPageDTO extends PageBaseDTO {
    Integer brandId;
}
