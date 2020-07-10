package com.fz6m.controller;


import com.fz6m.base.Result;
import com.fz6m.entity.Staff;
import com.fz6m.request.StaffRequest;
import com.fz6m.service.IStaffService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 员工信息表 前端控制器
 */
@RestController
@RequestMapping("/staff")
public class StaffController {

    @Autowired
    private IStaffService staffService;

    /**
     * 分页条件查询
     */
    @PostMapping("/list/search/{page}/{size}")
    public Result search(@PathVariable("page") long page,
                         @PathVariable("size") long size,
                         @RequestBody(required = false) StaffRequest request) {
        return staffService.search(page, size, request);
    }

    /**
     * 新增
     */
    @PostMapping
    public Result add(@RequestBody Staff staff) {
        return staffService.add(staff);
    }

    /**
     * 删除员工 DELETE
     */
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable int id) {
        boolean b = staffService.removeById(id);
        if(b) {
            return Result.ok();
        }

        return Result.error("删除员工信息失败");
    }

    /**
     * 查询员工详情
     */
    @GetMapping("/{id}")
    public Result get(@PathVariable int id) {
        Staff staff = staffService.getById(id);
        return Result.ok(staff);
    }

    /**
     * 修改员工信息
     */
    @PutMapping("/{id}")
    public Result update(@PathVariable int id,
                         @RequestBody Staff staff) {
        return staffService.update(id, staff);
    }

}
