package com.loveunited.tmall_b_backend.mapper;

import org.apache.ibatis.annotations.Mapper;
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

    public Integer insertBackOrderInfo(BackOrderInfo backOrderInfo);
}
