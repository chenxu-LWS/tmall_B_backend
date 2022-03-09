package com.loveunited.tmall_b_backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.loveunited.tmall_b_backend.entity.User;

/**
 * @author LiuWenshuo
 * Created on 2022-01-30
 */
@Mapper
@Component
public interface UserMapper {
    public User queryUserById(Integer id);
    public List<User> queryUserList();
    public Integer insertUser(User user);
    public User queryUserByName(@Param("name") String name);
    public User queryUserByNameAndPass(@Param("name") String name, @Param("password") String password);
}
