package com.spring.redis.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
	
  @GetMapping("/data")
  public List<Data> getAllData() {
      return service.getAllData();
  }

  //@Cacheable(value = "data", key = "#id")
  @GetMapping("/data/{id}")
  public Data getData(@PathVariable("id") String key) {
      return service.findById(key);
  }


  @CacheEvict(value = "data", key="#id")
  @DeleteMapping("/data/{id}")
  public void deleteData(@PathVariable("id") String key) {
      service.deleteStudent(key);
  }

  @PostMapping("/data")
  public void saveData(@RequestBody Data data) {
      service.saveData(data);
  }

  @CachePut(value = "data", key = "#data.id")
  @PutMapping("/data/update")
  public void updateData(@RequestBody Data data) {
      service.updateData(data);
  }
	
	
}
