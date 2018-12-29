package com.spring.web.mvc.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.spring.web.mvc.dao.entity.CarEntity;
import com.spring.web.mvc.dao.entity.LoginEntity;


//@Repository("CarDao")
public class CarDao implements ICarDao {
	
	@Autowired
	@Qualifier("pjdbcTemplate")
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public String validateUser(LoginEntity entity){
		System.out.println("Before saving\n");
		String sql="select  username from logins_tbl where username=? and password=?";
		try {
			jdbcTemplate.queryForObject(sql,new Object[]{entity.getUsername(),entity.getPassword()},new BeanPropertyRowMapper(LoginEntity.class));	
		}catch(Exception ex){
			return "fail";
		}
		return "success";
	}
	
	@Override
	public String updateCar(CarEntity car){
		System.out.println("Before Updating\n");
		String sql="update profile set vendor=?,model=?,color=?,power=?,image=? where cid=?";
		Object data[] = new Object[]{car.getVendor(),car.getModel(),car.getColor(),car.getPower(),car.getMileage(),car.getImage()};
		jdbcTemplate.update(sql,data);	
		return "success";
	}

	//REQUIRED ensures this method must be execute inside transaction
	@Transactional(propagation=Propagation.REQUIRED)
	@Override
	public void save(CarEntity car){
		boolean b=TransactionSynchronizationManager.isActualTransactionActive();
		System.out.println("Transaction status = "+b);
		System.out.println("Before saving\n");
		String sql1="insert into cars_tbl(vendor,model,color,power,mileage,image) values(?,?,?,?,?,?)";
		Object data[] = new Object[]{car.getVendor(),car.getModel(),car.getColor(),car.getPower(),car.getMileage(),car.getImage()};
		System.out.println(jdbcTemplate);
		//Query1
		jdbcTemplate.update(sql1,data);
		
		String sql2="insert into car_history_tbl(vendor,model,color,power,mileage,image) values(?,?,?,?,?,?)";
		jdbcTemplate.update(sql2,data);
		System.out.println("Completed saving");
	}
	
	@Override
	public CarEntity findCarByCid(int cid){
		String sql="select sno,name,email,gender,mobile,photo,city from cars_tbl where cid=?";
		CarEntity carEntity=(CarEntity)jdbcTemplate.queryForObject(sql,new Object[]{cid},new BeanPropertyRowMapper(CarEntity.class));	
		return carEntity;
	}
	
	@Override
	public String deleteCarByCid(int cid){
		String sql="delete from  cars_tbl where cid=?";
		int row=jdbcTemplate.update(sql, new Object[]{cid});	
		return row>0?"deleted":"fail";
	}
	
	@Override
	public List<CarEntity> getCars(){
		List<CarEntity> carList = new ArrayList<CarEntity>();
		String sql="select sno,name,email,gender,mobile,photo,city from profile";
		carList=jdbcTemplate.query(sql, new BeanPropertyRowMapper(CarEntity.class));	
		return carList;
	}

}
