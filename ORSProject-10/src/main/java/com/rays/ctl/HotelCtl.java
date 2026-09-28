package com.rays.ctl;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.ORSResponse;
import com.rays.dto.HotelDTO;
import com.rays.form.HotelForm;
import com.rays.service.HotelServiceInt;

@RestController
@RequestMapping(value = "Hotel")
public class HotelCtl extends BaseCtl<HotelForm, HotelDTO, HotelServiceInt>{

	@GetMapping("preload")
	public ORSResponse preload() {
		return new ORSResponse(true);
	}

}
