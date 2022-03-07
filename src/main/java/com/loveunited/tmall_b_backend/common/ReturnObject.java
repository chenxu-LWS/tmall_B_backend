package com.loveunited.tmall_b_backend.common;

import lombok.Data;

/**
 * @author LiuWenshuo
 * Created on 2022-02-07
 */
@Data
public class ReturnObject {
    Boolean success;
    Object result;
    Integer code;
    String message;

    public ReturnObject() {

    }

    public ReturnObject(Boolean success, Object result, Integer code) {
        this.success = success;
        this.result = result;
        this.code = code;
    }

    public ReturnObject(Boolean success, Object result, Integer code, String message) {
        this.success = success;
        this.result = result;
        this.code = code;
        this.message = message;
    }
}
