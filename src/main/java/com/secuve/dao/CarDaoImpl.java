package com.secuve.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import com.secuve.vo.Car;

@Repository
@Qualifier("carDao")
public class CarDaoImpl implements CarDao {

	@Autowired
	MongoTemplate mongoTemplate;

	final String COLLECTION = "cars";

	@Override
	public void create(Car car) {

		mongoTemplate.insert(car);

	}

	@Override
	public void update(Query query, Update update) {

//		mongoTemplate.save(car);
//		mongoTemplate.updateFirst(query, update, collectionName); //쿼리 조건에 맞는 정보 중 첫 번쨰 문서에만
		mongoTemplate.updateMulti(query, update, COLLECTION); //쿼리 조건에 맞는 정보 중 모든 문서에만

	}

	@Override
	public void delete(Query query) {

		mongoTemplate.remove(query, COLLECTION);

	}

	@Override
	public void deleteAll() {

		mongoTemplate.remove(new Query(), COLLECTION);

	}

	@Override
	public Car find(Car car) {
		
		Query query = new Query(Criteria.where("_id").is(car.getId()));
		return mongoTemplate.findOne(query, Car.class, COLLECTION);
		
	}

	@Override
	public List<Car> findAll() {
		
		return (List<Car>) mongoTemplate.findAll(Car.class);
		
	}


}
