package com.fz6m.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fz6m.base.Result;
import com.fz6m.entity.Member;
import com.fz6m.mapper.MemberMapper;
import com.fz6m.request.MemberRequest;
import com.fz6m.service.IMemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;

/**
 * 会员信息表 服务实现类
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements IMemberService {

    @Override
    public Result search(long page, long size, MemberRequest request) {
        QueryWrapper<Member> query = new QueryWrapper<>();
        if(request != null) {
            if(StringUtils.isNotBlank(request.getName())) {
                query.like("name", request.getName());
            }
            if(StringUtils.isNotBlank(request.getCardNum())) {
                query.like("card_num", request.getCardNum());
            }
            if(request.getBirthday() != null) {
                query.eq("birthday", request.getBirthday());
            }
            if(StringUtils.isNotBlank(request.getPayType())) {
                query.eq("pay_type", request.getPayType());
            }
        }

        // 封装分页对象
        IPage<Member> p = new Page<>(page ,size);
        IPage<Member> data = baseMapper.selectPage(p, query);

        return Result.ok(data);
    }

    @Override
    public Result update(int id, Member member) {
        if(member.getId() == null) {
            member.setId(id);
        }

        int i = baseMapper.updateById(member);
        if(i < 1){
            return Result.error("修改会员信息失败");
        }

        return Result.ok();
    }

}
