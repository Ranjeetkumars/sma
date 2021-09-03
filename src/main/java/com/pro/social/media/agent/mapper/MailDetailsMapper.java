package com.pro.social.media.agent.mapper;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;


import com.pro.social.media.agent.controllerdto.MailDetailsControllerDto;
import com.pro.social.media.agent.controllerdto.MailDetailsDto;
import com.pro.social.media.agent.persistancedto.MailDetailsPersistanceDto;
import com.pro.social.media.agent.servicedto.MailDetailsServiceDto;
import com.pro.social.media.agent.utills.CommonConstants;
import lombok.extern.slf4j.Slf4j;

/**
 * This MailDetailsMapper class is developed for to convert one layer to another
 * layer.which are ControllerDto,ServiceDto,PersistanceDto etc.
 * 
 * @author Ranjeet kumar
 */
@Slf4j
public class MailDetailsMapper {

	public List<MailDetailsServiceDto> conversionFromPersistanceTOServiceDTO(
			List<MailDetailsPersistanceDto> persistenceDTOs) {
		log.info("conversionFromPersistanceTOServiceDTO method is executed inside MailDetailsMapper");
		List<MailDetailsServiceDto> appointmentServiceDTO = new ArrayList<MailDetailsServiceDto>();
		try {
			for (MailDetailsPersistanceDto objects : persistenceDTOs) {
				MailDetailsServiceDto objAppointmentServiceDTO = new MailDetailsServiceDto();
				BeanUtils.copyProperties(objects, objAppointmentServiceDTO);
				appointmentServiceDTO.add(objAppointmentServiceDTO);
			}
		} catch (BeansException e) {
			log.warn("copyProperties is unable to copy source to traget::" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			log.warn("copyProperties is unable to copy source to traget::" + e.getMessage());
			e.printStackTrace();
		}
		return appointmentServiceDTO;
	}

	public List<MailDetailsControllerDto> conversionFromServiceToControllerDTO(
			List<MailDetailsServiceDto> dataServiceDTOs) {
		log.info("conversionFromServiceToControllerDTO method is executed inside MailDetailsMapper");
		List<MailDetailsControllerDto> controllerDTOs = new ArrayList<MailDetailsControllerDto>();
		try {
			for (MailDetailsServiceDto objects : dataServiceDTOs) {
				MailDetailsControllerDto dataControllerDTO = new MailDetailsControllerDto();
				BeanUtils.copyProperties(objects, dataControllerDTO);
				controllerDTOs.add(dataControllerDTO);
			}
		} catch (BeansException e) {
			log.warn("copyProperties is unable to copy source to traget::" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			log.warn("copyProperties is unable to copy source to traget::" + e.getMessage());
			e.printStackTrace();
		}
		return controllerDTOs;
	}

	public MailDetailsServiceDto conversionFromMailDetailsDtoServiceDTO(MailDetailsDto controllerDTO) {
		log.info("conversionFromMailDetailsDtoServiceDTO method is executed inside MailDetailsMapper");
		MailDetailsServiceDto serviceDTO = new MailDetailsServiceDto();
		try {
			BeanUtils.copyProperties(controllerDTO, serviceDTO);
		} catch (BeansException e) {
			log.warn("copyProperties is unable to copy source to traget::" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			log.warn("copyProperties is unable to copy source to traget::" + e.getMessage());
			e.printStackTrace();
		}
		return serviceDTO;
	}

	public MailDetailsServiceDto conversionFromControllerTOServiceDTO(MailDetailsControllerDto controllerDTO) {
		log.info("conversionFromServiceToControllerDTO method is executed inside MailDetailsMapper");
		MailDetailsServiceDto serviceDTO = new MailDetailsServiceDto();
		try {
			BeanUtils.copyProperties(controllerDTO, serviceDTO);
		} catch (BeansException e) {
			log.warn("copyProperties is unable to copy source to traget::" + e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			log.warn("copyProperties is unable to copy source to traget::" + e.getMessage());
			e.printStackTrace();
		}
		return serviceDTO;
	}

	public List<MailDetailsPersistanceDto> conversiongetappointmentType(List<Object[]> list) {
		List<MailDetailsPersistanceDto> persistanceDTO = new ArrayList<MailDetailsPersistanceDto>();
		for (Object[] objects : list) {
			MailDetailsPersistanceDto persistanceDTO1 = new MailDetailsPersistanceDto();

			if (objects[0] != null) {
				persistanceDTO1.setMail_id(objects[0].toString());
			} else {
				persistanceDTO1.setMail_id(CommonConstants.DATA_NOT_AVIALABLE);
			}

			if (objects[1] != null) {
				persistanceDTO1.setSubject(objects[1].toString());
			} else {
				persistanceDTO1.setSubject(CommonConstants.DATA_NOT_AVIALABLE);
			}

			if (objects[2] != null) {
				persistanceDTO1.setFromMail(objects[2].toString().replace("<", " "));
			} else {
				persistanceDTO1.setFromMail(CommonConstants.DATA_NOT_AVIALABLE);
			}
			if (objects[3] != null) {
				persistanceDTO1.setMessageBody(objects[3].toString());
			} else {
				persistanceDTO1.setMessageBody(CommonConstants.DATA_NOT_AVIALABLE);
			}

			if (objects[4] != null) {
				persistanceDTO1.setToMailids(objects[4].toString());
			} else {
				persistanceDTO1.setToMailids(CommonConstants.DATA_NOT_AVIALABLE);
			}
			if (objects[5] != null) {
				persistanceDTO1.setCcMailIds(objects[5].toString());
			} else {
				persistanceDTO1.setCcMailIds(CommonConstants.DATA_NOT_AVIALABLE);
			}
			if (objects[6] != null) {
				persistanceDTO1.setBccMailIds(objects[6].toString());
			} else {
				persistanceDTO1.setBccMailIds(CommonConstants.DATA_NOT_AVIALABLE);
			}
			if (objects[7] != null) {
				persistanceDTO1.setMailReceviedDate(objects[7].toString());

			} else {
				persistanceDTO1.setMailReceviedDate(CommonConstants.DATA_NOT_AVIALABLE);
			}

			if (objects[8] != null) {
				persistanceDTO1.setMailStatus(objects[8].toString());
			} else {
				persistanceDTO1.setMailStatus(CommonConstants.DATA_NOT_AVIALABLE);
			}

			if (objects[9] != null) {
				persistanceDTO1.setChainType(objects[9].toString());
			} else {
				persistanceDTO1.setChainType(CommonConstants.DATA_NOT_AVIALABLE);
			}

			if (objects[10] != null) {
				persistanceDTO1.setParentId(objects[10].toString());
			} else {
				persistanceDTO1.setParentId(CommonConstants.DATA_NOT_AVIALABLE);
			}
			if (objects[12] != null) {
				persistanceDTO1.setChainType(objects[12].toString());
			} else {
				persistanceDTO1.setChainType(CommonConstants.DATA_NOT_AVIALABLE);
			}

			if (objects[14] != null) {
				persistanceDTO1.setCreateddtm(objects[14].toString());
			} else {
				persistanceDTO1.setCreateddtm(CommonConstants.DATA_NOT_AVIALABLE);
			}
			if (objects[15] != null) {
				persistanceDTO1.setMailCount(objects[15].toString());
			} else {
				persistanceDTO1.setMailCount(CommonConstants.DATA_NOT_AVIALABLE);
			}
			persistanceDTO.add(persistanceDTO1);
		}
		return persistanceDTO;
	}

}
