package com.rays.form;

import com.rays.common.BaseForm;

import jakarta.validation.constraints.NotEmpty;

public class ForgetPasswordForm extends BaseForm {

	@NotEmpty(message = "Login Id is required")
	private String loginId;

	public String getLoginId() {
		return loginId;
	}

	public void setLoginId(String loginId) {
		this.loginId = loginId;
	}
}
