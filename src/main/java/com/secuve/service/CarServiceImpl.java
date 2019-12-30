package com.secuve.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.secuve.dao.CarDao;
import com.secuve.vo.Car;

@Service("carService")
public class CarServiceImpl implements CarService {

	@Autowired
	CarDao carDao;

	@Override
	public void create(HttpServletRequest req) {
		
		String brand = req.getParameter("brand");
		String modelName = req.getParameter("model");

		Car car = new Car(brand, modelName);
		
		carDao.create(car);
	}

	@Override
	public void update(HttpServletRequest req) {
		
		String modelName = req.getParameter("model");
		String brand = req.getParameter("brand");
		
		String brand_modify = req.getParameter("brand_modify");
		String modelName_modify = req.getParameter("model_modify");
		
		Criteria criteria = new Criteria("brand");
		criteria.is(brand);
		
		criteria = criteria.and("model");
		criteria.is(modelName);
		
		Query query = new Query(criteria);
		
		Update update = new Update();
		update.set("model", modelName_modify);
		update.set("brand", brand_modify);
		
		carDao.update(query, update);
	}

	@Override
	public void delete(HttpServletRequest req) {
		
		String brand = req.getParameter("brand");
		String modelName = req.getParameter("model");
		
		Criteria criteria = new Criteria("model");
		criteria.is(modelName);
		
		criteria = criteria.and("brand");
		criteria.is(brand);
		
		Query query = new Query(criteria);
		
		carDao.delete(query);
	}

	@Override
	public void deleteAll() {
		carDao.deleteAll();
	}

	@Override
	public Car find(HttpServletRequest req) {
		
		String getId = req.getParameter("id");
		
		Car car = new Car("", "");
		car.setId(getId);
		
		return carDao.find(car);
	}

	@Override
	public List<Car> findAll() {
		return carDao.findAll();
	}

}
