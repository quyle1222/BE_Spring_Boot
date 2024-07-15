package com.example.springbootexample;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class SpringBootExampleApplication {

    public SpringBootExampleApplication(ConfigurableApplicationContext context) {
        this.context = context;
    }

    @RequestMapping("/")
	public String home() {
		return "Hello Docker World";
	}

	private final ConfigurableApplicationContext context;

	public static void main(String[] args) {
		SpringApplication.run(SpringBootExampleApplication.class, args
		);
	}

	public void stop() {
		context.close();
	}
}
