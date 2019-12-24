package com.secuve.controller;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;

import com.secuve.configuration.MongoDbConfig;
import com.secuve.service.CarService;
import com.secuve.vo.Car;

public class App {
	public static void main(String[] args) {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(MongoDbConfig.class);
        CarService carService = (CarService) context.getBean("carService");
        
        Car polo = new Car("Volkswagen", "Polo");
        carService.create(polo);
        
	}
}
