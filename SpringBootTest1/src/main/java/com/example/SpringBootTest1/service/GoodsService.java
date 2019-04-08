package com.example.SpringBootTest1.service;

import com.example.SpringBootTest1.entity.Goods;
import com.example.SpringBootTest1.entity.PageBean;

public interface GoodsService extends BaseService<Goods> {
    /**
     * 分页查询
     * @param goods 查询条件
     * @param pageCode 当前页
     * @param pageSize 每页的记录数
     */
    PageBean findByPage(Goods goods, int pageCode, int pageSize);
}
