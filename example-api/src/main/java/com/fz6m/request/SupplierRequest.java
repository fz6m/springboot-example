package com.fz6m.request;

import lombok.Data;

@Data
public class SupplierRequest {

    /**
     * 供应商名称
     */
    private String name;

    /**
     * 联系人
     */
    private String linkman;

    /**
     * 联系电话
     */
    private String mobile;
}
