package com.spring.web.mvc.service;

import java.util.List;

import com.spring.web.mvc.controller.model.Car;
import com.spring.web.mvc.controller.model.Login;
import com.spring.web.mvc.dao.CarDao;

public interface ICarService {

	String validateUser(Login login);

	String updateCar(Car car);

	void save(Car car);

	Car findCarByCid(int cid);

	List<Car> getCars();

	void setCarDao(CarDao carDao);

	String deleteCarByCid(int cid);

}
