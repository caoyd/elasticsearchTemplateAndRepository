package com.dazekj;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class SpringBootESDemoApplication {
	public static void main(String[] args) {
		SpringApplication.run(SpringBootESDemoApplication.class, args);
	}

	/** 测试  false
	 *@author DZ5343
	 *@date 2019/4/15 16:45
	 *@desc	 设置欢迎页
	 */
	 @RequestMapping("/")
	 public String welcome(){
	 	return "redirect:/" + "hello";
	 }

}
