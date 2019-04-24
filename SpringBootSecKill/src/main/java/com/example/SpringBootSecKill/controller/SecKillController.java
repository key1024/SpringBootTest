package com.example.SpringBootSecKill.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.SpringBootSecKill.dto.Exposer;
import com.example.SpringBootSecKill.dto.SecKillExecution;
import com.example.SpringBootSecKill.dto.SecKillResult;
import com.example.SpringBootSecKill.entity.SecKill;
import com.example.SpringBootSecKill.enums.SecKillStatEnum;
import com.example.SpringBootSecKill.exception.RepeatKillException;
import com.example.SpringBootSecKill.exception.SecKillCloseException;
import com.example.SpringBootSecKill.exception.SecKillException;
import com.example.SpringBootSecKill.service.SecKillService;

/**
 * m秒杀商品的控制层
 * @author Administrator
 *
 */
@Controller
@RequestMapping("seckill")
public class SecKillController {

	@Autowired
	private SecKillService secKillService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping("/list")
	public String findSecKillList(Model model) {
		List<SecKill> list = secKillService.findAll();	
		model.addAttribute("list", list);
		return "page/seckill";
	}
	
	@ResponseBody
	@RequestMapping("/findById")
	public SecKill findById(@RequestParam("id")Long id) {
		return secKillService.findById(id);
	}
	
	@RequestMapping("/{seckillId}/detail")
	public String detail(@PathVariable("seckillId")Long seckillId, Model model) {
		if(seckillId == null) {
			return "page/seckill";
		}
		
		SecKill secKill = secKillService.findById(seckillId);
		model.addAttribute("seckill", secKill);
		if (secKill == null) {
			return "page/seckill";
		}
		return "page/seckill_detail";
	}
	
	@ResponseBody
	@RequestMapping(value="{seckillId}/exposer",
				method = RequestMethod.POST, produces = {"application/json;charset=UTF-8"})
	public SecKillResult<Exposer> exposer(@PathVariable("seckillId") Long seckillId) {
		SecKillResult<Exposer> result;
		try {
			Exposer exposer = secKillService.exportSecKillUrl(seckillId);
			result = new SecKillResult<Exposer>(true, exposer);
		} catch (Exception e) {
			// TODO: handle exception
			logger.error(e.getMessage(), e);
			result = new SecKillResult<Exposer>(false, e.getMessage());
		}
		
		return result;
	}
	
	@RequestMapping(value="/{seckillId}/{md5}/execution",
				method=RequestMethod.POST,
				produces={"application/json;charset=UTF-8"})
	@ResponseBody
	public SecKillResult<SecKillExecution> execute(@PathVariable("seckillId")Long seckillId,
												   @PathVariable("md5")String md5,
												   @RequestParam("money")BigDecimal money,
												   @CookieValue(value="killPhone", required=false)Long userPhone) {
		if (userPhone == null) {
			return new SecKillResult<SecKillExecution>(false, "未注册");
		}
		
		try {
			SecKillExecution execution = secKillService.executeSecKill(seckillId, money, userPhone, md5);
			return new SecKillResult<SecKillExecution>(true, execution);
		} catch (RepeatKillException e) {
			// TODO: handle exception
			SecKillExecution secKillExecution = new SecKillExecution(seckillId, SecKillStatEnum.REPEAT_KILL);
			return new SecKillResult<SecKillExecution>(true, secKillExecution);
		} catch (SecKillCloseException e) {
			// TODO: handle exception
			SecKillExecution secKillExecution = new SecKillExecution(seckillId, SecKillStatEnum.END);
			return new SecKillResult<SecKillExecution>(true, secKillExecution);
		} catch (SecKillException e) {
			// TODO: handle exception
			SecKillExecution secKillExecution = new SecKillExecution(seckillId, SecKillStatEnum.INNER_ERROR);
			return new SecKillResult<SecKillExecution>(true, secKillExecution);
		}
	}
	
	@ResponseBody
	@GetMapping(value="/time/now")
	public SecKillResult<Long> time() {
		Date now = new Date();
		return new SecKillResult<Long>(true, now.getTime());
	}
}
