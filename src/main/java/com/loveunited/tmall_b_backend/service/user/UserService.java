package com.loveunited.tmall_b_backend.service.user;

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

    public User queryUserById(String id) {
        return userMapper.queryUserById(id);
    }

    public List<User> queryUserList() {
        return userMapper.queryUserList();
    }

    public Integer insertUser(String name, String password) {
        return userMapper.insertUser(name, password);
    }
}
