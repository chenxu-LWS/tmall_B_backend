package com.loveunited.tmall_b_backend.service.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.loveunited.tmall_b_backend.common.constants.ErrInfo;
import com.loveunited.tmall_b_backend.exception.BizException;
import com.loveunited.tmall_b_backend.mapper.CustomerMapper;

/**
 * @author LiuWenshuo
 * Created on 2022-02-07
 */
@Service
public class LoginService {
    @Autowired
    CustomerMapper customerMapper;

    public Integer register(String name, String password) {
        // 判断是否有重名的
        if (customerMapper.queryCustomerByName(name) != null) {
            throw new BizException(ErrInfo.REGISTER_ERR_NAME_EXISTS);
        }
        return customerMapper.insertCustomer(name, password);
    }

    public Integer login(String name, String password) {
        if (customerMapper.queryCustomerByNameAndPass(name, password) == null) {
            throw new BizException(ErrInfo.LOGIN_ERR_NAME_NOT_EXISTS);
        }
        return 0;
    }
}
