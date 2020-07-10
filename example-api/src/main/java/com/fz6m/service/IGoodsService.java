package com.fz6m.service;

import com.fz6m.base.Result;
import com.fz6m.entity.Goods;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fz6m.request.GoodsRequest;

import java.util.List;

/**
 * 商品信息表 服务类
 */
public interface IGoodsService extends IService<Goods> {

    /**
     * 通过供应商id查询商品信息
     */
    List<Goods> selectBySupplierId(int supplierId);


    Result search(long page, long size, GoodsRequest request);

    /**
     * 查询商品详情及供应商名称
     */
    Result findById(int id);

    Result update(int id, Goods goods);
}
