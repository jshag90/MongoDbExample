package com.secuve.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.secuve.configuration.MongoDbConfig;
import com.secuve.service.CarService;
import com.secuve.vo.Car;

@Controller
public class HomeController {

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {
		
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(MongoDbConfig.class);
		CarService carService = (CarService) context.getBean("carService");

		carService.deleteAll();

		Car polo = new Car("Volkswagen", "Polo");
		carService.create(polo);

		Car jetta = new Car("Volkswagen", "Jetta");
		carService.create(jetta);
		
		Car ertiga = new Car("Maruti Suzuki", "Ertiga");
		carService.create(ertiga);

		List<Car> cars = carService.findAll();
		List<String> brandList = new ArrayList<String>(); 
		List<String> modelList = new ArrayList<String>();
		List<String> dataIds = new ArrayList<String>();
		
		for (Car car : cars) {

			brandList.add(car.getBrand());
			modelList.add(car.getModel());
			dataIds.add(car.getId());
			
		}
		
		model.addAttribute("IDS", dataIds);
		model.addAttribute("BRANDS", brandList);
		model.addAttribute("MODELS", modelList);
		return "home";
		
	}
	
}
