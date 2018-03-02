package com.tamguo.dao.redis;

public class PoolConfigBean {
	private int max_active;
	private int max_idle;
	private long max_wait;

	public PoolConfigBean() {
	}

	public PoolConfigBean(int max_active, int max_idle, long max_wait) {
		super();
		this.max_active = max_active;
		this.max_idle = max_idle;
		this.max_wait = max_wait;
	}

	public int getMax_active() {
		return max_active;
	}

	public void setMax_active(int max_active) {
		this.max_active = max_active;
	}

	public int getMax_idle() {
		return max_idle;
	}

	public void setMax_idle(int max_idle) {
		this.max_idle = max_idle;
	}

	public long getMax_wait() {
		return max_wait;
	}

	public void setMax_wait(long max_wait) {
		this.max_wait = max_wait;
	}

	@Override
	public String toString() {
		return "PoolConfig [max_active=" + max_active + ", max_idle=" + max_idle + ", max_wait=" + max_wait + "]";
	}

}
