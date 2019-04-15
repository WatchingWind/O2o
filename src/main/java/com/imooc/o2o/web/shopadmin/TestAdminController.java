package com.imooc.o2o.web.shopadmin;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller

public class TestAdminController {
	@RequestMapping(value = "/test")
	public String test(){
		System.out.println("test");
		return "/shop/shopoperation";
	}
}
