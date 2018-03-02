package com.tamguo.dao.redis;

public class RedisServerNodeBean {
	private String ip;
	private int port;
	private boolean needAuth;
	private String auth;

	public RedisServerNodeBean(String ip, int port, boolean needAuth, String auth) {
		this.ip = ip;
		this.port = port;
		this.needAuth = needAuth;
		this.auth = auth;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public boolean isNeedAuth() {
		return needAuth;
	}

	public void setNeedAuth(boolean needAuth) {
		this.needAuth = needAuth;
	}

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	@Override
	public String toString() {
		return "RedisServer [ip=" + ip + ", port=" + port + ", needAuth=" + needAuth + ", auth=" + auth + "]";
	}

}
