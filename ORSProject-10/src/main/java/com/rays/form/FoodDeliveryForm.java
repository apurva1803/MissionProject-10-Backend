package com.rays.form;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.FoodDeliveryDTO;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class FoodDeliveryForm extends BaseForm{
	
	@NotEmpty(message = "Hotel Name is required")
	private String customerName;
	
	@NotEmpty(message = "Restaurant is required")
	private String restaurant;
	
	@NotNull(message = "Order Amount is required")
	private int orderAmount;
	
	@NotEmpty(message = "Delivery Status is required")
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
	
	public BaseDTO getDto() {
		
		FoodDeliveryDTO dto = initDTO(new FoodDeliveryDTO());
		
		dto.setCustomerName(customerName);
		dto.setRestaurant(restaurant);
		dto.setOrderAmount(orderAmount);
		dto.setDeliveryStatus(deliveryStatus);

		return dto;
		
	}
}
