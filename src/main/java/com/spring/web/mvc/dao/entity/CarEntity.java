package com.spring.web.mvc.dao.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="cars_tbl")
public class CarEntity {

	private int cid;
	private String model;
	private String color;
	private String vendor;
	private String power;
	private String mileage;
	private String image;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="sno")
	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}

	@Column(length=100)
	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	@Column(length=20)
	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Column(length=100)
	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	@Column(length=10)
	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	@Column(length=100)
	public String getMileage() {
		return mileage;
	}

	public void setMileage(String mileage) {
		this.mileage = mileage;
	}

	@Column(name="image",columnDefinition="longblob")
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
