package com.spring.redis.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Builder;

@lombok.Data
@Builder
@RedisHash("data")
public class Data implements Serializable {
	
	private static final long serialVersionUID = -5277617897410631986L;
	
  @Id
	private String key;
	private String Value;

}
