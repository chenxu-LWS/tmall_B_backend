package com.loveunited.tmall_b_backend.controller.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loveunited.tmall_b_backend.common.ReturnObject;
import com.loveunited.tmall_b_backend.common.ReturnPageObject;
import com.loveunited.tmall_b_backend.common.constants.ErrInfo;
import com.loveunited.tmall_b_backend.common.exception.BizException;
import com.loveunited.tmall_b_backend.common.page.PageBean;
import com.loveunited.tmall_b_backend.controller.PageBaseDTO;
import com.loveunited.tmall_b_backend.controller.activity.dto.InsertActivityDTO;
import com.loveunited.tmall_b_backend.controller.activity.dto.UpdateActivityStatusDTO;
import com.loveunited.tmall_b_backend.entity.Activity;
import com.loveunited.tmall_b_backend.service.activity.ActivityService;

/**
 * @author LiuWenshuo
 * Created on 2022-03-27
 */
@Controller
@RequestMapping("/api/activity")
public class ActivityController {
    @Autowired
    ActivityService activityService;

    @ResponseBody
    @PostMapping("/insert")
    @Transactional(rollbackFor=Exception.class)
    public ReturnObject insertActivity(@RequestBody InsertActivityDTO dto) {
        System.out.println(dto);
        if (dto.getActivityName() == null || dto.getDsl() == null) {
            return new ReturnObject(ErrInfo.PARAMETER_ERROR);
        }
        try {
            final Integer result =
                    activityService.insertActivity(dto.getActivityName(), dto.getDsl());
            return new ReturnObject(true, result, 0);
        } catch (BizException e) {
            return new ReturnObject(e);
        }
    }

    @ResponseBody
    @PostMapping("/updateStatus")
    public ReturnObject updateStatus(@RequestBody UpdateActivityStatusDTO dto) {
        if (dto.getId() == null || dto.getStatus() == null) {
            return new ReturnObject(ErrInfo.PARAMETER_ERROR);
        }
        if (dto.getStatus() != 0 && dto.getStatus() != 1) {
            return new ReturnObject(ErrInfo.PARAMETER_ERROR);
        }
        try {
            final Integer result = activityService.updateActivityStatus(dto.getId(), dto.getStatus());
            return new ReturnObject(true, result, 0);
        } catch (BizException e) {
            return new ReturnObject(e);
        }
    }

    @ResponseBody
    @PostMapping("/queryAllByPage")
    public ReturnPageObject<Activity> queryAllByPage(@RequestBody PageBaseDTO dto) {
        if (dto.hasNull()) {
            return new ReturnPageObject<>(ErrInfo.PARAMETER_ERROR);
        }
        try {
            final PageBean<Activity> activityPageBean =
                    activityService.queryAllActivityByPage(dto.getPageNo(), dto.getPageSize());
            return new ReturnPageObject<>(true, activityPageBean, 0);
        } catch (BizException e) {
            return new ReturnPageObject<>(e);
        }
    }

    @ResponseBody
    @PostMapping("/queryOnlineByPage")
    public ReturnPageObject<Activity> queryOnlineByPage(@RequestBody PageBaseDTO dto) {
        if (dto.hasNull()) {
            return new ReturnPageObject<>(ErrInfo.PARAMETER_ERROR);
        }
        try {
            final PageBean<Activity> activityPageBean =
                    activityService.queryOnlineActivityByPage(dto.getPageNo(), dto.getPageSize());
            return new ReturnPageObject<>(true, activityPageBean, 0);
        } catch (BizException e) {
            return new ReturnPageObject<>(e);
        }
    }

    @ResponseBody
    @RequestMapping("/queryById")
    public ReturnObject queryById(Integer id) {
        if (id == null) {
            return new ReturnObject(ErrInfo.PARAMETER_ERROR);
        }
        try {
            final Activity activity =
                    activityService.queryActivityById(id);
            return new ReturnObject(true, activity, 0);
        } catch (BizException e) {
            return new ReturnObject(e);
        }
    }
}
