package com.spring.redis.service;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import com.spring.redis.model.Data;
import com.spring.redis.util.JsonUtil;

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
	private RedisTemplate<String, Object> redisTemplate;
  
  public void saveData(Data data) {
  	log.info("Save Data > " + data.toString());
  	redisTemplate.opsForValue().set(data.getKey(), data);
	}
	
	public void updateData(Data data) {
		log.info("Update Data > " + data.toString());
		redisTemplate.opsForValue().set(data.getKey(), data);
	}
	
	public void deleteData(String key) {
		log.info("Delete Data Key > " + key);
		redisTemplate.delete(key);
	}
	
	public JSONObject findById(String key)  {
		log.info("find Key > " + key);
		
		JSONObject result = new JSONObject();
		
		Object value = redisTemplate.opsForValue().get(key);
		if(value == null) {
			return null;
		}
		log.info("value > " + value.toString());
		
		result = JsonUtil.getDataAsJSONObject(key , value.toString());
		log.debug("findById result > " + result.toString());
		 
		 return result;
	    
	}
	
	
	public JSONArray getAllData() {
	    JSONArray alldata = new JSONArray();
	    redisTemplate.keys("*").forEach(key -> {
	        String value = redisTemplate.opsForValue().get(key).toString();
	        JSONObject data = new JSONObject();
	        data = JsonUtil.getDataAsJSONObject(key , value);
	        alldata.put(data);
	    });
	    log.info("find All Data Length > " +  alldata.length());
	    return alldata;
	}
	
}
