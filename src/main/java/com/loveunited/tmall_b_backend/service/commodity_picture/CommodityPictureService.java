package com.loveunited.tmall_b_backend.service.commodity_picture;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.OSSObject;
import com.loveunited.tmall_b_backend.common.constants.ErrInfo;
import com.loveunited.tmall_b_backend.common.exception.BizException;
import com.loveunited.tmall_b_backend.common.oss.OssClient;
import com.loveunited.tmall_b_backend.entity.CommodityPicture;
import com.loveunited.tmall_b_backend.mapper.CommodityMapper;
import com.loveunited.tmall_b_backend.mapper.CommodityPictureMapper;

/**
 * @author LiuWenshuo
 * Created on 2022-03-09
 */
@Service
public class CommodityPictureService {
    @Autowired
    CommodityPictureMapper pictureMapper;
    @Autowired
    CommodityMapper commodityMapper;

    @Autowired
    OssClient ossClient;

    private static final String MAIN_BUCKET_NAME = "tmall-commodity-pictures";
    private static final String DETAIL_BUCKET_NAME = "tmall-commodity-details-pictures";


    public Integer insertPic(CommodityPicture picture) {
        pictureMapper.insertPic(picture);
        return picture.getId();
    }

    public List<String> queryMainPicByCommodityId(Integer commodityId) throws BizException{
        if (commodityMapper.queryCommodityById(commodityId) == null) {
            throw new BizException(ErrInfo.COMMODITY_ID_NOT_EXISTS);
        }
        try {
            final List<CommodityPicture> commodityPictures = pictureMapper.queryMainPicByCommodityId(commodityId);
            List<String> pictures = new ArrayList<>();
            for (CommodityPicture commodityPicture : commodityPictures) {
                final String pictureObj = commodityPicture.getPictureObj();
                final OSSObject ossObject = ossClient.getOssClient().getObject(MAIN_BUCKET_NAME, pictureObj);
                BufferedReader reader = new BufferedReader(new InputStreamReader(ossObject.getObjectContent()));
                StringBuilder builder = new StringBuilder();
                while (true) {
                    String line = reader.readLine();
                    if (line == null) {
                        break;
                    }
                    builder.append(line);
                }
                reader.close();
                pictures.add(builder.toString());
            }
            return pictures;
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
            throw new BizException(ErrInfo.OSS_ERROR);
        } catch (Throwable ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
            throw new BizException(ErrInfo.OSS_ERROR);
        }
    }

    public List<String> queryDetailPicByCommodityId(Integer commodityId) {
        if (commodityMapper.queryCommodityById(commodityId) == null) {
            throw new BizException(ErrInfo.COMMODITY_ID_NOT_EXISTS);
        }
        try {
            final List<CommodityPicture> commodityPictures = pictureMapper.queryDetailPicByCommodityId(commodityId);
            List<String> pictures = new ArrayList<>();
            for (CommodityPicture commodityPicture : commodityPictures) {
                final String pictureObj = commodityPicture.getPictureObj();
                final OSSObject ossObject = ossClient.getOssClient().getObject(DETAIL_BUCKET_NAME, pictureObj);
                BufferedReader reader = new BufferedReader(new InputStreamReader(ossObject.getObjectContent()));
                StringBuilder builder = new StringBuilder();
                while (true) {
                    String line = reader.readLine();
                    if (line == null) {
                        break;
                    }
                    builder.append(line);
                }
                reader.close();
                pictures.add(builder.toString());
            }
            return pictures;
        }catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
            throw new BizException(ErrInfo.OSS_ERROR);
        } catch (Throwable ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
            throw new BizException(ErrInfo.OSS_ERROR);
        }
    }
}
