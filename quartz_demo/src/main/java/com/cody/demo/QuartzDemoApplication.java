package com.cody.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class QuartzDemoApplication {

	private static Logger logger = LoggerFactory.getLogger(QuartzDemoApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(QuartzDemoApplication.class, args);

        logger.info("【【【【【【定时任务分布式节点 - quartz-cluster-node-first 已启动】】】】】】");
	}

}
