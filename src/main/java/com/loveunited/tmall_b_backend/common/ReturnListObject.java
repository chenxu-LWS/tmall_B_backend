package com.loveunited.tmall_b_backend.common;

import java.util.List;

import lombok.Data;

/**
 * @author LiuWenshuo
 * Created on 2022-02-07
 */
@Data
public class ReturnListObject {
    Boolean success;
    List<Object> result;
    String message;
    Integer code;

    public ReturnListObject() {
    }

    public ReturnListObject(Boolean success, List<Object> result, Integer code) {
        this.success = success;
        this.result = result;
        this.code = code;
    }

    public ReturnListObject(Boolean success, List<Object> result, String message) {
        this.success = success;
        this.result = result;
        this.message = message;
    }
}
