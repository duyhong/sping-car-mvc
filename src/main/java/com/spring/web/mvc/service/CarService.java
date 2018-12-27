package com.spring.web.mvc.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.spring.web.mvc.advice.Pusher;
import com.spring.web.mvc.controller.model.Car;
import com.spring.web.mvc.controller.model.Login;
import com.spring.web.mvc.dao.CarDao;
import com.spring.web.mvc.dao.ICarDao;
import com.spring.web.mvc.dao.entity.CarEntity;
import com.spring.web.mvc.dao.entity.LoginEntity;
import com.spring.web.mvc.repository.CarDaoRepository;

//We have to put @Transactional annotation since we do not dao layer ..............................
@Transactional(propagation=Propagation.REQUIRED)
@Service("CarService")
public class CarService implements  ICarService {

	@Autowired
	private CarDaoRepository carDao;

	
	@Pusher
	@Override
	public String validateUser(Login login){
		/*LoginEntity entity=new LoginEntity();
		BeanUtils.copyProperties(login, entity);
		return carDao.validateUser(entity);*/
		
		List<LoginEntity> loginEntities=carDao.authUserByUsernameAndPassword(login.getUsername(), login.getPassword());
		if(loginEntities.size()==0){
			return "fail";
		}else{
			return "success";
		}
	}
	
	@Pusher
	@Override
	public String updateCar(Car car){
		CarEntity entity=new CarEntity();
		BeanUtils.copyProperties(car, entity);
		carDao.save(entity);
		 return "success";
	}
	
	@Pusher
	@Override
	public void save(Car car) {
		CarEntity entity=new CarEntity();
		BeanUtils.copyProperties(car, entity);
		carDao.save(entity);
	}
	
	@Pusher
	@Override
	public String deleteCarByCid(int cid){
		carDao.deleteById(cid);
		 return "deleted";
	}
	
	@Pusher
	@Override
	public Car findCarByCid(int cid){
		Optional<CarEntity> carEntity=carDao.findById(cid);
		Car car=new Car();
		BeanUtils.copyProperties(carEntity, car);
		return car;
		
	}

	@Pusher(mcode="M910191")
	@Override
	public List<Car> getCars() {
		List<Car> carsList=new ArrayList<Car>();
		List<CarEntity> list=carDao.findAll();
		for(CarEntity entity:list){
			Car car=new Car();
			BeanUtils.copyProperties(entity, car);
			carsList.add(car);
		}
		return carsList;
	}

	@Override
	public void setCarDao(CarDao carDao) {
		// TODO Auto-generated method stub
		
	}

}
