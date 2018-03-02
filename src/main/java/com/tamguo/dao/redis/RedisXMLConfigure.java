package com.tamguo.dao.redis;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import com.tamguo.util.XMLConfiguration;

import redis.clients.jedis.JedisPoolConfig;
import redis.clients.jedis.JedisShardInfo;
import redis.clients.jedis.ShardedJedis;
import redis.clients.jedis.ShardedJedisPool;

@Component("redisConfigure")
public class RedisXMLConfigure implements InitializingBean {
	private static final Logger logger = Logger.getLogger(RedisXMLConfigure.class);
	private static String preKey;
	private static Document document = null;
	private ShardedJedisPool shardedJedisPool;

	@Override
	public void afterPropertiesSet() throws Exception {
		XMLConfiguration xmlConfiguration = new XMLConfiguration();
		String REDIS_PATH = "redis.xml";
		InputStream stream = null;
		try {
			stream = this.getClass().getClassLoader().getResourceAsStream(REDIS_PATH);
			if (stream == null) {
				logger.error("load redis.xml failed!!!" + REDIS_PATH);
				throw new RuntimeException("load redis.xml failed");
			}
			logger.info("Redis XML config path:" + REDIS_PATH);
			if (xmlConfiguration.readConfigFile(stream)) {
				document = xmlConfiguration.getDocument();
			} else {
				logger.error("load redis.xml failed!!!");
			}
		} finally {
			if (null != stream)
				stream.close();
		}
		//初始化参数
		initPreKey();
		PoolConfigBean pcb = initPoolConfigBean();
		List<RedisServerNodeBean> rsnbs = initRedisServerNodeBeans();
		//实现shardedJedisPool
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		//no maxActive config
		jedisPoolConfig.setMaxIdle(pcb.getMax_idle());
		jedisPoolConfig.setMaxWaitMillis(pcb.getMax_wait());
		shardedJedisPool = new ShardedJedisPool(jedisPoolConfig,getJedisShardInfo(rsnbs));
		if(shardedJedisPool == null){
			throw new RuntimeException("config redis.xml error");
		}
	}

	/**
	 * 初始化jedis参数
	 */
	private PoolConfigBean initPoolConfigBean() {
		PoolConfigBean poolConfigBean = new PoolConfigBean();
		Element poolElement = (Element) document.getElementsByTagName("pool").item(0);
		int max_active = poolElement.hasAttribute("maxActive") ? Integer.parseInt(poolElement.getAttribute("maxActive")) : -1;
		int max_idle = poolElement.hasAttribute("maxIdle") ? Integer.parseInt(poolElement.getAttribute("maxIdle")) : -1;
		long max_wait = poolElement.hasAttribute("maxWait") ? Long.parseLong(poolElement.getAttribute("maxWait")) : -1;
		poolConfigBean.setMax_active(max_active);
		poolConfigBean.setMax_idle(max_idle);
		poolConfigBean.setMax_wait(max_wait);
		return poolConfigBean;
	}

	/**
	 * 解析配置redis的server列表
	 */
	private List<RedisServerNodeBean> initRedisServerNodeBeans() {
		List<RedisServerNodeBean> redisServers = new ArrayList<RedisServerNodeBean>();
		NodeList serverElements = document.getElementsByTagName("server");
		int serverLen = serverElements.getLength();
		if (serverLen < 1) {
			logger.error("redis.servers.server must have one !");
			return null;
		}
		for (int i = 0; i < serverLen; i++) {
			Element serverElement = (Element) serverElements.item(i);
			String temp_ip = serverElement.hasAttribute("ip") ? serverElement.getAttribute("ip") : null;
			if (temp_ip == null) {
				logger.error("redis.servers.server.ip must be supplied!");
				return null;
			}

			String temp_port = serverElement.hasAttribute("port") ? serverElement.getAttribute("port") : "6379";
			String temp_needAuth = serverElement.hasAttribute("needAuth") ? serverElement.getAttribute("needAuth") : "false";
			String temp_auth = null;
			// need auth
			if ("true".equals(temp_needAuth)) {
				temp_auth = serverElement.hasAttribute("auth") ? serverElement.getAttribute("auth") : null;
				if (null == temp_auth) {
					logger.error("since needAuth is true,auth must be supplied!");
					return null;
				}
			}

			RedisServerNodeBean rs = null;
			try {
				rs = new RedisServerNodeBean(temp_ip, Integer.parseInt(temp_port), Boolean.parseBoolean(temp_needAuth), temp_auth);
			} catch (NumberFormatException e) {
				logger.error("port must be a number!\n" + e.getMessage());
				return null;
			}
			redisServers.add(rs);
		}
		return redisServers;
	}

	/**
	 * 转换自定义配置为JedisShardInfo对象
	 * @param redisServers
	 * @return
	 */
	private List<JedisShardInfo> getJedisShardInfo(List<RedisServerNodeBean> redisServers) {
		if(redisServers == null){
			logger.error("redisServers must not be empty null");
			return null;
		}
		int serverLen = redisServers.size();
		if (serverLen < 1) {
			logger.error("redisServers must not be empty ");
			return null;
		}
		List<JedisShardInfo> servers = new ArrayList<JedisShardInfo>(serverLen);
		for (int i = 0; i < serverLen; i++) {
			RedisServerNodeBean redisServer = redisServers.get(i);
			JedisShardInfo jedisShardInfo = new JedisShardInfo(redisServer.getIp(), redisServer.getPort());
			if (redisServer.isNeedAuth()) {
				jedisShardInfo.setPassword(redisServer.getAuth());
			}
			servers.add(jedisShardInfo);
		}
		return servers;
	}
	
	/*
	 * 初始化redis的key前缀
	 */
	private void initPreKey() {
		Element preKeyElement = (Element) document.getElementsByTagName("preKey").item(0);
		preKey = preKeyElement.hasAttribute("value") ? preKeyElement.getAttribute("value") : "";
	}

	public String getPreKey() {
		return preKey;
	}
	/**
	 * 从jedis连接池获得一个连接
	 * @return
	 */
	public ShardedJedis getConnection() {
		return shardedJedisPool.getResource();
	}
	/**
	 * 把连接放回jedis连接池
	 * @param resource
	 */
	public void closeConnection(ShardedJedis resource) {
		resource.close();
	}

}
