package com.loveunited.tmall_b_backend.service.activity_picture;

import java.io.ByteArrayInputStream;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loveunited.tmall_b_backend.common.constants.ErrInfo;
import com.loveunited.tmall_b_backend.common.exception.BizException;
import com.loveunited.tmall_b_backend.common.oss.OssClient;
import com.loveunited.tmall_b_backend.entity.Activity;
import com.loveunited.tmall_b_backend.entity.ActivityPicture;
import com.loveunited.tmall_b_backend.mapper.ActivityMapper;
import com.loveunited.tmall_b_backend.mapper.ActivityPictureMapper;

/**
 * @author LiuWenshuo
 * Created on 2022-04-25
 */
@Service
public class ActivityPictureService {

    Logger logger = Logger.getLogger(ActivityPictureService.class);

    @Autowired
    ActivityMapper activityMapper;
    @Autowired
    ActivityPictureMapper pictureMapper;
    @Autowired
    OssClient ossClient;

    private static final String ACTIVITY_BUCKET_NAME = "tmall-activity-pictures";

    public Integer insertPic(ActivityPicture picture, String base64Content) {
        final Activity activity = activityMapper.queryActivityById(picture.getActivityID());
        if (activity == null) {
            throw new BizException(ErrInfo.ACTIVITY_ID_NOT_EXISTS);
        }
        final ActivityPicture activityPicture = pictureMapper.queryActivityPicByID(picture.getActivityID());
        if (activityPicture != null) {
            throw new BizException(ErrInfo.ACTIVITY_PICTURE_ALREADY_EXISTS);
        }
        ossClient.getOssClient().putObject(ACTIVITY_BUCKET_NAME, picture.getPictureObj(),
                new ByteArrayInputStream(base64Content.getBytes()));

        return pictureMapper.insertActivityPic(picture);
    }
}
