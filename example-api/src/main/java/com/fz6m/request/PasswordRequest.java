package com.fz6m.request;

import lombok.Data;

@Data
public class PasswordRequest {
    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 原密码或新密码
     */
    private String password;
}
