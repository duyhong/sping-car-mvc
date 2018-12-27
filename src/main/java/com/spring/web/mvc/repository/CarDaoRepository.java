package com.spring.web.mvc.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.spring.web.mvc.dao.entity.CarEntity;
import com.spring.web.mvc.dao.entity.LoginEntity;

@Repository("CarDaoRepository")
public interface CarDaoRepository extends  JpaRepository<CarEntity, Integer> {

	public CarEntity findByCid(int cid);
		//custom query to fetch data from Spring Data with JPA
		@Query("SELECT p FROM LoginEntity p WHERE p.username = :username AND p.password= :password")
		public List<LoginEntity> authUserByUsernameAndPassword(@Param("username") String username,@Param("password") String password);

}
