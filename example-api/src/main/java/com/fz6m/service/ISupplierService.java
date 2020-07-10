package com.fz6m.service;

import com.fz6m.base.Result;
import com.fz6m.entity.Supplier;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fz6m.request.SupplierRequest;

/**
 * 供应商信息表 服务类
 */
public interface ISupplierService extends IService<Supplier> {

    /**
     * 分页条件查询
     */
    Result search(long page, long size, SupplierRequest request);

    /**
     * 删除供应商
     */
    Result deleteById(int id);


    /**
     * 更新数据
     */
    Result update(int id, Supplier supplier);


}
