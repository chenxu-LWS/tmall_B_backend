package com.loveunited.tmall_b_backend.controller.login;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loveunited.tmall_b_backend.common.ReturnListObject;
import com.loveunited.tmall_b_backend.common.ReturnObject;
import com.loveunited.tmall_b_backend.common.constants.ErrInfo;
import com.loveunited.tmall_b_backend.controller.login.dto.UserDTO;
import com.loveunited.tmall_b_backend.entity.User;
import com.loveunited.tmall_b_backend.service.user.UserService;

/**
 * @author LiuWenshuo
 * Created on 2022-02-01
 */
@Controller
@RequestMapping("/api/user")
public class UserController {
    @Autowired
    UserService userService;

    @RequestMapping("/queryUserById")
    @ResponseBody
    public ReturnObject queryUserById(Integer userId) {
        if (userId == null) {
            return new ReturnObject(false, null,
                    ErrInfo.PARAMETER_ERROR.getCode(), ErrInfo.PARAMETER_ERROR.getMessage());
        }
        User user = userService.queryUserById(userId);
        return new ReturnObject(true, user, 0 );
    }

    @RequestMapping("/queryUserList")
    @ResponseBody
    public ReturnListObject queryUserList() {
        List<User> user = userService.queryUserList();
        return new ReturnListObject(true, new ArrayList<>(user), 0);
    }

    @PostMapping(value = "/insertUser")
    @ResponseBody
    public Object insertUser(@RequestBody UserDTO userDTO) {
        userService.insertUser(userDTO.getName(), userDTO.getPassword());
        return new ReturnObject(true, null, 0);
    }
}
