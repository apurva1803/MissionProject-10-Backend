package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.CollegeDTO;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class CollegeDAOImpl extends BaseDAOImpl<CollegeDTO> implements CollegeDAOInt {

	@Override
	public Class<CollegeDTO> getDTOClass() {
		return CollegeDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(CollegeDTO dto, CriteriaBuilder builder, Root<CollegeDTO> qRoot) {

		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getName())) {

			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}

		if (!isEmptyString(dto.getCity())) {

			whereCondition.add(builder.like(qRoot.get("city"), dto.getCity() + "%"));
		}

		if (!isEmptyString(dto.getState())) {

			whereCondition.add(builder.like(qRoot.get("state"), dto.getState() + "%"));
		}

		if (!isEmptyString(dto.getAddress())) {

			whereCondition.add(builder.like(qRoot.get("address"), dto.getAddress() + "%"));
		}

		if (!isEmptyString(dto.getPhoneNo())) {

			whereCondition.add(builder.like(qRoot.get("phoneNo"), dto.getPhoneNo() + "%"));
		}

		return whereCondition;
	}

}