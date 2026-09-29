package com.rays.dto;

import com.rays.common.BaseDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "st_fooddelivery")
public class FoodDeliveryDTO extends BaseDTO{

	@Column(name = "customer_name")
	private String customerName;
	
	@Column(name = "restaurant")
	private String restaurant;
	
	@Column(name = "order_amount")
	private int orderAmount;
	
	@Column(name = "delivery_status")
	private String deliveryStatus;
	
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getRestaurant() {
		return restaurant;
	}

	public void setRestaurant(String restaurant) {
		this.restaurant = restaurant;
	}

	public int getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(int orderAmount) {
		this.orderAmount = orderAmount;
	}

	public String getDeliveryStatus() {
		return deliveryStatus;
	}

	public void setDeliveryStatus(String deliveryStatus) {
		this.deliveryStatus = deliveryStatus;
	}

	@Override
	public String getUniqueKey() {
		return "customerName";
	}

	@Override
	public String getUniqueValue() {
		return customerName;
	}

	@Override
	public String getLabel() {
		return "customerName";
	}

	@Override
	public String getTableName() {
		return "FoodDelivery";
	}
	
	@Override
	public String getKey() {
		return restaurant;
	}
	
	@Override
	public String getValue() {
		return restaurant;
	}
	
}
