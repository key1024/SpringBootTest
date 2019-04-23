package com.example.SpringBootSecKill.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 秒杀订单表
 * @author Administrator
 *
 */
public class SecKillOrder implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2905148720476738680L;

	private long secKillID; //秒杀到的商品ID
	private BigDecimal money; // 支付金额
	private long userPhone; //秒杀用户的手机号
	
	@DateTimeFormat(pattern="YYYY-MM-DD HH:mm:ss")
	@JsonFormat(pattern="YYYY-MM-DD HH:mm:ss", timezone="UTF-8")
	private Date createTime; //创建时间
	
	private boolean status; //订单状态，-1：无效 0：成功 1：已付款
	private SecKill secKill; //秒杀商品，和订单是一对多的关系
	public long getSecKillID() {
		return secKillID;
	}
	public void setSecKillID(long secKillID) {
		this.secKillID = secKillID;
	}
	public BigDecimal getMoney() {
		return money;
	}
	public void setMoney(BigDecimal money) {
		this.money = money;
	}
	public long getUserPhone() {
		return userPhone;
	}
	public void setUserPhone(long userPhone) {
		this.userPhone = userPhone;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public SecKill getSecKill() {
		return secKill;
	}
	public void setSecKill(SecKill secKill) {
		this.secKill = secKill;
	}
	
	@Override
	public String toString() {
        return "SeckillOrder{" +
                "seckillId=" + secKillID +
                ", money=" + money +
                ", createTime=" + createTime +
                ", status=" + status +
                ", seckill=" + secKill +
                '}';
	}
}
