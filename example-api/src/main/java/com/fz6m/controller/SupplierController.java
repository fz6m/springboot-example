package com.fz6m.controller;


import com.fz6m.base.Result;
import com.fz6m.entity.Supplier;
import com.fz6m.request.SupplierRequest;
import com.fz6m.service.ISupplierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 供应商信息表 前端控制器
 */
@RestController
@RequestMapping("/supplier")
public class SupplierController {


    @Autowired
    private ISupplierService supplierService;

    /**
     * 分页条件查询供应商列表
     */
    @PostMapping("/list/search/{page}/{size}")
    public Result search(@PathVariable("page") int page,
                         @PathVariable("size") int size,
                         @RequestBody(required = false) SupplierRequest request) {
        return supplierService.search(page, size, request);
    }

    /**
     * 新增供应商
     */
    @PostMapping
    public Result add(@RequestBody Supplier supplier) {
        boolean b = supplierService.save(supplier);
        if(b) {
            return Result.ok();
        }
        return Result.error("新增供应商信息失败");
    }

    /**
     * 通过供应商id删除数据
     */
    @DeleteMapping("/{id}") // /supplier/{id}
    public Result delete(@PathVariable("id") int id) {
        return supplierService.deleteById(id);
    }

    @GetMapping("/{id}")
    public Result get(@PathVariable("id") int id) {
        Supplier supplier = supplierService.getById(id);
        return Result.ok(supplier);
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable("id") int id,
                         @RequestBody Supplier supplier) {
        return supplierService.update(id, supplier);
    }

}
