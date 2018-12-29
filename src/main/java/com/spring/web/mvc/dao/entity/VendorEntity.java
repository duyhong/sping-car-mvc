package com.spring.web.mvc.dao.entity;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="vendors_tbl")
public class VendorEntity {
	private int sno;
	private String vendorname;
	private Timestamp doe;
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getSno() {
		return sno;
	}
	public void setSno(int sno) {
		this.sno = sno;
	}
	
	@Column(length=100)
	public String getVendorName() {
		return vendorname;
	}
	
	public void setVendorName(String vendorname) {
		this.vendorname = vendorname;
	}
	
	public Timestamp getDoe() {
		return doe;
	}
	
	public void setDoe(Timestamp doe) {
		this.doe = doe;
	}
	
	@Override
	public String toString() {
		return "VendorEntity [sno=" + sno + ", vendorname=" + vendorname + ", doe=" + doe + "]";
	}
	
}
