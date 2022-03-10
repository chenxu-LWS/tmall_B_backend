package com.loveunited.tmall_b_backend.controller.stock;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loveunited.tmall_b_backend.common.ReturnListObject;
import com.loveunited.tmall_b_backend.common.ReturnObject;
import com.loveunited.tmall_b_backend.common.constants.ErrInfo;
import com.loveunited.tmall_b_backend.common.exception.BizException;
import com.loveunited.tmall_b_backend.controller.stock.dto.InsertStockDetailDTO;
import com.loveunited.tmall_b_backend.service.stock.StockDetailService;
import com.loveunited.tmall_b_backend.service.stock.dto.StockDetailDTO;

/**
 * @author LiuWenshuo
 * Created on 2022-03-10
 */
@Controller
@RequestMapping("/api/stock")
public class StockDetailController {
    @Autowired
    StockDetailService stockDetailService;

    @RequestMapping("/queryById")
    @ResponseBody
    public ReturnObject queryStockDetailById(Integer id) {
        if (id == null || id < 0) {
            return new ReturnObject(ErrInfo.PARAMETER_ERROR);
        }
        return new ReturnObject(true, stockDetailService.queryStockDetailById(id), 0);
    }

    @RequestMapping("/queryByCommodityId")
    @ResponseBody
    public ReturnListObject queryStockDetailByCommodityId(Integer commodityId) {
        if (commodityId == null || commodityId < 0) {
            return new ReturnListObject(ErrInfo.PARAMETER_ERROR);
        }
        try {
            final List<StockDetailDTO> stockDetailDTOS = stockDetailService.queryStockDetailByCommodityId(commodityId);
            return new ReturnListObject(true, new ArrayList<>(stockDetailDTOS), 0);
        } catch (BizException e) {
            return new ReturnListObject(e);
        }
    }

    @RequestMapping("/insert")
    @ResponseBody
    public ReturnObject insertStockDetail(@RequestBody InsertStockDetailDTO dto) {
        try {
            final Integer result = stockDetailService.insertStockDetail(dto.getCommodityID(), dto.getStockPrice(),
                    dto.getStockNum());
            return new ReturnObject(true, result, 0);
        } catch (BizException e) {
            return new ReturnObject(e);
        }
    }
}
