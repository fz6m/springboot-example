package com.fz6m.service;

import com.fz6m.base.Result;
import com.fz6m.entity.Staff;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fz6m.request.PasswordRequest;
import com.fz6m.request.StaffRequest;

/**
 * 员工信息表 服务类
 */
public interface IStaffService extends IService<Staff> {

    Result search(long page, long size, StaffRequest request);

    Result add(Staff staff);

    Result update(int id, Staff staff);

    /**
     * 校验原密码是否正确
     */
    Result checkPassword(PasswordRequest request);

    /**
     * 更新新密码
     */
    Result updatePassword(PasswordRequest request);

    Result login(String username, String password);

    /**
     * 通过令牌获取用户信息
     */
    Result getUserInfo(String token);

}
