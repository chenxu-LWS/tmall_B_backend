package com.loveunited.tmall_b_backend.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import com.loveunited.tmall_b_backend.entity.ActivityPicture;

/**
 * @author LiuWenshuo
 * Created on 2022-04-25
 */
@Mapper
@Component
public interface ActivityPictureMapper {
    public Integer insertActivityPic(ActivityPicture picture);
    public ActivityPicture queryActivityPicByID(Integer activityID);
}
