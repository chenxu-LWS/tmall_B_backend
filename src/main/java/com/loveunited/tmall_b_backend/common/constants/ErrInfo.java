package com.loveunited.tmall_b_backend.common.constants;

import lombok.Getter;

/**
 * @author LiuWenshuo
 * Created on 2022-02-07
 */
@Getter
public enum ErrInfo {
    // 通用错误码
    PARAMETER_ERROR(1000, "参数不合法"),
    // 登录相关
    LOGIN_ERR_NAME_NOT_EXISTS(1001, "用户名不存在或密码错误，请重试"),
    REGISTER_ERR_NAME_EXISTS(1002, "该用户名已被占用"),

    // 品类相关
    PARENT_CATEGORY_NOT_EXISTS(1003, "父品类ID不存在"),
    INSERT_CATEGORY_NAME_EXISTS(1004, "品类名已存在"),
    DELETE_CATEGORY_FAILED(1005, "删除失败，请先删除其所有子品类"),
    CATEGORY_ID_NOT_EXISTS(1006, "当前品类ID不存在"),

    // 品牌相关
    DELETE_BRAND_FAILED(1007, "当前品牌ID不存在"),
    INSERT_BRAND_NAME_EXISTS(1008, "待新增的品牌名已存在");

    Integer code;
    String message;

    ErrInfo(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
