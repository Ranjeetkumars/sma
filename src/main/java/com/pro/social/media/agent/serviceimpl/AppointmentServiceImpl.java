//package com.pro.social.media.agent.serviceimpl;
//import java.util.List;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.stereotype.Service;
//import com.pro.social.media.agent.dao.SocialMediaAgentDao;
//import com.pro.social.media.agent.exceptions.DataNotFoundException;
//import com.pro.social.media.agent.mapper.AppointmentMapper;
//import com.pro.social.media.agent.persistancedto.AppointmentPersistanceDTO;
//import com.pro.social.media.agent.service.AppointmentService;
//import com.pro.social.media.agent.servicedto.AppointmentServiceDTO;
//
//import lombok.extern.slf4j.Slf4j;
//
///**
// * @author : Ranjeet kumar
// *
// */
//
//@Service("objAppointmentServiceImpl")
//@Slf4j
//public class AppointmentServiceImpl implements AppointmentService {
//
//	
//	
//	@Autowired
//	@Qualifier("objSocialMediaAgentDaoImpl")
//	SocialMediaAgentDao objSocialMediaAgentDaoImpl;
//
//	@Override
//	public String checkAppointmentIsExist(AppointmentServiceDTO dataServiceDTO, String strRequestID)
//			throws DataNotFoundException {
//		String strQuery = "select * from sp_select_check_ers_future_events_trans(" + dataServiceDTO.getEventId() + ",'"
//				+ dataServiceDTO.getTime() + "','" + dataServiceDTO.getDate() + "'," + dataServiceDTO.getPatientId()
//				+ ")";
//		log.info(strRequestID + ":::::::::::::" + strQuery);
//		String status = objSocialMediaAgentDaoImpl.getSingleData(strQuery);
//		return status;
//	}
//
//	@Override
//	public String saveAppointment(AppointmentServiceDTO dataInfo, String requestId) {
//		String strQuery = "select * from sp_insert_ers_future_events_transs(" + dataInfo.getEventId() + ","
//				+ dataInfo.getVictimId() + ",'" + dataInfo.getTime() + "','" + dataInfo.getDate() + "',"
//				+ dataInfo.getUserId() + "," + dataInfo.getModuleId() + "," + dataInfo.getRoleid() + ",'"
//				+ dataInfo.getDescription() + "')";
//		log.info(requestId + ":::::::::::::" + strQuery);
//		String status = objSocialMediaAgentDaoImpl.saveData(strQuery);
//		return status;
//	}
//
//	@Override
//	public List<AppointmentServiceDTO> appointmentType(String strRequestID)
//			throws DataNotFoundException {
//		
//		AppointmentMapper objAppointmentMapper = new AppointmentMapper();
//		List<AppointmentServiceDTO> appointmentServiceDTO = null;
//		String strQuery = "select * from sp_select_ers_future_type_ref()";
//		log.info(strRequestID + ":::::::::::::" + strQuery);
//		@SuppressWarnings("unchecked")
//		List<Object[]> list = (List<Object[]>) objSocialMediaAgentDaoImpl.getData(strQuery);
//		if (null != list && !list.isEmpty()) {
//			List<AppointmentPersistanceDTO> gisPersDTOs = objAppointmentMapper.conversiongetappointmentType(list);
//			appointmentServiceDTO = objAppointmentMapper.conversionFromPersistanceTOServiceDTO(gisPersDTOs);
//		} else {
//			throw new DataNotFoundException(strRequestID + ":::::No getVictimDetails::::");
//		}
//		return appointmentServiceDTO;
//	}
//
//	@Override
//	public List<AppointmentServiceDTO> weekType(String strRequestID) throws DataNotFoundException {
//		AppointmentMapper objAppointmentMapper = new AppointmentMapper();
//		List<AppointmentServiceDTO> appointmentServiceDTO = null;
//		String strQuery = "select * from sp_select_ers_future_countdays_ref()";
//		log.info(strRequestID + ":::::::::::::" + strQuery);
//		@SuppressWarnings("unchecked")
//		List<Object[]> list = (List<Object[]>) objSocialMediaAgentDaoImpl.getData(strQuery);
//		if (null != list && !list.isEmpty()) {
//			List<AppointmentPersistanceDTO> gisPersDTOs = objAppointmentMapper.conversionForweekType(list);
//			appointmentServiceDTO = objAppointmentMapper.conversionFromPersistanceTOServiceDTO(gisPersDTOs);
//		} else {
//			throw new DataNotFoundException(strRequestID + ":::::No weekType::::");
//		}
//		return appointmentServiceDTO;
//	}
//
//	@Override
//	public List<AppointmentServiceDTO> getWeeks(String strRequestID) throws DataNotFoundException {
//		AppointmentMapper objAppointmentMapper = new AppointmentMapper();
//		List<AppointmentServiceDTO> appointmentServiceDTO = null;
//		String strQuery = "select * from sp_select_wfkellyday_ref()";
//		log.info(strRequestID + ":::::::::::::" + strQuery);
//		@SuppressWarnings("unchecked")
//		List<Object[]> list = (List<Object[]>) objSocialMediaAgentDaoImpl.getData(strQuery);
//		if (null != list && !list.isEmpty()) {
//			List<AppointmentPersistanceDTO> gisPersDTOs = objAppointmentMapper.conversionForweek(list);
//			appointmentServiceDTO = objAppointmentMapper.conversionFromPersistanceTOServiceDTO(gisPersDTOs);
//		} else {
//			throw new DataNotFoundException(strRequestID + ":::::No Weeks Found:::::");
//		}
//		return appointmentServiceDTO;
//	}
//	@Override
//	public List<AppointmentServiceDTO> months(String strRequestID) throws DataNotFoundException {
//		AppointmentMapper objAppointmentMapper = new AppointmentMapper();
//		List<AppointmentServiceDTO> appointmentServiceDTO = null;
//		String strQuery = "select * from sp_select_ers_future_months_ref()";
//		log.info(strRequestID + ":::::::::::::" + strQuery);
//		@SuppressWarnings("unchecked")
//		List<Object[]> list = (List<Object[]>) objSocialMediaAgentDaoImpl.getData(strQuery);
//		if (null != list && !list.isEmpty()) {
//			List<AppointmentPersistanceDTO> gisPersDTOs = objAppointmentMapper.conversionFormonth(list);
//			appointmentServiceDTO = objAppointmentMapper.conversionFromPersistanceTOServiceDTO(gisPersDTOs);
//		} else {
//			throw new DataNotFoundException(strRequestID + ":::::No months Found:::::");
//		}
//		return appointmentServiceDTO;
//	}
//}
