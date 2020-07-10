package com.fz6m.request;

import lombok.Data;

import java.util.Date;

@Data
public class MemberRequest {

    /**
     * 会员卡号
     */
    private String cardNum;

    /**
     * 会员名字
     */
    private String name;

    /**
     * 生日
     */
    private Date birthday;

    /**
     * 支付类型（'1'现金, '2'微信, '3'支付宝, '4'银行卡）
     */
    private String payType;

}
