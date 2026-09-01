package com.rays.dao;

import java.util.List;

import com.rays.common.BaseDAOInt;
import com.rays.dto.MarksheetDTO;

public interface MarksheetDAOInt extends BaseDAOInt<MarksheetDTO> {

	public List<MarksheetDTO> getMeritList();
}