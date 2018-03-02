package com.tamguo.config.redis;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;

//这个类用配置redis服务器的连接
@EnableRedisHttpSession(maxInactiveIntervalInSeconds= 1800)
public class SessionConfig {

	@Value("${redis.hostname}")
	String HostName;
	@Value("${redis.port}")
	int Port;
	@Value("${redis.password}")
	String password;

	@Bean
	public JedisConnectionFactory connectionFactory() {
		JedisConnectionFactory connection = new JedisConnectionFactory();
		connection.setPort(Port);
		connection.setHostName(HostName);
		connection.setPassword(password);
		return connection;
	}
}
