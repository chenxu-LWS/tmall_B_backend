package com.loveunited.tmall_b_backend.common;

import java.util.List;

import com.loveunited.tmall_b_backend.common.constants.ErrInfo;
import com.loveunited.tmall_b_backend.common.exception.BizException;

import lombok.Data;

/**
 * @author LiuWenshuo
 * Created on 2022-02-07
 */
@Data
public class ReturnListObject {
    Boolean success;
    List<Object> result;
    Integer code;
    String message;

    public ReturnListObject() {
    }

    public ReturnListObject(Boolean success, List<Object> result, Integer code) {
        this.success = success;
        this.result = result;
        this.code = code;
    }

    public ReturnListObject(Boolean success, List<Object> result, Integer code, String message) {
        this.success = success;
        this.result = result;
        this.code = code;
        this.message = message;
    }

    public ReturnListObject(ErrInfo errInfo) {
        this.success = false;
        this.result = null;
        this.code = errInfo.getCode();
        this.message = errInfo.getMessage();
    }

    public ReturnListObject(BizException e) {
        this.success = false;
        this.result = null;
        this.code = e.getCode();
        this.message = e.getMessage();
    }
}
