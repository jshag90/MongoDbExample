package com.secuve.controller;

import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.secuve.service.CarService;
import com.secuve.vo.Car;

@Controller
public class CrudMongoDbController {

	private static final Logger logger = LoggerFactory.getLogger(CrudMongoDbController.class);

	@Autowired
	CarService carService;
	
	// ��λ���
	@RequestMapping(value = "/delete_all_car", method = RequestMethod.POST)
	public String allDelete(HttpServletRequest req, Model model) {

		String page = "";

		try {
			carService.deleteAll();
			page = "redirect:/";
		} catch (Exception e) {
			model.addAttribute("msg", "������ �����Ͽ����ϴ�.");
			page = "msg";
		}

		return page;

	}

	// �߰�
	@RequestMapping(value = "/insert_car", method = RequestMethod.POST)
	public String saveCar(HttpServletRequest req, Model model) {

		String page = "";

		try {
			carService.create(req);
			page = "redirect:/";
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "��Ͽ� �����Ͽ����ϴ�.");
			page = "msg";
		}

		return page;
	}

	// ��ü��ȸ
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Locale locale, Model model) {

		try {
			List<Car> cars = carService.findAll();
			model.addAttribute("CARS", cars);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return "home";

	}

	// �ϳ��� ��ȸ
	@RequestMapping(value = "/find_one_car", method = RequestMethod.GET)
	public String findOneCarData(HttpServletRequest req, Model model) {

		try {
			Car getData = carService.find(req);
			model.addAttribute("CAR", getData);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return "updateform";

	}

	// ����
	@RequestMapping(value = "/update_car", method = RequestMethod.POST)
	public String updateCar(HttpServletRequest req, Model model) {

		try {

			carService.update(req);
			model.addAttribute("msg", "������ �Ϸ�Ǿ����ϴ�.");

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "������ ���о����ϴ�.");
		}

		return "msg";

	}

	// ����
	@RequestMapping(value = "/delete_car", method = RequestMethod.POST)
	public String deleteCar(HttpServletRequest req, Model model) {

		try {
			
			carService.delete(req);
			model.addAttribute("msg", "������ �Ϸ�Ǿ����ϴ�.");

		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("msg", "������ �����Ͽ����ϴ�.");
		}

		return "msg";

	}


}
