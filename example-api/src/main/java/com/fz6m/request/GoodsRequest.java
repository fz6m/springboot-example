package com.fz6m.request;

import lombok.Data;

@Data
public class GoodsRequest {
    /**
     * 商品名称
     */
    private String name;

    /**
     * 商品编码
     */
    private String code;

    /**
     * 供应商id
     */
    private Integer supplierId;
}
