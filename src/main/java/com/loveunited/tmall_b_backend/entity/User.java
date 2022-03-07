package com.loveunited.tmall_b_backend.entity;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author LiuWenshuo
 * Created on 2022-02-01
 */
@Getter
@Setter
@ToString
public class User {
    private String id;
    private String name;
    private String password;
}
