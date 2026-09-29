package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.dto.FoodDeliveryDTO;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class FoodDeliveryDAOImpl extends BaseDAOImpl<FoodDeliveryDTO> implements FoodDeliveryDAOInt {

	@Override
	public Class<FoodDeliveryDTO> getDTOClass() {
		return FoodDeliveryDTO.class;
	}

	@Override
	protected List<Predicate> getWhereClause(FoodDeliveryDTO dto, CriteriaBuilder builder,
			Root<FoodDeliveryDTO> qRoot) {
		
		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getCustomerName())) {

			whereCondition.add(builder.like(qRoot.get("customerName"), dto.getCustomerName() + "%"));
		}

		if (!isEmptyString(dto.getRestaurant())) {

			whereCondition.add(builder.like(qRoot.get("restaurant"), dto.getRestaurant() + "%"));
		}

		if (!isZeroNumber(dto.getOrderAmount())) {

			whereCondition.add(builder.equal(qRoot.get("orderAmount"), dto.getOrderAmount()));
		}

		if (!isEmptyString(dto.getDeliveryStatus())) {

			whereCondition.add(builder.like(qRoot.get("deliveryStatus"), dto.getDeliveryStatus() + "%"));
		}

		return whereCondition;
	}

}
