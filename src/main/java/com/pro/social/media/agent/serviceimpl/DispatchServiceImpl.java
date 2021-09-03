package com.pro.social.media.agent.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.pro.social.media.agent.dao.SocialMediaAgentDao;
import com.pro.social.media.agent.exceptions.DataNotFoundException;
import com.pro.social.media.agent.mapper.ListOfDispatchRecordMapper;
import com.pro.social.media.agent.persistancedto.ListOfDispatchRecordPersistanceDto;
import com.pro.social.media.agent.service.DispatchService;
import com.pro.social.media.agent.servicedto.DispatchServiceDto;
import com.pro.social.media.agent.servicedto.ListOfDispatchRecordServiceDto;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service("dispatchServiceImpl")
@SuppressWarnings("unchecked")
public class DispatchServiceImpl implements DispatchService {

	@Autowired
	@Qualifier("objSocialMediaAgentDaoImpl")
	SocialMediaAgentDao objSocialMediaAgentDaoImpl;

	@Override
	public String insertDispatch(DispatchServiceDto serviceDto, String request) {
		log.info("insertDispatch method is executed inside DispatchServiceImpl class::" + serviceDto);
		String rtnInsertDispatchStatus = null;
		try {
			String str = "select * from sp_insert_update_social_media_mail_dispatch(115,'"
					+ serviceDto.getCallerNumber() + "','" + serviceDto.getCallerName() + "','" + serviceDto.getEmail()
					+ "','" + serviceDto.getEmergencyType() + "','" + serviceDto.getVictimName() + "',"
					+ serviceDto.getVictimAge() + "," + serviceDto.getGender() + ","
					+ serviceDto.getMedicalChiefCompType() + "," + serviceDto.getPoliceChiefCompType() + ","
					+ serviceDto.getFireChiefCompType() + ",'" + serviceDto.getAddress() + "','"
					+ serviceDto.getRemarks() + "','true',1,1,1)";
			log.info(request + ":::::::::::::" + str);
			rtnInsertDispatchStatus = objSocialMediaAgentDaoImpl.saveData(str);

		} catch (NullPointerException e) {
			log.warn("Exception is occuring into try-block::" + e.getMessage());
			log.warn("with request No::" + request);
			e.printStackTrace();
		} catch (Exception e) {
			log.warn("Exception is occuring into try-block::" + e.getMessage());
			log.warn("with request No::" + request);
			e.printStackTrace();
		}
		return rtnInsertDispatchStatus;
	}

	@Override
	public List<ListOfDispatchRecordServiceDto> getListOfDispatch(String strRequestID) throws DataNotFoundException {
		log.info("getListOfDispatch method is executed inside DispatchServiceImpl::" + strRequestID);
		ListOfDispatchRecordMapper mapper = new ListOfDispatchRecordMapper();
		List<ListOfDispatchRecordServiceDto> serviceDto = null;
		try {
			
			
			String str = "select * from sp_select_social_media_dispatch_details_trans()";
			log.info(strRequestID + ":::::::::::::" + str);
			List<Object[]> list = (List<Object[]>) objSocialMediaAgentDaoImpl.getData(str);
			if (null != list && !list.isEmpty()) {
				List<ListOfDispatchRecordPersistanceDto> gisPersDTOs = mapper.conversiongetappointmentType(list);
				serviceDto = mapper.conversionFromPersistanceTOServiceDTO(gisPersDTOs);
			} else {
				throw new DataNotFoundException(strRequestID + ":::::NogetMailDetails Found:::::");
			}
		} catch (NullPointerException e) {
			log.warn("Exception  comming into try- block of getListOfDispatch method::" + e.getMessage());
			log.warn("with request No::" + strRequestID);
			e.printStackTrace();
		} catch (Exception e) {
			log.warn("Exception  comming into try- block of getListOfDispatch method::" + e.getMessage());
			log.warn("with request No::" + strRequestID);
			e.printStackTrace();
		}

		return serviceDto;
	}

}
