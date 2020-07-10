package com.fz6m.entity;

import java.math.BigDecimal;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.util.Date;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 会员信息表
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("tb_member")
public class Member implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 会员卡号
     */
    private String cardNum;

    /**
     * 会员姓名
     */
    private String name;

    /**
     * 会员生日
     */
    private Date birthday;

    /**
     * 手机号码
     */
    private String phone;

    /**
     * 开卡金额
     */
    private Integer integral;

    /**
     * 可用积分
     */
    private BigDecimal money;

    /**
     * 支付类型（'1'现金, '2'微信, '3'支付宝, '4'银行卡）
     */
    private String payType;

    /**
     * 会员地址
     */
    private String address;


}
