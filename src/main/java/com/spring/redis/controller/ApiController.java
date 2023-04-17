package com.spring.redis.controller;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.spring.redis.model.Data;
import com.spring.redis.service.ApiService;

import lombok.extern.slf4j.Slf4j;

/**
  * @FileName : ApiController.java
  * @Project : redis
  * @Date : 2023. 4. 11. 
  * @작성자 : ndj
  * @변경이력 :
  * @프로그램 설명 : View(html)과 Data 통신을 위한 컨트롤러
  */
@Slf4j
@RequestMapping("/api")
@RestController
public class ApiController {
	
	@Autowired
	private ApiService service;
	
	@Cacheable(value = "data")
	@GetMapping("/data")
	public ResponseEntity<?> getAllData(@RequestParam(name = "page", defaultValue = "1") int page,
	                                    @RequestParam(name = "perpage", defaultValue = "10") int perPage) {
  	
  	JSONObject jsonObject = new JSONObject();
  	jsonObject.put("result", true);

  	JSONObject data = new JSONObject();
  	JSONArray contents =  service.getAllData();
  	data.put("contents", contents);

  	JSONObject pagination = new JSONObject();
  	pagination.put("page", page);
  	pagination.put("totalCount", contents.length());
  	data.put("pagination", pagination);

  	jsonObject.put("data", data);
  	
  	if(contents == null) {
    	return new ResponseEntity<>(jsonObject.toMap() , HttpStatus.NOT_FOUND);
    }
    
    return new ResponseEntity<>(jsonObject.toMap(), HttpStatus.OK);
  }
	
  @Cacheable(value = "data", key = "#id")
  @GetMapping("/data/{id}")
  public ResponseEntity<?> getData(@PathVariable("id") String key) {
      JSONObject data = service.findById(key);
      
      if(data == null) {
      	return new ResponseEntity<>(HttpStatus.NOT_FOUND);
      }
      
      return new ResponseEntity<>(data.toMap(), HttpStatus.OK);
  }

  @CacheEvict(value = "data", key="#id")
  @DeleteMapping("/data/{id}")
  public void deleteData(@PathVariable("id") String key) {
      service.deleteData(key);
  }

  @PostMapping("/data")
  public void saveData(@RequestBody Data data) {
      service.saveData(data);
  }

  @CachePut(value = "data", key = "#id")
  @PutMapping("/data/update")
  public void updateData(@RequestBody Data data) {
      service.updateData(data);
  }
	
	
}
