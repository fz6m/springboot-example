package com.fz6m.service;

import com.fz6m.base.Result;
import com.fz6m.entity.Member;
import com.baomidou.mybatisplus.extension.service.IService;
import com.fz6m.request.MemberRequest;

/**
 * 会员信息表 服务类
 */
public interface IMemberService extends IService<Member> {

    /**
     * 分布条件查询
     */
    Result search(long page, long size, MemberRequest request);

    /**
     * 更新数据
     */
    Result update(int id, Member member);
}
