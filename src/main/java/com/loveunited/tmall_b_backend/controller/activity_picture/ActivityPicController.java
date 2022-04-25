package com.loveunited.tmall_b_backend.controller.activity_picture;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loveunited.tmall_b_backend.common.ReturnObject;
import com.loveunited.tmall_b_backend.common.constants.ErrInfo;
import com.loveunited.tmall_b_backend.common.exception.BizException;
import com.loveunited.tmall_b_backend.controller.activity_picture.dto.InsertActivityPicDTO;
import com.loveunited.tmall_b_backend.entity.ActivityPicture;
import com.loveunited.tmall_b_backend.service.activity_picture.ActivityPictureService;

/**
 * @author LiuWenshuo
 * Created on 2022-04-25
 */
@Controller
@RequestMapping("/api/activitypic")
public class ActivityPicController {
    @Autowired
    ActivityPictureService pictureService;

    @PostMapping("/insertActivityPic")
    @ResponseBody
    public ReturnObject insertActivityPic(@RequestBody InsertActivityPicDTO dto) {
        if (dto.getActivityId() == null || dto.getPictureBase64() == null) {
            return new ReturnObject(ErrInfo.PARAMETER_ERROR);
        }
        try {
            String picObjectName = "activity-" + dto.getActivityId() + ".txt";
            final ActivityPicture activityPicture = new ActivityPicture();
            activityPicture.setActivityID(dto.getActivityId());
            activityPicture.setPictureObj(picObjectName);
            final Integer res = pictureService.insertPic(activityPicture, dto.getPictureBase64());
            return new ReturnObject(true, res, 0);
        } catch (BizException e) {
            return new ReturnObject(e);
        }
    }
}
