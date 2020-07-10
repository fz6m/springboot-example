package com.fz6m.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fz6m.base.Page;
import com.fz6m.base.Result;
import com.fz6m.entity.Goods;
import com.fz6m.entity.Supplier;
import com.fz6m.mapper.GoodsMapper;
import com.fz6m.request.GoodsRequest;
import com.fz6m.service.IGoodsService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fz6m.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 商品信息表 服务实现类
 */
@Service
public class GoodsServiceImpl extends ServiceImpl<GoodsMapper, Goods> implements IGoodsService {

    @Override
    public List<Goods> selectBySupplierId(int supplierId) {
        QueryWrapper<Goods> query = new QueryWrapper<>();
        query.eq("supplier_id", supplierId);
        return baseMapper.selectList(query);
    }

    @Override
    public Result search(long page, long size, GoodsRequest request) {
        if(request == null) {
            request = new GoodsRequest();
        }
        // 在 GoodsMapper 已经实现了 searchPage 分页条件查询
        IPage<Goods> data =
                baseMapper.searchPage(new Page<Goods>(page, size), request);
        return Result.ok(data);
    }

    @Autowired
    private ISupplierService supplierService;

    @Override
    public Result findById(int id) {
        // 1. 查询商品详情
        Goods goods = baseMapper.selectById(id);
        // 2. 通过供应商id查询供应商名称
        if(goods != null && goods.getSupplierId() != null) {
            Supplier supplier = supplierService.getById(goods.getSupplierId());
            if( supplier != null) {
                goods.setSupplierName(supplier.getName());
            }
        }

        return Result.ok(goods);
    }

    @Override
    public Result update(int id, Goods goods) {
        if(goods.getId() == null) {
            goods.setId(id);
        }

        int i = baseMapper.updateById(goods);

        if(i < 1) {
            return Result.error("修改商品信息失败");
        }

        return Result.ok();
    }
}
