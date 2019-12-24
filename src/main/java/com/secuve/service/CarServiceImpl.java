package com.secuve.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.secuve.dao.CarDao;
import com.secuve.vo.Car;

@Service("carService")
public class CarServiceImpl implements CarService {

	@Autowired
	CarDao carDao;

	@Override
	public void create(Car car) {
		carDao.create(car);
	}

	@Override
	public void update(Car car) {
		carDao.update(car);
	}

	@Override
	public void delete(Car car) {
		carDao.delete(car);
	}

	@Override
	public void deleteAll() {
		carDao.deleteAll();
	}

	@Override
	public Car find(Car car) {
		return carDao.find(car);
	}

	@Override
	public List<Car> findAll() {
		return carDao.findAll();
	}

}
