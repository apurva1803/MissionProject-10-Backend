package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.OnlineVotingDTO;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class OnlineVotingDAOImpl extends BaseDAOImpl<OnlineVotingDTO> implements OnlineVotingDAOInt {

	@Override
	public Class<OnlineVotingDTO> getDTOClass() {
		return OnlineVotingDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(OnlineVotingDTO dto, CriteriaBuilder builder,
			Root<OnlineVotingDTO> qRoot) {
		
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getName())) {

			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}

		if (!isEmptyString(dto.getConstituency())) {

			whereCondition.add(builder.like(qRoot.get("constituency"), dto.getConstituency() + "%"));
		}

		if (!isZeroNumber(dto.getAge())) {

			whereCondition.add(builder.equal(qRoot.get("age"), dto.getAge()));
		}

		return whereCondition;
	}

}
