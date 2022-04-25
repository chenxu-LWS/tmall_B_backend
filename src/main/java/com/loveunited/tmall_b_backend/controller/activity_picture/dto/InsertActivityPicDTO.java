package com.loveunited.tmall_b_backend.controller.activity_picture.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author LiuWenshuo
 * Created on 2022-04-25
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class InsertActivityPicDTO {
    Integer activityId;
    String pictureBase64;
}
