package com.example.SpringBootTest1.controller.admin;

import com.example.SpringBootTest1.entity.Goods;
import com.example.SpringBootTest1.entity.PageBean;
import com.example.SpringBootTest1.entity.Result;
import com.example.SpringBootTest1.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Autowired
    private GoodsService goodsService;

    /**
     * 分页查询
     *
     * @param goods 查询条件
     * @param pageCode 当前页
     * @param pageSize 每页显示记录数
     */
    @RequestMapping("/findByConPage")
    public PageBean findByConPage(Goods goods,
                                  @RequestParam(value = "pageCode", required = false)int pageCode,
                                  @RequestParam(value = "pageSize", required = false)int pageSize) {
        return goodsService.findByPage(goods, pageCode, pageSize);
    }

    /**
     * 新增商品
     */
    @RequestMapping("/create")
    public Result create(@RequestBody Goods goods) {
        try {
            goodsService.create(goods);
            return new Result(true, "创建成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }

    /**
     * 更新数据
     */
    @RequestMapping("/update")
    public Result update(@RequestBody Goods goods) {
        try {
            goodsService.update(goods);
            return new Result(true, "更新数据成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }

    /**
     * 批量删除
     */
    @RequestMapping("/delete")
    public Result delete(@RequestBody Long... ids) {
        try {
            goodsService.delete(ids);
            return new Result(true, "删除数据成功");
        } catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "发生未知错误");
        }
    }

    /**
     * 根据id查询
     */
    @RequestMapping("/findById")
    public List<Goods> fingById(@RequestParam(value = "id", required = false)Long id) {
        return goodsService.findById(id);
    }
}
