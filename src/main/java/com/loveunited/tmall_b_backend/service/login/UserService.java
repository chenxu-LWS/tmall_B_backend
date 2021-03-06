package com.loveunited.tmall_b_backend.service.login;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loveunited.tmall_b_backend.entity.User;
import com.loveunited.tmall_b_backend.mapper.UserMapper;

/**
 * @author LiuWenshuo
 * Created on 2022-03-06
 */
@Service
public class UserService {
    @Autowired
    UserMapper userMapper;

    public User queryUserById(Integer id) {
        return userMapper.queryUserById(id);
    }

    public List<User> queryUserList() {
        return userMapper.queryUserList();
    }
}
