package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.RoleDTO;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class RoleDAOImpl extends BaseDAOImpl<RoleDTO> implements RoleDAOInt{

	@Override
	public Class<RoleDTO> getDTOClass() {
		return RoleDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(RoleDTO dto, CriteriaBuilder builder, Root<RoleDTO> qRoot) {

		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isZeroNumber(dto.getId())) {

			whereCondition.add(builder.equal(qRoot.get("id"), dto.getId()));
		}

		if (!isEmptyString(dto.getName())) {

			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}

		return whereCondition;
	}

	
}
