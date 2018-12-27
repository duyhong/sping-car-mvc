package com.spring.web.mvc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.spring.web.mvc.dao.entity.VendorEntity;

@Repository("VendorDaoRepository")
public interface VendorDaoRepository extends  JpaRepository<VendorEntity, Integer> {

}
