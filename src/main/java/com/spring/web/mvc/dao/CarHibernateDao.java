package com.spring.web.mvc.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

import com.spring.web.mvc.dao.entity.CarEntity;
import com.spring.web.mvc.dao.entity.CarHistoryEntity;
import com.spring.web.mvc.dao.entity.LoginEntity;

//@Repository("CarHibernateDao")
//@Transactional
public class CarHibernateDao implements ICarDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public String validateUser(LoginEntity entity){
		Query query =this.getSession().createQuery("from LoginEntity where username=? and password=?");
		query.setParameter(1,entity.getUsername());
		query.setParameter(2, entity.getPassword());
		try {
			query.getSingleResult();
		}catch(NoResultException exception){
			return "fail";
		}
		return "success";
	}

	@Override
	public String updateCar(CarEntity car){

		Query query =this.getSession().createQuery("from CarEntity where cid=:pcid");
		query.setParameter("pcid",car.getCid());
		try {
			//dcarEntity is inside the session so if we change the state of this entity 
			//then automatically things would be updated into the database at the
			//end of the transaction
			CarEntity dcarEntity=(CarEntity)query.getSingleResult();
			dcarEntity.setVendor(car.getVendor());
			dcarEntity.setModel(car.getModel());
			dcarEntity.setColor(car.getColor());
			dcarEntity.setPower(car.getPower());
			dcarEntity.setMileage(car.getMileage());
		}catch(NoResultException exception){
			return "fail";
		}
		return "success";
	}

	//REQUIRED ensures this method must be execute inside transaction
	@Override
	public void save(CarEntity car){
		boolean b=TransactionSynchronizationManager.isActualTransactionActive();
		System.out.println("Hibernate tx is enabled = "+b);
		this.getSession().save(car);
		CarHistoryEntity carHistoryEntity=new CarHistoryEntity();
		BeanUtils.copyProperties(car, carHistoryEntity);
		this.getSession().save(carHistoryEntity);
	}

	@Override
	public CarEntity findCarByCid(int cid){
		CarEntity carEntity=new CarEntity();
		Query query =this.getSession().createQuery("from CarEntity where cid=?");
		query.setParameter(1, cid);
		try {
			 carEntity=(CarEntity)query.getSingleResult();
		}catch(NoResultException exception){
			System.out.println(exception.getMessage());
		}
		return carEntity;
	}
	
	@Override
	public String deleteCarByCid(int cid){
		CarEntity carEntity=this.getSession().get(CarEntity.class, cid);
		this.getSession().delete(carEntity);
		return "deleted";
	}

	@Override
	public List<CarEntity> getCars(){
		return this.getSession().createQuery("from CarEntity").list();
	}
}
