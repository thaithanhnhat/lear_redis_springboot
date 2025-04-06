package com.example.radiscache.dto.request;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class ProductRequest {

	private String name;
	private String category;
	private float price;
	
	
	
	public ProductRequest(String name, String category, float price) {
		this.name = name;
		this.category = category;
		this.price = price;
	}
	public ProductRequest() {
	
	}
	
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public float getPrice() {
		return price;
	}
	public void setPrice(float price) {
		this.price = price;
	}
	
	
	
	
}