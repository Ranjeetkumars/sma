/**
 * 
 */
package com.pro.social.media.agent.message.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.pro.social.media.agent.dao.SocialMediaAgentDao;
import com.pro.social.media.agent.message.mapper.MessageMapper;
import com.pro.social.media.agent.message.persistencedto.MessagePersistenceDTO;
import com.pro.social.media.agent.message.service.MessageServices;
import com.pro.social.media.agent.message.servicedto.MessageServiceDTO;




/**
 * @author VENKAT_PRO
 *
 */
@Service("objMessageServicesImpl")
public class MessageServicesImpl implements MessageServices{
	@Autowired
	@Qualifier("objSocialMediaAgentDaoImpl")
	SocialMediaAgentDao objSocialMediaAgentDaoImpl;

	@Override
	public List<MessageServiceDTO> getMessageTransactions(String strRequestID, String strMobileNumber) {
		// TODO Auto-generated method stub
		MessageMapper objMapper=new MessageMapper();
		String strQuery="SELECT * FROM sp_select_social_media_agent_msgs('"+strMobileNumber+"');";
		@SuppressWarnings("unchecked")
		List<Object[]> data=(List<Object[]>) objSocialMediaAgentDaoImpl.getData(strQuery);
		List<MessagePersistenceDTO> persistenceList=objMapper.convertObjectsArrayToMessagePersistenceDTO(data);
		List<MessageServiceDTO> servicedtos=objMapper.convertListMessagePersistenceDTOToMessageServiceDTO(persistenceList);
		return servicedtos;
	}

	
	@Override
	public String insertIntoMessage(MessageServiceDTO messageServiceDTO) {
		String strQuery="SELECT * FROM sp_insert_social_media_agent_msgs("+messageServiceDTO.getTransactionid()+",'"+messageServiceDTO.getMobilenumber()+"','"+messageServiceDTO.getStrMessage()+"',"+messageServiceDTO.getSentby()+",'"+messageServiceDTO.getMessagestatus()+"',1,1,1)";
		return objSocialMediaAgentDaoImpl.saveData(strQuery);
	}

}
