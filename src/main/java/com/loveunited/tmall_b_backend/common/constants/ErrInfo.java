package com.loveunited.tmall_b_backend.common.constants;

import lombok.Getter;

/**
 * @author LiuWenshuo
 * Created on 2022-02-07
 */
@Getter
public enum ErrInfo {
    LOGIN_ERR_NAME_NOT_EXISTS(1001, "用户名不存在或密码错误，请重试"),
    REGISTER_ERR_NAME_EXISTS(1002, "该用户名已被占用");

    Integer code;
    String message;

    ErrInfo(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
