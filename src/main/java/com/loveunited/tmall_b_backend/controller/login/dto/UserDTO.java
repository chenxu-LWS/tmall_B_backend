package com.loveunited.tmall_b_backend.controller.login.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author LiuWenshuo
 * Created on 2022-02-07
 */
@Getter
@Setter
@ToString
public class UserDTO implements Serializable {
    private String name;
    private String password;
}
