package com.isep.lucky_data;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication
@EntityScan
@EnableJpaRepositories("com.isep.lucky_data.repository")
@EnableSwagger2
public class LuckyDataApplication {
	public static void main(String[] args) {
		SpringApplication.run(LuckyDataApplication.class, args);
	}
}