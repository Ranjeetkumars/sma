/**
 * 
 */
package com.pro.social.media.agent.message.mapper;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;

import com.pro.social.media.agent.message.controllerdto.MessageControllerDTO;
import com.pro.social.media.agent.message.persistencedto.MessagePersistenceDTO;
import com.pro.social.media.agent.message.servicedto.MessageServiceDTO;
import com.pro.social.media.agent.utills.CommonConstants;


/**
 * @author VENKAT_PRO
 *
 */
public class MessageMapper {

	public List<MessagePersistenceDTO> convertObjectsArrayToMessagePersistenceDTO(List<Object[]> data) {
		// TODO Auto-generated method stub
		List<MessagePersistenceDTO> messagePersistenceDTOs = new ArrayList<MessagePersistenceDTO>();
		for (Object[] objects : data) {
			MessagePersistenceDTO messagePersistenceDTO = new MessagePersistenceDTO();
			if (objects[1] != null) {
				messagePersistenceDTO.setTransactionid(objects[1].toString());
			} else {
				messagePersistenceDTO.setTransactionid(CommonConstants.DATA_NOT_AVIALABLE);
			}
			if (objects[2] != null) {
				messagePersistenceDTO.setMobilenumber(objects[2].toString());
			} else {
				messagePersistenceDTO.setMobilenumber(CommonConstants.DATA_NOT_AVIALABLE);
			}
			if (objects[3] != null) {
				messagePersistenceDTO.setStrMessage(objects[3].toString());
			} else {
				messagePersistenceDTO.setStrMessage(CommonConstants.DATA_NOT_AVIALABLE);
			}
			if (objects[4] != null) {
				messagePersistenceDTO.setSentdtm(objects[4].toString());
			} else {
				messagePersistenceDTO.setSentdtm(CommonConstants.DATA_NOT_AVIALABLE);
			}
			if (objects[5] != null) {
				messagePersistenceDTO.setSentby(objects[5].toString());
			} else {
				messagePersistenceDTO.setSentby(CommonConstants.DATA_NOT_AVIALABLE);
			}
			if (objects[6] != null) {
				messagePersistenceDTO.setMessagestatus(objects[6].toString());
			} else {
				messagePersistenceDTO.setMessagestatus(CommonConstants.DATA_NOT_AVIALABLE);
			}
			messagePersistenceDTOs.add(messagePersistenceDTO);
		}		
		return messagePersistenceDTOs;
	}

	public List<MessageServiceDTO> convertListMessagePersistenceDTOToMessageServiceDTO(
			List<MessagePersistenceDTO> persistenceList) {
		List<MessageServiceDTO> messageServicedto = new ArrayList<MessageServiceDTO>();
		for (MessagePersistenceDTO objects : persistenceList) {
			MessageServiceDTO dataControllerDTO = new MessageServiceDTO();
			BeanUtils.copyProperties(objects, dataControllerDTO);
			messageServicedto.add(dataControllerDTO);
		}	
		return messageServicedto;
	}

	public List<MessageControllerDTO> convertListMessageServiceDTOtoMessageControllerDTOs(
			List<MessageServiceDTO> messages) {
		// TODO Auto-generated method stub
		List<MessageControllerDTO> messageControllerDTOs = new ArrayList<MessageControllerDTO>();
		for (MessageServiceDTO objects : messages) {
			MessageControllerDTO dataControllerDTO = new MessageControllerDTO();
			BeanUtils.copyProperties(objects, dataControllerDTO);
			messageControllerDTOs.add(dataControllerDTO);
		}	
		return messageControllerDTOs;
	}

	public MessageServiceDTO convertIntoServiceDTO(MessageControllerDTO objMessageControllerDTO) {
		MessageServiceDTO serviceDTO=new MessageServiceDTO();
		BeanUtils.copyProperties(objMessageControllerDTO, serviceDTO);
		return serviceDTO;
	}

}
