package com.fz6m.controller;

import com.fz6m.base.Result;
import com.fz6m.entity.Staff;
import com.fz6m.request.PasswordRequest;
import com.fz6m.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class AuthenticationController {

    @Autowired
    private IStaffService staffService;

    /**
     * 校验原密码是否正确
     */
    @PostMapping("/pwd")
    public Result checkPwd(@RequestBody PasswordRequest request) {
        return staffService.checkPassword(request);
    }

    /**
     * 提交修改密码
     */
    @PutMapping("/pwd")
    public Result updatePwd(@RequestBody PasswordRequest request) {
        return staffService.updatePassword(request);
    }


    @PostMapping("/login")
    public Result login(@RequestBody Staff staff) {
        return staffService.login(staff.getUsername(), staff.getPassword());
    }

    /**
     * 通过 token 获取用户信息
     */
    @GetMapping("/info")
    public Result getUserInfo(@RequestParam String token) {
        return staffService.getUserInfo(token);
    }


    @PostMapping("/logout")
    public Result logout() {
        return Result.ok();
    }


}
