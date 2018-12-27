package com.spring.web.mvc.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.spring.web.mvc.dao.entity.VendorEntity;

//@Repository("VendorHibernateDao")
//@Transactional
public class VendorHibernateDao implements IVendorDao {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	private Session getSession(){
		return sessionFactory.getCurrentSession();
	}

	@Override
	public List<String> findCities() {
		System.out.println("Calling hibernate one");
		List<VendorEntity> cityEntities=getSession().createQuery("from CityEntity").list();
		List<String>  list=new ArrayList<>();
		for(VendorEntity cityEntity:cityEntities){
			list.add(cityEntity.getVendorName());
		}
		return list;
	}
	
}