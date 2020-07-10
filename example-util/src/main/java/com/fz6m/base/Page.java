package com.fz6m.base;

import java.util.List;

public class Page<T> extends com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> {

    public List<T> getRows() {
        // 调用父类的方法将数据传递给rows
        return super.getRecords();
    }

    // 设置为null,响应后就不会有数据
    public List<T> getRecords() {
        return null;
    }

    public Page(long current, long size) {
        super(current, size);
    }

}
