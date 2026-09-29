package com.rays.ctl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.common.DropdownList;
import com.rays.common.ORSResponse;
import com.rays.dto.FoodDeliveryDTO;
import com.rays.form.FoodDeliveryForm;
import com.rays.service.FoodDeliveryServiceInt;

@RestController
@RequestMapping(value = "FoodDelivery")
public class FoodDeliveryCtl extends BaseCtl<FoodDeliveryForm, FoodDeliveryDTO, FoodDeliveryServiceInt> {
	
	@Autowired
	FoodDeliveryServiceInt foodService = null;
	
	@GetMapping("preload")
	public ORSResponse preload() {
		ORSResponse res = new ORSResponse(true);
		
		FoodDeliveryDTO dto = new FoodDeliveryDTO();
		
		List<DropdownList> restaurantList = foodService.search(dto, getUserContext());
		res.addResult("restaurantList", restaurantList);
		return res;
	}
}
