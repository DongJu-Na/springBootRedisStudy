package com.spring.redis.config;

import java.util.Optional;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import redis.embedded.RedisServer;

/**
  * @FileName : EmbeddedRedisConfig.java
  * @Project : redis
  * @Date : 2023. 4. 11. 
  * @작성자 : ndj
  * @변경이력 :
  * @프로그램 설명 : 내장 Redis Config
  */
@Slf4j
@RequiredArgsConstructor
@Profile("local")
@Configuration
public class EmbeddedRedisConfig implements InitializingBean , DisposableBean {
	
	private RedisServer redisServer;
	
	@Value("${redis.port}")
	private int port;
	
	@Override
	public void destroy() throws Exception {
		Optional.ofNullable(redisServer).ifPresent(RedisServer::stop);
		log.info("EmbeddedRedisServer Stop > " + port);
	}
	
	@Override
	public void afterPropertiesSet() throws Exception {
	  redisServer = new RedisServer(port);
    redisServer.start();
    log.info("EmbeddedRedisServer Start > " + port);
	} 
	
		
}
