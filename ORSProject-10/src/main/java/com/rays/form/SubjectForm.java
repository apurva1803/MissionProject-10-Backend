package com.rays.form;

import com.rays.common.BaseDTO;
import com.rays.common.BaseForm;
import com.rays.dto.SubjectDTO;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;

public class SubjectForm extends BaseForm {

	@NotNull(message = "Course is required")
	@Min(1)
	private Long courseId = 0L;

	private String courseName;

	@NotEmpty(message = "Name is required")
	private String name;

	@NotEmpty(message = "Description is required")
	private String description;

	public long getCourseId() {
		return courseId;
	}

	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}

	public String getCourseName() {
		return courseName;
	}

	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public BaseDTO getDto() {

		SubjectDTO dto = initDTO(new SubjectDTO());

		dto.setCourseId(courseId);
		dto.setCourseName(courseName);
		dto.setName(name);
		dto.setDescription(description);

		return dto;
	}
}
