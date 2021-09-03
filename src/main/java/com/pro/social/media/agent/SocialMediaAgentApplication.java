package com.pro.social.media.agent;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
//http://localhost:1000/common/swagger-ui.html#/
//http://localhost:1000/common/v2/api-docs
@SpringBootApplication
//@EnableDiscoveryClient
//@EnableCaching
//@EnableScheduling
public class SocialMediaAgentApplication {

	public static void main(String[] args) {
		SpringApplication.run(SocialMediaAgentApplication.class, args);
	}

}
