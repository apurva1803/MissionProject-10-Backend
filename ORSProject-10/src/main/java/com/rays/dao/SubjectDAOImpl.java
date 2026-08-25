package com.rays.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.rays.common.BaseDAOImpl;
import com.rays.common.UserContext;
import com.rays.dto.CourseDTO;
import com.rays.dto.SubjectDTO;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class SubjectDAOImpl extends BaseDAOImpl<SubjectDTO> implements SubjectDAOInt {

	@Autowired
	CourseDAOInt courseDao;

	@Override
	public Class<SubjectDTO> getDTOClass() {
		return SubjectDTO.class;
	}

	@Override
	protected void populate(SubjectDTO dto, UserContext userContext) {
		if (dto.getCourseId() != 0) {
			CourseDTO courseDTO = courseDao.findByPK(dto.getCourseId(), userContext);
			if (courseDTO != null) {
				dto.setCourseName(courseDTO.getName());
			}
		}
	}

	@Override
	protected List<Predicate> getWhereClause(SubjectDTO dto, CriteriaBuilder builder, Root<SubjectDTO> qRoot) {

		List<Predicate> whereCondition = new ArrayList<Predicate>();

		if (!isEmptyString(dto.getName())) {

			whereCondition.add(builder.like(qRoot.get("name"), dto.getName() + "%"));
		}

		if (!isEmptyString(dto.getCourseName())) {

			whereCondition.add(builder.like(qRoot.get("courseName"), dto.getCourseName() + "%"));
		}

		return whereCondition;
	}
}
