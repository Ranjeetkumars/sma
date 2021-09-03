package com.pro.social.media.agent.service;

import java.util.List;

import com.pro.social.media.agent.exceptions.DataNotFoundException;
import com.pro.social.media.agent.servicedto.AppointmentServiceDTO;

/**
 * @author : Habiboon Patan
 * @Date : 2019-07-16
 */
public interface AppointmentService {

	public String checkAppointmentIsExist(AppointmentServiceDTO dataServiceDTO, String strRequestID)
			throws DataNotFoundException;

	
	public String saveAppointment(AppointmentServiceDTO dataInfo, String requestId);
	
	public List<AppointmentServiceDTO> appointmentType(String strRequestID)
			throws DataNotFoundException;
	
	public List<AppointmentServiceDTO> weekType(String strRequestID)
			throws DataNotFoundException;
	
	public List<AppointmentServiceDTO> getWeeks(String strRequestID)
			throws DataNotFoundException;
	public List<AppointmentServiceDTO> months(String strRequestID)
			throws DataNotFoundException;
}
