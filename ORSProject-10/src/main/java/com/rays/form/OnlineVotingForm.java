package com.rays.form;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.HotelDTO;
import com.rays.dto.OnlineVotingDTO;

import jakarta.persistence.Column;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class OnlineVotingForm extends BaseForm {

	@NotEmpty(message = "Name is required")
	private String name;
	
	@NotNull(message = "Age is required")
	private int age;
	
	@NotEmpty(message = "Constituency is required")
	private String constituency;
	
	@NotEmpty(message = "HasVoted is required")
	private boolean hasVoted;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getConstituency() {
		return constituency;
	}

	public void setConstituency(String constituency) {
		this.constituency = constituency;
	}

	public boolean isHasVoted() {
		return hasVoted;
	}

	public void setHasVoted(boolean hasVoted) {
		this.hasVoted = hasVoted;
	}
	
	public BaseDTO getDto() {
		
		OnlineVotingDTO dto = initDTO(new OnlineVotingDTO());
		
		dto.setName(name);
		dto.setAge(age);
		dto.setConstituency(constituency);
		dto.setHasVoted(hasVoted);

		return dto;
		
	}

}
