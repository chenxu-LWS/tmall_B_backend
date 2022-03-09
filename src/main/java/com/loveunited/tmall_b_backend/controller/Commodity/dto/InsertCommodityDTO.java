package com.loveunited.tmall_b_backend.controller.Commodity.dto;

import java.io.Serializable;
import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author LiuWenshuo
 * Created on 2022-03-09
 */
@Getter
@Setter
@ToString
public class InsertCommodityDTO implements Serializable {
    private String name;
    private Integer categoryID;
    private Integer brandID;
    private Double price;
    private String props;
    private String detail;
    private List<String> mainPicBase64;
    private List<String> detailPicBase64;
}
