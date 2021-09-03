package com.pro.social.media.agent.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import com.pro.social.media.agent.controllerdto.ListOfDispatchRecordControllerDto;
import com.pro.social.media.agent.persistancedto.ListOfDispatchRecordPersistanceDto;
import com.pro.social.media.agent.servicedto.ListOfDispatchRecordServiceDto;
import com.pro.social.media.agent.utills.CommonConstants;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ListOfDispatchRecordMapper {

	public List<ListOfDispatchRecordControllerDto> conversionFromServiceToControllerDTO(
			List<ListOfDispatchRecordServiceDto> dataServiceDTOs) {
		log.info("conversionFromServiceToControllerDTO method is executed inside MailDetailsMapper");
		List<ListOfDispatchRecordControllerDto> controllerDTOs = new ArrayList<ListOfDispatchRecordControllerDto>();
		try {
			for (ListOfDispatchRecordServiceDto objects : dataServiceDTOs) {
				ListOfDispatchRecordControllerDto dataControllerDTO = new ListOfDispatchRecordControllerDto();
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

	public List<ListOfDispatchRecordServiceDto> conversionFromPersistanceTOServiceDTO(
			List<ListOfDispatchRecordPersistanceDto> persistenceDTOs) {
		log.info("conversionFromPersistanceTOServiceDTO method is executed inside MailDetailsMapper");
		List<ListOfDispatchRecordServiceDto> appointmentServiceDTO = new ArrayList<ListOfDispatchRecordServiceDto>();
		try {
			for (ListOfDispatchRecordPersistanceDto objects : persistenceDTOs) {
				ListOfDispatchRecordServiceDto objAppointmentServiceDTO = new ListOfDispatchRecordServiceDto();
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

	public ListOfDispatchRecordServiceDto conversionFromControllerTOServiceDTO(
			ListOfDispatchRecordControllerDto controllerDTO) {
		log.info("conversionFromServiceToControllerDTO method is executed inside MailDetailsMapper");
		ListOfDispatchRecordServiceDto serviceDTO = new ListOfDispatchRecordServiceDto();
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

	public List<ListOfDispatchRecordPersistanceDto> conversiongetappointmentType(List<Object[]> list) {
		List<ListOfDispatchRecordPersistanceDto> persistanceDTO = new ArrayList<ListOfDispatchRecordPersistanceDto>();
		for (Object[] objects : list) {
			ListOfDispatchRecordPersistanceDto persistanceDTO1 = new ListOfDispatchRecordPersistanceDto();

			if (objects[0] != null) {
				persistanceDTO1.setSerial_Id(objects[0].toString());
			} else {
				persistanceDTO1.setSerial_Id(CommonConstants.DATA_NOT_AVIALABLE);
			}

			if (objects[1] != null) {
				persistanceDTO1.setParentMailId(objects[1].toString());
			} else {
				persistanceDTO1.setParentMailId(CommonConstants.DATA_NOT_AVIALABLE);
			}
			if (objects[2] != null) {
				persistanceDTO1.setCallerNumber(objects[2].toString());
			} else {
				persistanceDTO1.setCallerNumber(CommonConstants.DATA_NOT_AVIALABLE);
			}
			if (objects[3] != null) {
				persistanceDTO1.setCallerName(objects[3].toString());
			} else {
				persistanceDTO1.setCallerName(CommonConstants.DATA_NOT_AVIALABLE);
			}

			if (objects[4] != null) {
				persistanceDTO1.setEmail(objects[4].toString());
			} else {
				persistanceDTO1.setEmail(CommonConstants.DATA_NOT_AVIALABLE);
			}
			if (objects[5] != null) {
				persistanceDTO1.setEnergencyTypeId(objects[5].toString());
			} else {
				persistanceDTO1.setEnergencyTypeId(CommonConstants.DATA_NOT_AVIALABLE);
			}
			if (objects[6] != null) {
				persistanceDTO1.setVictimName(objects[6].toString());
			} else {
				persistanceDTO1.setVictimName(CommonConstants.DATA_NOT_AVIALABLE);
			}
			if (objects[7] != null) {
				persistanceDTO1.setVictimAge(objects[7].toString());

			} else {
				persistanceDTO1.setVictimAge(CommonConstants.DATA_NOT_AVIALABLE);
			}

			if (objects[8] != null) {
				persistanceDTO1.setGenderId(objects[8].toString());
			} else {
				persistanceDTO1.setGenderId(CommonConstants.DATA_NOT_AVIALABLE);
			}

			if (objects[9] != null) {
				persistanceDTO1.setMedicalChiefComplaintId(objects[9].toString());
			} else {
				persistanceDTO1.setMedicalChiefComplaintId(CommonConstants.DATA_NOT_AVIALABLE);
			}

			if (objects[10] != null) {
				persistanceDTO1.setPoliceChiefComplaintId(objects[10].toString());
			} else {
				persistanceDTO1.setPoliceChiefComplaintId(CommonConstants.DATA_NOT_AVIALABLE);
			}
			if (objects[11] != null) {
				persistanceDTO1.setFireChiefComplaintId(objects[11].toString());
			} else {
				persistanceDTO1.setFireChiefComplaintId(CommonConstants.DATA_NOT_AVIALABLE);
			}
			if (objects[12] != null) {
				persistanceDTO1.setAddress(objects[12].toString());
			} else {
				persistanceDTO1.setAddress(CommonConstants.DATA_NOT_AVIALABLE);
			}
			if (objects[13] != null) {
				persistanceDTO1.setRemarks(objects[13].toString());
			} else {
				persistanceDTO1.setRemarks(CommonConstants.DATA_NOT_AVIALABLE);
			}

			if (objects[14] != null) {
				persistanceDTO1.setIsActive(objects[14].toString());
			} else {
				persistanceDTO1.setIsActive(CommonConstants.DATA_NOT_AVIALABLE);
			}

			if (objects[15] != null) {
				persistanceDTO1.setCreatedById(objects[15].toString());
			} else {
				persistanceDTO1.setCreatedById(CommonConstants.DATA_NOT_AVIALABLE);
			}
			if (objects[16] != null) {
				persistanceDTO1.setCreatedByRoleid(objects[16].toString());
			} else {
				persistanceDTO1.setCreatedByRoleid(CommonConstants.DATA_NOT_AVIALABLE);
			}

			if (objects[17] != null) {
				persistanceDTO1.setCreatedByModuleId(objects[17].toString());
			} else {
				persistanceDTO1.setCreatedByModuleId(CommonConstants.DATA_NOT_AVIALABLE);
			}
			if (objects[18] != null) {
				persistanceDTO1.setCreateddtm(objects[18].toString());
			} else {
				persistanceDTO1.setCreateddtm(CommonConstants.DATA_NOT_AVIALABLE);
			}

			persistanceDTO.add(persistanceDTO1);
		}
		return persistanceDTO;
	}

}
