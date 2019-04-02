package com.example.SpringBootTest1.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
/**
 * 首页控制器
 * @author key
 * @date 2019/04/02
 */
@Controller
public class HomeController {
	/*
	 * 系统首页
	 */
	@GetMapping(value= {"/", "index"})
	public String index() {
		return "home/index";
	}
	
	/*
	 * 商品列表
	 */
	@GetMapping(value= {"/goods"})
	public String goods() {
		return "site/goods";
	}
}
