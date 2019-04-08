package com.cody;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.cody.demo.mapper")
public class SecurityJwtDemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(SecurityJwtDemoApplication.class, args);
	}

}
