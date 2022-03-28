package com.loveunited.tmall_b_backend.controller.activity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiuWenshuo
 * Created on 2022-03-27
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UpdateActivityStatusDTO {
    private Integer id;
    private Integer status;
}
