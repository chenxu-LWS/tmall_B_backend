package com.loveunited.tmall_b_backend.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import com.loveunited.tmall_b_backend.entity.Customer;

/**
 * @author LiuWenshuo
 * Created on 2022-01-30
 */
@Mapper
@Component
public interface CustomerMapper {
    public Customer queryCustomerById(String id);
    public List<Customer> queryCustomerList();
    public Integer insertCustomer(@Param("name") String name, @Param("password") String password);
    public Customer queryCustomerByName(@Param("name") String name);
    public Customer queryCustomerByNameAndPass(@Param("name") String name, @Param("password") String password);
}
