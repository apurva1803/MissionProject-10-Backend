package com.rays.ctl;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.rays.common.BaseCtl;
import com.rays.dto.OnlineVotingDTO;
import com.rays.form.OnlineVotingForm;
import com.rays.service.OnlineVotingServiceInt;

@RestController
@RequestMapping(value = "OnlineVoting")
public class OnlineVotingCtl extends BaseCtl<OnlineVotingForm, OnlineVotingDTO, OnlineVotingServiceInt> {

}
