package com.example.SpringBootTest1.service.impl;

import com.example.SpringBootTest1.entity.Goods;
import com.example.SpringBootTest1.entity.PageBean;
import com.example.SpringBootTest1.mapper.GoodsMapper;
import com.example.SpringBootTest1.service.GoodsService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {

    @Autowired
    private GoodsMapper goodsMapper;

    @Override
    /**
     * 分页查询
     * @param goods 查询条件
     * @param pageCode 当前页
     * @param pageSize 每页的记录数
     */
    public PageBean findByPage(Goods goods, int pageCode, int pageSize) {
        //使用MyBatis分页插件
        PageHelper.startPage(pageCode, pageSize);

        //调用分页查询方法，其实就是查询所有数据，mybatis自动帮我们进行分页计算
        Page<Goods> page = goodsMapper.findByPage(goods);

        return new PageBean(page.getTotal(), page.getResult());
    }

    @Override
    public List<Goods> findAll() {
        return goodsMapper.findAll();
    }

    @Override
    public List<Goods> findById(Long id) {
        return goodsMapper.findById(id);
    }

    @Override
    public void create(Goods goods) {
        goodsMapper.create(goods);
    }

    @Override
    public void delete(Long... ids) {
        for(Long id : ids) {
            goodsMapper.delete(id);
        }
    }

    @Override
    public void update(Goods goods) {
        goodsMapper.update(goods);
    }
}
