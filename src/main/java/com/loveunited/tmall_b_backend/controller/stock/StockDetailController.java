package com.loveunited.tmall_b_backend.controller.stock;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.loveunited.tmall_b_backend.common.ReturnObject;
import com.loveunited.tmall_b_backend.common.ReturnPageObject;
import com.loveunited.tmall_b_backend.common.constants.ErrInfo;
import com.loveunited.tmall_b_backend.common.exception.BizException;
import com.loveunited.tmall_b_backend.common.page.PageBean;
import com.loveunited.tmall_b_backend.controller.PageBaseDTO;
import com.loveunited.tmall_b_backend.controller.stock.dto.InsertStockDetailDTO;
import com.loveunited.tmall_b_backend.controller.stock.dto.QueryStockDetailByCommodityIdDTO;
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

    @PostMapping("/queryAllByPage")
    @ResponseBody
    public ReturnPageObject<StockDetailDTO> queryStockDetailAllByPage(@RequestBody PageBaseDTO dto) {
        if (dto == null || dto.hasNull()) {
            return new ReturnPageObject<>(ErrInfo.PARAMETER_ERROR);
        }
        return new ReturnPageObject<>(true, stockDetailService
                .queryStockDetailAllByPage(dto.getPageNo(), dto.getPageSize()), 0);
    }

    @RequestMapping("/queryById")
    @ResponseBody
    public ReturnObject queryStockDetailById(Integer id) {
        if (id == null || id < 0) {
            return new ReturnObject(ErrInfo.PARAMETER_ERROR);
        }
        return new ReturnObject(true, stockDetailService.queryStockDetailById(id), 0);
    }

    @PostMapping("/queryByCommodityIdByPage")
    @ResponseBody
    public ReturnPageObject<StockDetailDTO> queryStockDetailByCommodityIdByPage(@RequestBody QueryStockDetailByCommodityIdDTO dto) {
        if (dto == null || dto.hasNull() || dto.getCommodityId() == null || dto.getCommodityId() < 0) {
            return new ReturnPageObject<>(ErrInfo.PARAMETER_ERROR);
        }
        try {
            final PageBean<StockDetailDTO> stockDetailDTOPageBean =
                    stockDetailService.queryStockDetailByCommodityIdByPage(dto.getCommodityId(), dto.getPageNo(), dto.getPageSize());
            return new ReturnPageObject<>(true, stockDetailDTOPageBean, 0);
        } catch (BizException e) {
            return new ReturnPageObject<>(e);
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
