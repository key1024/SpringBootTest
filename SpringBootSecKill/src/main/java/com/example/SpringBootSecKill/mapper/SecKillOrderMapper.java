package com.example.SpringBootSecKill.mapper;

import java.math.BigDecimal;

import org.apache.ibatis.annotations.Mapper;

import com.example.SpringBootSecKill.entity.SecKillOrder;

import io.lettuce.core.dynamic.annotation.Param;

@Mapper
public interface SecKillOrderMapper {

	/**
	 * 插入购买订单明细
	 * 
	 * @param secKillID 秒杀到的商品ID
	 * @param money     秒杀的金额
	 * @param userPhone 秒杀的用户
	 * @return 返回该sql更新的记录数，如果>=1则更新成功
	 */
	int insertOrder(@Param("secKillID")long secKillID, @Param("money")BigDecimal money, @Param("userPhone")long userPhone);
	
	/**
	 * 根据秒杀商品ID查询订单明细数据并得到对应秒杀商品的数据，因为我们再SeckillOrder中已经定义了一个Seckill的属性
	 * 
     * @param seckillId
     * @param userPhone
     * @return
	 */
	SecKillOrder findById(@Param("secKillID")long secKillID, @Param("userPhone")long userPhone);
}
