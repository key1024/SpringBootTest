package com.example.SpringBootSecKill.service.impl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import com.example.SpringBootSecKill.dto.Exposer;
import com.example.SpringBootSecKill.dto.SecKillExecution;
import com.example.SpringBootSecKill.entity.SecKill;
import com.example.SpringBootSecKill.entity.SecKillOrder;
import com.example.SpringBootSecKill.enums.SecKillStatEnum;
import com.example.SpringBootSecKill.exception.RepeatKillException;
import com.example.SpringBootSecKill.exception.SecKillCloseException;
import com.example.SpringBootSecKill.exception.SecKillException;
import com.example.SpringBootSecKill.mapper.SecKillMapper;
import com.example.SpringBootSecKill.mapper.SecKillOrderMapper;
import com.example.SpringBootSecKill.service.SecKillService;

@Service
public class SecKillServiceImpl implements SecKillService {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	// 设置盐值字符串，随便定义，用于混淆md5值
	private final String salt = "anioda-w0ke1mdam";
	// 设置秒杀redis缓存的key
	private final String key = "secKill";
	
	@Autowired
	private SecKillMapper secKillMapper;
	
	@Autowired
	private SecKillOrderMapper secKillOrderMapper;
	
	@Autowired
	private RedisTemplate redisTemplate;
	
	@Override
	public List<SecKill> findAll(){
//		List<SecKill> secKillList = redisTemplate.boundHashOps(key).values();
//		if(secKillList == null || secKillList.size() == 0) {
//			// 说明缓存中没有秒杀列表数据
//			// 查询数据库中秒杀列表数据，并将列表数据循环放入redis缓存中
//			secKillList = secKillMapper.findAll();
//			for(SecKill secKill : secKillList) {
//				// 将秒杀列表数据依次放入redis缓存中，key：秒杀表的ID值，value：秒杀商品数据
//				redisTemplate.boundHashOps(key).put(secKill.getSecKillID(), secKill);
//				logger.info("findAll -> 从数据库中读取放入缓存中");
//			}
//		} else {
//			logger.info("findAll -> 从缓存中读取");
//		}
		
		return secKillMapper.findAll();
	}
	
    @Override
    public SecKill findById(long secKillID) {
        return secKillMapper.findById(secKillID);
    }
    
    @Override
    public Exposer exportSecKillUrl(long secKillID) {
//    	SecKill secKill = (SecKill)redisTemplate.boundHashOps(key).get(secKillID);
//    	if(secKill == null) {
//    		// 说明redis缓存中没有此key对应的value
//    		// 查询数据库，并将数据放入缓存中
//    		secKill = secKillMapper.findById(secKillID);
//    		if(secKill == null) {
//    			// 说明没有查到
//    			return new Exposer(false, secKillID);
//    		} else {
//				// 查到了，存入redis缓存中
//    			redisTemplate.boundHashOps(key).put(secKill.getSecKillID(), secKill);
//    			logger.info("RedisTemplate -> 从数据库中读取数据并放入到缓存中");
//			}
//    	} else {
//			logger.info("RedisTemplate -> 从缓存中读取");
//		}
    	
    	SecKill secKill = secKillMapper.findById(secKillID);
    	Date startTimeDate = secKill.getStartTime();
    	Date endTime = secKill.getEndTime();
    	//获取系统时间
    	Date nowTime = new Date();
    	if(nowTime.getTime() < startTimeDate.getTime() || nowTime.getTime() > endTime.getTime()) {
    		return new Exposer(false, secKillID, nowTime.getTime(), startTimeDate.getTime(), endTime.getTime());
    	}
    	//转换特定字符串的过程，不可逆算法
    	String md5 = getMD5(secKillID);
    	return new Exposer(true, md5, secKillID);
    }
    
    // 生成MD5值
    private String getMD5(Long secKillID) {
    	String base = secKillID + "/" + salt;
    	String md5 = DigestUtils.md5DigestAsHex(base.getBytes());
    	return md5;
    }
    
    /**
     * Spring默认只对运行期异常进行事务的回滚操作，对于编译异常Spring是不进行回滚的，所以对于需要进行事务控制的方法
     * 尽可能将可能抛出的异常都转换成运行期异常
     */
    @Override
    @Transactional
    public SecKillExecution executeSecKill(long secKillID, BigDecimal money, long userPhone, String md5)
			throws SecKillException, RepeatKillException, SecKillCloseException {
    	if(md5 == null || !md5.equals(getMD5(secKillID))) {
    		throw new SecKillException("seckill data rewrite");
    	}
    	
    	// 执行秒杀逻辑：1.减库存，2.存储秒杀订单
    	Date nowTime = new Date();
    	
    	try {
			// 记录秒杀订单信息
    		int insertCount = secKillOrderMapper.insertOrder(secKillID, money, userPhone);
    		// 唯一性:secKillID,userPhone,保证一个用户只能秒杀一件商品
    		if (insertCount <= 0) {
    			// 重复秒杀
    			throw new RepeatKillException("seckill repeated");
    		} else {
				// 减库存
    			int updateCount = secKillMapper.reduceStock(secKillID, nowTime);
    			if (updateCount <= 0) {
    				// 没有更新记录，秒杀结束
    				throw new SecKillCloseException("seckill is closed");
    			} else {
					// 秒杀成功
    				SecKillOrder secKillOrder = secKillOrderMapper.findById(secKillID, userPhone);
    				// 更新缓存(更新库存数量)
//    				SecKill secKill = (SecKill)redisTemplate.boundHashOps(key).get(secKillID);
//    				secKill.setStockCount(secKill.getSecKillID() - 1);
//    				redisTemplate.boundHashOps(key).put(secKillID, secKill);
    				
    				return new SecKillExecution(secKillID, SecKillStatEnum.SUCCESS, secKillOrder);
				}
			}
		} catch (SecKillCloseException e) {
			// TODO: handle exception
			throw e;
		} catch (RepeatKillException e) {
			// TODO: handle exception
			throw e;
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
			// 所有编译期异常，转换为运行期异常
			throw new SecKillException("seckill inner error: " + e.getMessage());
		}
    }
}
