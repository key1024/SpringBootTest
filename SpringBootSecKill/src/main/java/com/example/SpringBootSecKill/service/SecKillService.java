package com.example.SpringBootSecKill.service;

import java.math.BigDecimal;
import java.util.List;

import com.example.SpringBootSecKill.dto.Exposer;
import com.example.SpringBootSecKill.dto.SecKillExecution;
import com.example.SpringBootSecKill.entity.SecKill;
import com.example.SpringBootSecKill.exception.RepeatKillException;
import com.example.SpringBootSecKill.exception.SecKillCloseException;
import com.example.SpringBootSecKill.exception.SecKillException;

/**
 * 业务接口：应该站在使用者的角度设计，比如：
 * 1.定义方法的颗粒度要细
 * 2.方法的参数要明确且简练，不建议使用map这类类型让使用者封装一堆参数传递进来
 * 3.方法的return类型，除了要明确返回值的类型，还应明确该方法可能抛出的异常
 * 
 * @author Administrator
 *
 */
public interface SecKillService {
	
	/**
	 * 获取所有秒杀商品的列表
	 * 
	 * @return
	 */
	List<SecKill> findAll();
	
	/**
	 * 获取某一条商品的秒杀信息
	 * 
	 * @param secKillID
	 * @return
	 */
	SecKill findById(long secKillID);
	
	
	/**
	 * 秒杀开始时输出暴露秒杀的地址
	 * 否则输出系统时间和秒杀时间
	 * 
	 * @param seckillID
	 * @return
	 */
	Exposer exportSecKillUrl(long secKillID);
	
	
	/**
	 * 执行秒杀的操作
	 * @param secKillID
	 * @param money
	 * @param userPhone
	 * @param md5
	 * @return
	 */
	SecKillExecution executeSecKill(long secKillID, BigDecimal money, long userPhone, String md5)
			throws SecKillException, RepeatKillException, SecKillCloseException;
}
