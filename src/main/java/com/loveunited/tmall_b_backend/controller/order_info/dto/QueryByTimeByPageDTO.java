package com.loveunited.tmall_b_backend.controller.order_info.dto;

import com.loveunited.tmall_b_backend.controller.PageBaseDTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiuWenshuo
 * Created on 2022-04-19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class QueryByTimeByPageDTO extends PageBaseDTO {
    Long startTime;
    Long endTime;
}
