package com.fz6m.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fz6m.base.Result;
import com.fz6m.entity.Staff;
import com.fz6m.mapper.StaffMapper;
import com.fz6m.request.PasswordRequest;
import com.fz6m.request.StaffRequest;
import com.fz6m.service.IStaffService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fz6m.utils.JwtUtil;
import io.jsonwebtoken.Claims;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 员工信息表 服务实现类
 */
@Service
public class StaffServiceImpl extends ServiceImpl<StaffMapper, Staff> implements IStaffService {

    @Override
    public Result search(long page, long size, StaffRequest request) {
        // 封装查询条件
        QueryWrapper<Staff> query = new QueryWrapper<>();
        if(request != null) {
            if(StringUtils.isNotBlank(request.getUsername())) {
                query.like("username", request.getUsername());
            }
            if(StringUtils.isNotBlank(request.getName())) {
                query.like("name", request.getName());
            }
        }
        // 分页查询列表数据
        IPage<Staff> data =
                baseMapper.selectPage(new Page<Staff>(page, size), query);
        return Result.ok(data);
    }

    @Override
    public Result add(Staff staff) {
        if(staff == null || StringUtils.isBlank(staff.getUsername())) {
            return Result.error("用户名不能为空");
        }
        // 1. 校验提交的帐号是否已经存在
        Staff s = getByUsername(staff.getUsername());
        if(s != null){
            return Result.error("用户名已存在");
        }

        // 2. 将提交的密码进行加密
        String password = new BCryptPasswordEncoder().encode(staff.getPassword());
        staff.setPassword(password);
        // 3. 提交数据
        boolean b = this.save(staff);
        if(b) {
            return Result.ok();
        }
        return Result.error("新增员工信息失败");
    }



    public Staff getByUsername(String username) {
        QueryWrapper<Staff> query = new QueryWrapper<>();
        query.eq("username", username);
        return baseMapper.selectOne(query);
    }


    @Override
    public Result update(int id, Staff staff) {
        if(staff.getId() == null) {
            staff.setId(id);
        }

        // 更新操作
        int i = baseMapper.updateById(staff);
        if(i < 1) {
            return Result.error("修改员工信息失败");
        }
        return Result.ok();
    }

    @Override
    public Result checkPassword(PasswordRequest req) {
        if(req == null || StringUtils.isEmpty(req.getPassword())) {
            return Result.error("原密码不能为空");
        }

        // 通过用户id查询用户信息（正确密码）
        Staff staff = baseMapper.selectById(req.getUserId());
        // 判断输入的密码是否正确
        boolean b = new BCryptPasswordEncoder().matches(req.getPassword(), staff.getPassword());
        if(b) {
            return Result.ok();
        }
        return Result.error("原密码错误");
    }

    @Override
    public Result updatePassword(PasswordRequest req) {
        if(req == null || StringUtils.isEmpty(req.getPassword())) {
            return Result.error("新密码不能为空");
        }

        // 将新密码加密
        String password =
                new BCryptPasswordEncoder().encode(req.getPassword());

        // 更新操作
        Staff staff = baseMapper.selectById(req.getUserId());
        staff.setPassword(password);
        baseMapper.updateById(staff);
        return Result.ok();
    }

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Result login(String username, String password) {
        Result error = Result.error("用户名或密码错误");

        if(StringUtils.isBlank(username)
                || StringUtils.isBlank(password)) {
            return error;
        }

        // 1. 通过用户名查询数据
        Staff staff = getByUsername(username);
        // 用户不存在
        if(staff == null) {
            return error;
        }

        // 2. 存在，判断输入的密码和数据库密码是否一致
        boolean b = new BCryptPasswordEncoder().matches(password, staff.getPassword());
        if(!b) {
            return error;
        }

        // 3. 生成 token
        String jwt =
                jwtUtil.createJWT(staff.getId()+"", staff.getUsername(), true);
        // 4. 响应给客户端
        Map<String, String> map = new HashMap<>();
        map.put("token", jwt);
        return Result.ok(map);
    }

    @Override
    public Result getUserInfo(String token) {
        // 解析令牌
        Claims claims = jwtUtil.parseJWT(token);
        // 解析不到，或则用户名为空时，提供获取失败
        if(claims == null ||
                StringUtils.isBlank(claims.getSubject())) {
            return Result.error("获取用户令牌失败");
        }

        // 获取用户名
        String username = claims.getSubject();
        // 通过用户名查询对应用户信息
        Staff staff = getByUsername(username);
        if(staff == null){
            return Result.error("用户不存在");
        }

        // 将密码设置null,为了安全
        staff.setPassword(null);

        return Result.ok(staff);
    }
}
