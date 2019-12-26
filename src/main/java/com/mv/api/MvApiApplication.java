package com.mv.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

import com.mv.api.config.MvApiProperties;

@SpringBootApplication
@EnableConfigurationProperties(MvApiProperties.class)
public class MvApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(MvApiApplication.class, args);
	}

}
