package com.example.SpringBootSecKill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * y用于做页面映射跳转的控制层
 * @author Administrator
 *
 */
@Controller
public class BaseController {
	/**
	 * t跳转到秒杀商品页
	 * 
	 * @return
	 */
	@RequestMapping("/seckill")
	public String seckillGoods() {
		return "redirect:/seckill/list";
	}
}
