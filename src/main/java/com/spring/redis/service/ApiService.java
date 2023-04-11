package com.spring.redis.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.spring.redis.model.Data;

import lombok.extern.slf4j.Slf4j;

/**
  * @FileName : ApiService.java
  * @Project : redis
  * @Date : 2023. 4. 11. 
  * @작성자 : ndj
  * @변경이력 :
  * @프로그램 설명 : 데이터 처리 로직
  */
@Service
@Slf4j
public class ApiService {
	@Autowired
	private RedisTemplate<String, Data> redisTemplate;
  
  public void saveData(Data data) {
  	log.info("Save Data > " + data.toString());
  	redisTemplate.opsForValue().set(data.getKey(), data);
	}
	
	public void updateData(Data data) {
		log.info("Update Data > " + data.toString());
		redisTemplate.opsForValue().set(data.getKey(), data);
	}
	
	public void deleteStudent(String key) {
		log.info("Delete Data Key > " + key);
		redisTemplate.delete(key);
	}
	
	public Data findById(String key) {
		// log.info("find Key > " + key);
    ValueOperations<String, Data> valueOperations = redisTemplate.opsForValue();
    Object result = valueOperations.get(key);
    System.out.println("Key: " + key + ", Value: " + result); // 콘솔에 출력
	  return redisTemplate.opsForValue().get(key);
	}
	
	
	public List<Data> getAllData() {
	    List<Data> alldata = new ArrayList<Data>();
	    redisTemplate.keys("*").forEach(dt -> alldata.add(redisTemplate.opsForValue().get(dt)));
	    log.info("find All Data Length > " + alldata.size());
	    return alldata;
	}
}
