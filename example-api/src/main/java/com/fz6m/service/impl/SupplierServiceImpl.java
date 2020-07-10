package com.fz6m.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fz6m.base.Result;
import com.fz6m.entity.Goods;
import com.fz6m.entity.Supplier;
import com.fz6m.mapper.SupplierMapper;
import com.fz6m.request.SupplierRequest;
import com.fz6m.service.IGoodsService;
import com.fz6m.service.ISupplierService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 供应商信息表 服务实现类
 */
@Service
public class SupplierServiceImpl extends ServiceImpl<SupplierMapper, Supplier> implements ISupplierService {

    @Override
    public Result search(long page, long size, SupplierRequest request) {
        QueryWrapper<Supplier> query = new QueryWrapper<>();
        if(request != null) {
            if(StringUtils.isNotBlank(request.getName())) {
                query.like("name", request.getName());
            }
            if(StringUtils.isNotBlank(request.getLinkman())) {
                query.like("linkman", request.getLinkman());
            }
            if(StringUtils.isNotBlank(request.getMobile())) {
                query.like("mobile", request.getMobile());
            }
        }

        // 分页查询供应商
        IPage<Supplier> data = baseMapper
                .selectPage(new Page<Supplier>(page, size), query);
        return Result.ok(data);
    }

    @Autowired
    private IGoodsService goodsService;

    @Override
    public Result deleteById(int id) {
        // 1. 通过供应商id查询是否被商品引用，
        List<Goods> goodsList = goodsService.selectBySupplierId(id);
        // 2. 如果被商品引用，则不让删除供应商
        if(CollectionUtils.isNotEmpty(goodsList)) {
            return Result.error("该供应商被商品引用，不允许删除");
        }

        // 3. 如果没有被引用，直接删除
        int i = baseMapper.deleteById(id);
        if(i < 1) {
            return Result.error("删除供应商失败");
        }

        return Result.ok();
    }

    @Override
    public Result update(int id, Supplier supplier) {
        if(supplier.getId() == null) {
            supplier.setId(id);
        }
        // 更新操作
        int i = baseMapper.updateById(supplier);

        if(i < 1) {
            return Result.error("修改供应商信息失败");
        }
        return Result.ok();
    }

}
