package com.rays.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.FoodDeliveryDAOInt;
import com.rays.dto.FoodDeliveryDTO;

@Service
@Transactional
public class FoodDeliveryServiceImpl extends BaseServiceImpl<FoodDeliveryDTO,FoodDeliveryDAOInt> implements FoodDeliveryServiceInt{

}
