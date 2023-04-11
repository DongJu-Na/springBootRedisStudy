package com.spring.redis.config;

import java.time.Duration;
import java.util.Optional;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import io.lettuce.core.ClientOptions;
import io.lettuce.core.SocketOptions;
import io.lettuce.core.TimeoutOptions;
import io.lettuce.core.resource.ClientResources;
import io.lettuce.core.resource.DefaultClientResources;
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
	
	 @Bean
   public LettuceConnectionFactory redisConnectionFactory() {
       RedisStandaloneConfiguration redisConfiguration = new RedisStandaloneConfiguration();
       redisConfiguration.setHostName("localhost");
       redisConfiguration.setPort(port);

       LettuceClientConfiguration clientConfiguration = LettuceClientConfiguration.builder()
               .clientResources(clientResources())
               .clientOptions(ClientOptions.builder()
                       .socketOptions(SocketOptions.builder()
                               .connectTimeout(Duration.ofSeconds(10))
                               .build())
                       .timeoutOptions(TimeoutOptions.enabled(Duration.ofSeconds(10)))
                       .build())
               .build();

       return new LettuceConnectionFactory(redisConfiguration, clientConfiguration);
   }

   @Bean
   public RedisTemplate<?, ?> redisTemplate() {
       RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
       redisTemplate.setConnectionFactory(redisConnectionFactory());

       redisTemplate.setDefaultSerializer(new GenericJackson2JsonRedisSerializer());
       redisTemplate.setKeySerializer(new StringRedisSerializer());
       redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
       redisTemplate.setHashKeySerializer(new StringRedisSerializer());
       redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());

       return redisTemplate;
   }

   @Bean
   public ClientResources clientResources() {
       return DefaultClientResources.create();
   }
	
		
}
