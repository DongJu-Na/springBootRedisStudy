package com.spring.redis.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import lombok.Builder;
import lombok.NoArgsConstructor;

/*
 * @deprecated 저장되는 값이 일정하지 않아 사용 x
 */
@lombok.Data
@Builder
@RedisHash("data")
@NoArgsConstructor
@Deprecated
public class Data implements Serializable {
	
	private static final long serialVersionUID = -5277617897410631986L;
	
  @Id
	private String key;
	private String Value;
	
	public Data(String key, String value) {
		this.key = key;
		this.Value = value;
	}
	
  public Map<String, Object> toMap() {
    Map<String, Object> map = new HashMap<>();
    map.put("key", this.key);
    map.put("value", this.Value);
    return map;
}

}
