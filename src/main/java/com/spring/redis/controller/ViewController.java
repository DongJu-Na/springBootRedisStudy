package com.spring.redis.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

/**
  * @FileName : ViewController.java
  * @Project : redis
  * @Date : 2023. 4. 11. 
  * @작성자 : ndj
  * @변경이력 :
  * @프로그램 설명 : View 컨트롤러 반환 문자열과 일치하는 파일(html) return
  */
@Slf4j
@Controller
public class ViewController {
	
	@GetMapping("/")
	public String main() {
		log.info("Main Page Access");
		return "main";
	}
	
}
