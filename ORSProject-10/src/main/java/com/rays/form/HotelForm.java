package com.rays.form;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.HotelDTO;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class HotelForm extends BaseForm{

	@NotEmpty(message = "Hotel Name is required")
	private String hotelName;
	
	@NotEmpty(message = "Location is required")
	private String location;
	
	@NotNull(message = "Rating is required")
	private Double rating;
	
	@NotEmpty(message = "Contact No is required")
	private String contactNo;

	public String getHotelName() {
		return hotelName;
	}

	public void setHotelName(String hotelName) {
		this.hotelName = hotelName;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public Double getRating() {
		return rating;
	}

	public void setRating(Double rating) {
		this.rating = rating;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}
	
	public BaseDTO getDto() {
		
		HotelDTO dto = initDTO(new HotelDTO());
		dto.setHotelName(hotelName);
		dto.setLocation(location);
		dto.setRating(rating);
		dto.setContactNo(contactNo);
		

		return dto;
		
	}

	
}
