package com.emsi.dao;

import java.util.List;

import com.emsi.entities.Car;

public interface CarDao {
	void insert(Car car);

	void update(Car car);

	void deleteById(Integer register);

	Car findById(Integer register);

	List<Car> findAll();

	boolean validateUser(String username, String password);
}
