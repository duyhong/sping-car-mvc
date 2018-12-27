package com.spring.web.mvc.dao;

import java.util.List;

import com.spring.web.mvc.dao.entity.CarEntity;
import com.spring.web.mvc.dao.entity.LoginEntity;

public interface ICarDao {

	String validateUser(LoginEntity entity);

	String updateCar(CarEntity car);

	void save(CarEntity car);

	CarEntity findCarByCid(int cid);

	String deleteCarByCid(int cid);

	List<CarEntity> getCars();

	/*default String deleteCarByCid(int cid) {
		return "";
	}*/

}
