package com.fz6m.controller;


import com.fz6m.base.Result;
import com.fz6m.entity.Goods;
import com.fz6m.request.GoodsRequest;
import com.fz6m.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 商品信息表 前端控制器
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private IGoodsService goodsService;


    @PostMapping("/list/search/{page}/{size}")
    public Result search(@PathVariable("page") long page,
                         @PathVariable("size") long size,
                         @RequestBody(required = false) GoodsRequest request) {
        return goodsService.search(page, size, request);
    }


    @PostMapping // /goods
    public Result add(@RequestBody Goods goods) {
        boolean b = goodsService.save(goods);
        if(b) {
            return Result.ok();
        }
        return Result.error("新增商品信息失败");
    }

    /**
     * 删除操作
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") int id) {
        boolean b = goodsService.removeById(id);
        if(b) {
            return Result.ok();
        }
        return Result.error("删除商品信息失败");
    }

    /**
     * 查询商品详情
     */
    @GetMapping("/{id}")
    public Result get(@PathVariable("id") int id) {
        return goodsService.findById(id);
    }

    /**
     * 更新
     */
    @PutMapping("/{id}")
    public Result update(@PathVariable("id") int id,
                         @RequestBody Goods goods) {
        return goodsService.update(id, goods);
    }

}
