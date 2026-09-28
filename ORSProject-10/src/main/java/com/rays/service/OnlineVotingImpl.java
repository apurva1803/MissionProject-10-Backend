package com.rays.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rays.common.BaseServiceImpl;
import com.rays.dao.OnlineVotingDAOInt;
import com.rays.dto.OnlineVotingDTO;

@Service
@Transactional
public class OnlineVotingImpl extends BaseServiceImpl<OnlineVotingDTO, OnlineVotingDAOInt> implements OnlineVotingServiceInt {

}
