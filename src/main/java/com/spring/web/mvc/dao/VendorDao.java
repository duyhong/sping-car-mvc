package com.spring.web.mvc.dao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;


//@Repository("CityDao")
public class VendorDao implements IVendorDao{
	
	@Autowired
	@Qualifier("pjdbcTemplate")
	private JdbcTemplate jdbcTemplate;

	@Override
	public List<String> findCities(){
		String sql="select cityname from cities_tbl";
		//queryForList - will fetch list of  = String.class
		List<String> cities=jdbcTemplate.queryForList(sql,String.class);	
		return cities;
	}

}
