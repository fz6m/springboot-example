package com.fz6m.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.fz6m.entity.Goods;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fz6m.request.GoodsRequest;
import org.apache.ibatis.annotations.Param;

/**
 * 商品信息表 Mapper 接口
 */
public interface GoodsMapper extends BaseMapper<Goods> {

    /**
     * 分页查询商品列表
     * 1. 第1个参数传递分页对象 Page （此对象封装当前页码，还有每显查询多少条）
     * 2. 查询条件， @Param 取别名
     */
    IPage<Goods> searchPage(IPage<Goods> page, @Param("req") GoodsRequest request);
}
