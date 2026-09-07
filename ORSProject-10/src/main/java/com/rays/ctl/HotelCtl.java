package com.rays.ctl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.dto.HotelDTO;
import com.rays.form.HotelForm;
import com.rays.service.HotelServiceInt;

@RestController
@RequestMapping(value = "Hotel")
public class HotelCtl extends BaseCtl<HotelForm, HotelDTO, HotelServiceInt>{

}
