package com.secuve.controller;

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
public class CrudMongoDbController {

	private static final Logger logger = LoggerFactory.getLogger(CrudMongoDbController.class);

	// ��λ���
	@RequestMapping(value = "/delete_all_car", method = RequestMethod.GET)
	public String allDelete(HttpServletRequest req, Model model) {
		String page = "";
		try {
			initCarService().deleteAll();
			page = "redirect:/";
		} catch (Exception e) {
			model.addAttribute("msg", "������ �����Ͽ����ϴ�.");
			page = "msg";
		}

		return page;

	}

	// �߰�
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

	// ��ü��ȸ
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		List<Car> cars = initCarService().findAll();
		model.addAttribute("CARS", cars);
		return "home";

	}

	// �ϳ��� ��ȸ
	@RequestMapping(value = "/findOneCarData", method = RequestMethod.GET)
	public String findOneCarData(HttpServletRequest req, Model model) {

		try {

			Car getData = initCarService().find(req);
			model.addAttribute("CAR", getData);
		} catch (Exception e) {

		}

		return "updateform";

	}

	// ����
	@RequestMapping(value = "/update_car", method = RequestMethod.GET)
	public String updateCar(HttpServletRequest req, Model model) {

		try {

			initCarService().update(req);
			model.addAttribute("msg", "������ �Ϸ�Ǿ����ϴ�.");

		} catch (Exception e) {

			e.printStackTrace();
			model.addAttribute("msg", "������ ���о����ϴ�.");

		}

		return "msg";

	}

	// ����
	@RequestMapping(value = "/delete_car", method = RequestMethod.GET)
	public String deleteCar(HttpServletRequest req, Model model) {

		try {

			initCarService().delete(req);
			model.addAttribute("msg", "������ �Ϸ�Ǿ����ϴ�.");

		} catch (Exception e) {

			e.printStackTrace();
			model.addAttribute("msg", "������ �����Ͽ����ϴ�.");

		}

		return "msg";

	}

	private CarService initCarService() {
		CarService carService = null;
		
		try {
			AbstractApplicationContext context = new AnnotationConfigApplicationContext(MongoDbConfig.class);
			carService = (CarService) context.getBean("carService");
		} catch (Exception e) {
			e.printStackTrace();
		}

		return carService;
	}

}
