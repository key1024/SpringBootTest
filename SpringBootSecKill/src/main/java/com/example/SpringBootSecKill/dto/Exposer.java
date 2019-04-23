package com.example.SpringBootSecKill.dto;

/**
 * 暴露秒杀地址的dto
 * @author Administrator
 *
 */
public class Exposer {

	// 是否开启秒杀
	private boolean exposed;
	
	// 加密措施，避免用户通过抓包拿到秒杀地址
	private String md5;
	
	// ID
	private long secKillID;
	
	// 系统当前时间（毫秒）
	private long now;
	
	// 秒杀开始时间
	private long start;
	
	// 秒杀结束时间
	private long end;
	
	public Exposer(boolean exposed, String md5, long secKillID) {
		this.exposed = exposed;
		this.md5 = md5;
		this.secKillID = secKillID;
	}
	
	public Exposer(boolean exposed, long secKillID, long now, long start, long end) {
		this.exposed = exposed;
		this.secKillID = secKillID;
		this.now = now;
		this.start = start;
		this.end = end;
	}
	
	public Exposer(boolean exposed, long secKillID) {
		this.exposed = exposed;
		this.secKillID = secKillID;
	}

	public boolean isExposed() {
		return exposed;
	}

	public void setExposed(boolean exposed) {
		this.exposed = exposed;
	}

	public String getMd5() {
		return md5;
	}

	public void setMd5(String md5) {
		this.md5 = md5;
	}

	public long getSecKillID() {
		return secKillID;
	}

	public void setSecKillID(long secKillID) {
		this.secKillID = secKillID;
	}

	public long getNow() {
		return now;
	}

	public void setNow(long now) {
		this.now = now;
	}

	public long getStart() {
		return start;
	}

	public void setStart(long start) {
		this.start = start;
	}

	public long getEnd() {
		return end;
	}

	public void setEnd(long end) {
		this.end = end;
	}
	
    @Override
    public String toString() {
        return "Exposer{" +
                "exposed=" + exposed +
                ", md5='" + md5 + '\'' +
                ", seckillId=" + secKillID +
                ", now=" + now +
                ", start=" + start +
                ", end=" + end +
                '}';
    }
}
