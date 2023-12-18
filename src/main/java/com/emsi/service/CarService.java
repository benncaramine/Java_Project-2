package com.emsi.service;

import java.util.List;

import com.emsi.dao.CarDao;
import com.emsi.dao.impl.CarDaoImpl;
import com.emsi.entities.Car;

public class CarService {
	private CarDao carDao = new CarDaoImpl();

	public List<Car> findAll() {
		return carDao.findAll();
	}

	public void saveOrUpdate(Car car) {
		if (carDao.findById(car.getRegister()) == null) {
			carDao.insert(car);
			System.out.println("adding car");
		} else {
			carDao.update(car);
		}
	}
	
	public void save(Car car) {
		carDao.insert(car);
	}

	public void update(Car car) {
		carDao.update(car);
	}

	public void remove(Car car) {
		carDao.deleteById(car.getRegister());
	}
	public boolean validateUser(String username, String password) {
		return carDao.validateUser(username, password);
	}
}
