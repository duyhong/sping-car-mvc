package com.spring.web.mvc.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.spring.web.mvc.dao.IVendorDao;
import com.spring.web.mvc.dao.entity.VendorEntity;
import com.spring.web.mvc.repository.VendorDaoRepository;

@Service("VendorService")
public class VendorService implements IVendorService {

/*	@Autowired //byTpe , @Qualifier , byName
	@Qualifier("VendorHibernateDao")
	private IVendorDao vendorDao;*/
	@Autowired
	private VendorDaoRepository vendorDaoRepository;
	
	@Override
	public List<String> findVendors(){
		
		List<VendorEntity> vendorEntities=vendorDaoRepository.findAll();
		System.out.println(vendorEntities);
		List<String>  list=new ArrayList<>();
		for(VendorEntity vendorEntity:vendorEntities){
			list.add(vendorEntity.getVendorName());
		}
		return list;
	}

}
