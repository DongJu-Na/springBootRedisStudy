package com.spring.redis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
  * @FileName : RedisApplication.java
  * @Project : redis
  * @Date : 2023. 4. 11. 
  * @작성자 : ndj
  * @변경이력 :
  * @프로그램 설명 : Spring Boot 애플리케이션 실행
  */
@SpringBootApplication
@EnableCaching
public class RedisApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedisApplication.class, args);
	}

}
