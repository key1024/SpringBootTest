package com.example.SpringBootSecKill.mapper;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.example.SpringBootSecKill.entity.SecKill;

import io.lettuce.core.dynamic.annotation.Param;

@Mapper
public interface SecKillMapper {

	/**
	 * 查询所有秒杀商品的记录信息
	 * 
	 * @return
	 */
	List<SecKill> findAll();
	
	/**
	 * 根据主键查询当前秒杀商品的数据
	 * 
	 * @param id
	 * @return
	 */
	SecKill findById(long id);
	
	/**
	 * 减库存
	 * 对于Mapper映射接口方法中存在多个参数的要加@Param()注解标识字段名称，不然Mybatis不能识别出来哪个字段相互对应
	 * @param secKillID 秒杀商品ID
	 * @param killTime 秒杀时间
	 * 
	 * @return 返回此sql更新的记录数，如果>=1表示更新成功
	 */
	int reduceStock(@Param("seckillID")long secKillID, @Param("killTime")Date killTime);
}
