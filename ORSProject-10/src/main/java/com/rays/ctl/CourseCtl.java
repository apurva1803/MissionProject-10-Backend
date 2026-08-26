package com.rays.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.DropdownList;
import com.rays.common.ORSResponse;
import com.rays.dto.CourseDTO;
import com.rays.dto.RoleDTO;
import com.rays.form.CourseForm;
import com.rays.service.CourseServiceInt;
import com.rays.service.RoleServiceInt;

@RestController
@RequestMapping(value = "Course")
public class CourseCtl extends BaseCtl<CourseForm, CourseDTO, CourseServiceInt> {

	@Autowired
	CourseServiceInt courseService = null;

	@GetMapping("preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);
		CourseDTO dto = new CourseDTO();
		// dto.setStatus(RoleDTO.ACTIVE);
		List<DropdownList> list = courseService.search(dto, userContext);
		res.addResult("courseList", list);
		return res;
	}
}
