package com.loveunited.tmall_b_backend.common.constants;

import lombok.Getter;

/**
 * @author LiuWenshuo
 * Created on 2022-02-07
 */
@Getter
public enum ErrInfo {
    // 登录相关
    LOGIN_ERR_NAME_NOT_EXISTS(1001, "用户名不存在或密码错误，请重试"),
    REGISTER_ERR_NAME_EXISTS(1002, "该用户名已被占用"),

    // 品类相关
    PARENT_CATEGORY_NOT_EXISTS(1003, "父品类ID不存在"),
    INSERT_CATEGORY_NAME_EXISTS(1004, "品类名已存在"),
    DELETE_CATEGORY_FAILED(1005, "删除失败，请先删除其所有子品类"),
    CATEGORY_ID_NOT_EXISTS(1006, "当前品类ID不存在"),
    DELETE_CATEGORY_FAILED_COMMODITY_BIND(1007, "删除品类失败，有商品绑定到该品类"),

    // 品牌相关
    BRAND_ID_NOT_EXISTS(1007, "当前品牌ID不存在"),
    INSERT_BRAND_NAME_EXISTS(1008, "待新增的品牌名已存在"),
    DELETE_BRAND_ERROR_COMMODITY_BIND(1009, "当前品牌下有绑定的商品，无法删除"),
    COMMODITY_INSERT_NOT_AVAILABLE(1010, "品类ID不合法，只能往三级品类插入商品"),

    // 商品相关
    COMMODITY_ID_NOT_EXISTS(1010, "当前商品ID不存在"),
    COMMODITY_ALREADY_ON_SALE(1011, "商品已经上架了"),
    COMMODITY_STATUS_ERROR(1012, "请先上架商品或商品已经被下架"),
    COMMODITY_PROP_FORMAT_ERROR(1013, "商品属性的json格式错误,需要是String-String的map形式"),

    // 订单相关
    ORDERINFO_DETAIL_FORMAT_ERROR(1014, "订单详情格式错误,请检查"),
    ORDERINFO_ID_NOT_EXISTS(1015, "订单ID不存在"),
    ORDERINFO_DETAIL_INVENTORY_INFINITY(1016, "商品库存不足"),
    ORDERINFO_DETAIL_CONTAINS_COMM_NOT_EXISTS(1017, "订单中的商品ID不存在"),

    // 退货单相关
    BACKORDERINFO_ID_NOT_EXISTS(1018, "退货单ID不存在"),

    // 活动相关
    DSL_SYNTAX_ERROR(1019, "活动创建语句语法有误"),
    CATEGORY_LEVEL_NOT_SUPPORTED(1020, "只能对三级品类进行活动发布"),

    // 通用错误码
    PARAMETER_ERROR(10000, "参数不合法"),
    PARAMETER_ERROR_CANNOT_CAST_TO_JSON(10001, "参数无法转换为json,请检查"),
    OSS_ERROR(10002, "oss对象存储出错");

    Integer code;
    String message;

    ErrInfo(Integer code, String message) {
        this.code = code;
        this.message = message;
    }
}
