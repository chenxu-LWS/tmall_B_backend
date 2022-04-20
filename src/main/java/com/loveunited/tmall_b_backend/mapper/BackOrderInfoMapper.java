package com.loveunited.tmall_b_backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.loveunited.tmall_b_backend.entity.BackOrderInfo;

/**
 * @author LiuWenshuo
 * Created on 2022-03-11
 */
@Mapper
@Component
public interface BackOrderInfoMapper {
    public BackOrderInfo queryBackOrderInfoById(Integer id);
    public List<BackOrderInfo> queryAllByPage(@Param("offset") Integer offset, @Param("pageSize") Integer pageSize);
    public Integer queryAllTotalNum();

    public Integer insertBackOrderInfo(BackOrderInfo backOrderInfo);
}
