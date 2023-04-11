package com.spring.redis.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import lombok.extern.slf4j.Slf4j;

/**
  * @FileName : RedisConfig.java
  * @Project : redis
  * @Date : 2023. 4. 11. 
  * @작성자 : ndj
  * @변경이력 :
  * @프로그램 설명 : 외장 Redis Config
  */
@Slf4j
@Profile("prod")
@Configuration
public class RedisConfig {

  @Value("${spring.redis.host}")
  private String host;

  @Value("${spring.redis.port}")
  private int port;
  
  @Autowired
  private RedisConnectionFactory redisConnectionFactory;


  @Bean
  public RedisTemplate<?, ?> redisTemplate() {
      RedisTemplate<byte[], byte[]> redisTemplate = new RedisTemplate<>();
      redisTemplate.setConnectionFactory(redisConnectionFactory);
      // StringRedisSerializer를 사용하여 byte[]을 String으로 변환
      redisTemplate.setKeySerializer(new StringRedisSerializer());
      redisTemplate.setValueSerializer(new StringRedisSerializer());
      redisTemplate.setHashKeySerializer(new StringRedisSerializer());
      redisTemplate.setHashValueSerializer(new StringRedisSerializer());
      
      return redisTemplate;
  }
  
  
}
