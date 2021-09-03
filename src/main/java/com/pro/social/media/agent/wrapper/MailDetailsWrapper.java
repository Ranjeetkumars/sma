package com.pro.social.media.agent.wrapper;

import java.io.Serializable;
import java.util.List;

import com.pro.social.media.agent.controllerdto.AppointmentControllerDTO;
import com.pro.social.media.agent.controllerdto.MailDetailsControllerDto;
import com.pro.social.media.agent.controllerdto.Response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class MailDetailsWrapper extends Response implements Serializable {
	
	
	
	
	private static final long serialVersionUID = -4055925064027662526L;
	private List<MailDetailsControllerDto> objAppointmentControllerDTO;


	
}
