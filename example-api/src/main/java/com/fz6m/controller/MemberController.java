package com.fz6m.controller;


import com.fz6m.base.Result;
import com.fz6m.entity.Member;
import com.fz6m.request.MemberRequest;
import com.fz6m.service.IMemberService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * 会员信息表 前端控制器
 */
@RestController
@RequestMapping("/member")
@Slf4j
public class MemberController {


    @Autowired
    private IMemberService memberService;

    @PostMapping("/list/search/{page}/{size}")
    public Result search(@PathVariable("page") long page,
                         @PathVariable("size") long size,
                         @RequestBody MemberRequest request ) {
        log.info("查询会员列表：page={}, size={}", page, size);
        return memberService.search(page, size, request);
    }

    /**
     * 新增会员
     */
    @PostMapping
    public Result add(@RequestBody Member member) {
        boolean b = memberService.save(member);
        if(b) {
            return Result.ok();
        }
        return Result.error("新增会员信息失败");
    }

    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") int id) {
        boolean b = memberService.removeById(id);
        if(b){
            return Result.ok();
        }
        return Result.error("删除会员信息失败");
    }

    @GetMapping("/{id}")
    public Result get(@PathVariable int id) {
        Member member = memberService.getById(id);
        return Result.ok(member);
    }

    @PutMapping("/{id}")
    public Result update(@PathVariable int id,
                         @RequestBody Member member) {
        return memberService.update(id, member);
    }

}
