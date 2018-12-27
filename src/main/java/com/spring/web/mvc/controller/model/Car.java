package com.spring.web.mvc.controller.model;

public class Car {

	private int cid;
	private String model;
	private String color;
	private String vendor;
	private String power;
	private String milage;
	private String image;

	public int getCid() {
		return cid;
	}

	public void setCid(int cid) {
		this.cid = cid;
	}
	
	public Car(){
		
	}

	public Car(String model, String color, String vendor, String power, String milage, String image) {
		super();
		this.model = model;
		this.color = color;
		this.vendor = vendor;
		this.power = power;
		this.milage = milage;
		this.image = image;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getVendor() {
		return vendor;
	}

	public void setVendor(String vendor) {
		this.vendor = vendor;
	}

	public String getPower() {
		return power;
	}

	public void setPower(String power) {
		this.power = power;
	}

	public String getMilage() {
		return milage;
	}

	public void setMilage(String milage) {
		this.milage = milage;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	@Override
	public String toString() {
		return "Car [cid=" + cid + ", model=" + model + ", color=" + color + ", vendor=" + vendor + ", power=" + power
				+ ", milage=" + milage + ", image=" + image + "]";
	}

}
