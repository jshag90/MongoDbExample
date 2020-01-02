package com.secuve.dao;

import java.util.List;

import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;

import com.secuve.vo.Car;

public interface CarDao {

	public void create(Car car);

	public void delete(Query query);

	public void deleteAll();

	public Car find(Query query);

	public List<Car> findAll();

	void update(Query query, Update update);

}
