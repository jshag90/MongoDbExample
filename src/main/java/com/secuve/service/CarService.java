package com.secuve.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.secuve.vo.Car;

public interface CarService {

	public void create(HttpServletRequest req);

	public void delete(HttpServletRequest req);

	public void deleteAll();

	public Car find(HttpServletRequest req);

	public List<Car> findAll();

	void update(HttpServletRequest req);

}
