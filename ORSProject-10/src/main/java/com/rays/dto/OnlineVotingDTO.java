package com.rays.dto;

import com.rays.common.BaseDTO;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "st_onlinevoting")
public class OnlineVotingDTO extends BaseDTO{

	@Column(name = "name")
	private String name;
	
	@Column(name = "age")
	private int age;
	
	@Column(name = "constituency")
	private String constituency;
	
	@Column(name = "has_voted")
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

	@Override
	public String getUniqueKey() {
		return "Constituency";
	}

	@Override
	public String getUniqueValue() {
		return constituency;
	}

	@Override
	public String getLabel() {
		return "Constituency";
	}

	@Override
	public String getTableName() {
		return "Online_Voting";
	}

}
