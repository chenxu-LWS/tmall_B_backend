package com.loveunited.tmall_b_backend.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loveunited.tmall_b_backend.common.constants.ErrInfo;
import com.loveunited.tmall_b_backend.common.exception.BizException;
import com.loveunited.tmall_b_backend.entity.User;
import com.loveunited.tmall_b_backend.mapper.UserMapper;

/**
 * @author LiuWenshuo
 * Created on 2022-02-07
 */
@Service
public class LoginService {
    @Autowired
    UserMapper userMapper;

    public Integer register(String name, String password) {
        // 判断是否有重名的
        if (userMapper.queryUserByName(name) != null) {
            throw new BizException(ErrInfo.REGISTER_ERR_NAME_EXISTS);
        }
        User user = new User(null, name, password);
        userMapper.insertUser(user);
        return user.getId();
    }

    public Integer login(String name, String password) {
        if (userMapper.queryUserByNameAndPass(name, password) == null) {
            throw new BizException(ErrInfo.LOGIN_ERR_NAME_NOT_EXISTS);
        }
        return 0;
    }
}
