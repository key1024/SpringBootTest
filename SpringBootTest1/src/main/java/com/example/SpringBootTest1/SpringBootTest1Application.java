package com.example.SpringBootTest1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootTest1Application {

	public static void main(String[] args) {
		System.out.println("程序启动");
		SpringApplication.run(SpringBootTest1Application.class, args);
	}

}
