package com.fz6m.base;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResultEnum {

    SUCCESS(2000, "成功"),
    ERROR(999, "失败");

    private Integer code;
    private String desc;

}
