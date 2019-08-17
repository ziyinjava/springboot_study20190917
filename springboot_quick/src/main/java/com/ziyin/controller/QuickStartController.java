package com.ziyin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author ziyin
 * @create 2019-07-31 22:35
 */
@Controller
public class QuickStartController {
	@RequestMapping("/quick")
	@ResponseBody
	public String quick(){
		return "springboot 你s好!";
	}
}