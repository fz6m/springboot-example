package com.fz6m.request;

import lombok.Data;

@Data
public class StaffRequest {
    /**
     * 帐号
     */
    private String username;

    /**
     * 姓名
     */
    private String name;
}
