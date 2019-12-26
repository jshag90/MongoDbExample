package com.secuve.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

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

	private CarService initCarService() {
		AbstractApplicationContext context = new AnnotationConfigApplicationContext(MongoDbConfig.class);
		CarService carService = (CarService) context.getBean("carService");
		return carService;
	}

	//��λ���
	@RequestMapping(value = "/delete_all_car", method = RequestMethod.GET)
	public String allDelete(HttpServletRequest req, Model model) {
		String page = "";
		try {
			initCarService().deleteAll();
			page = "redirect:/";
		} catch (Exception e) {
			model.addAttribute("msg", "������ �����Ͽ����ϴ�.");
			page = "fail";
		}

		return page;

	}

	//�߰�
	@RequestMapping(value = "/insert_car", method = RequestMethod.GET)
	public String saveCar(HttpServletRequest req, Model model) {

		String page = "";

		try {
			String brand = req.getParameter("brand");
			String modelName = req.getParameter("model");

			Car insertData = new Car(brand, modelName);
			initCarService().create(insertData);
			page = "redirect:/";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "��Ͽ� �����Ͽ����ϴ�.");
			page = "fail";
		}

		return page;
	}

	//��ü��ȸ
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		List<Car> cars = initCarService().findAll();
		List<String> brandList = new ArrayList<String>();
		List<String> modelList = new ArrayList<String>();
		List<String> dataIds = new ArrayList<String>();

		for (Car car : cars) {

			brandList.add(car.getBrand());
			modelList.add(car.getModel());
			dataIds.add(car.getId());

		}

		model.addAttribute("CARS", cars);
		model.addAttribute("IDS", dataIds);
		model.addAttribute("BRANDS", brandList);
		model.addAttribute("MODELS", modelList);
		return "home";

	}
	
	//�ϳ��� ��ȸ
	@RequestMapping(value = "/findOneData", method = RequestMethod.GET)
	public String updateCar(HttpServletRequest req, Model model) {

		String getId = req.getParameter("id");
		String brand = req.getParameter("brand");
		String modelName = req.getParameter("model");
		
		Car insertData = new Car(brand, modelName);
		insertData.setId(getId);
		
		
		Car getData = initCarService().find(insertData);
		System.out.println(getData.getModel().toString());
		

		return "updateform";

	}
	
	

}
