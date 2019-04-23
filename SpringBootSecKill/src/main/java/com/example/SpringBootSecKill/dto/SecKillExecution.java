package com.example.SpringBootSecKill.dto;

import com.example.SpringBootSecKill.entity.SecKillOrder;
import com.example.SpringBootSecKill.enums.SecKillStatEnum;

/**
 * 封装执行秒杀后的结果
 * @author Administrator
 *
 */
public class SecKillExecution {

	private long secKillID;
	
	// 秒杀执行结果状态
	private int state;
	
	// 状态表示
	private String stateInfo;
	
	// 秒杀成功的订单对象
	private SecKillOrder secKillOrder;
	
	public SecKillExecution(long secKillID, SecKillStatEnum secKillStatEnum, SecKillOrder secKillOrder) {
		this.secKillID = secKillID;
		this.state = secKillStatEnum.getState();
		this.stateInfo = secKillStatEnum.getStateInfo();
		this.secKillOrder = secKillOrder;
	}
	
	public SecKillExecution(long secKillID, SecKillStatEnum secKillStatEnum) {
		this.secKillID = secKillID;
		this.state = secKillStatEnum.getState();
		this.stateInfo = secKillStatEnum.getStateInfo();
	}

	public long getSecKillID() {
		return secKillID;
	}

	public void setSecKillID(long secKillID) {
		this.secKillID = secKillID;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getStateInfo() {
		return stateInfo;
	}

	public void setStateInfo(String stateInfo) {
		this.stateInfo = stateInfo;
	}

	public SecKillOrder getSecKillOrder() {
		return secKillOrder;
	}

	public void setSecKillOrder(SecKillOrder secKillOrder) {
		this.secKillOrder = secKillOrder;
	}
	
    @Override
    public String toString() {
        return "SeckillExecution{" +
                "seckillId=" + secKillID +
                ", state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", seckillOrder=" + secKillOrder +
                '}';
    }
}
