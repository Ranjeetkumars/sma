package com.pro.social.media.agent.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import com.pro.social.media.agent.controllerdto.MailDetailsDto;
import com.pro.social.media.agent.dao.SocialMediaAgentDao;
import com.pro.social.media.agent.exceptions.DataNotFoundException;
import com.pro.social.media.agent.mapper.MailDetailsMapper;
import com.pro.social.media.agent.persistancedto.MailDetailsPersistanceDto;
import com.pro.social.media.agent.service.MailService;
import com.pro.social.media.agent.servicedto.MailDetailsServiceDto;
import com.pro.social.media.agent.servicedto.ReplyMailServiceDto;
import com.pro.social.media.agent.template.ConstructHtmlTemlpate;
import com.pro.social.media.agent.utills.AES;

import lombok.extern.slf4j.Slf4j;

@Service("objMailServiceImpl")
@Slf4j
public class MailServiceImpl implements MailService {

	@Autowired
	@Qualifier("objSocialMediaAgentDaoImpl")
	SocialMediaAgentDao objSocialMediaAgentDaoImpl;

	@Override
	public String insertMailDetails(MailDetailsDto objMailDetailsDto, String requestId) {
		String rtnStatus = null;
		log.info("insertMailDetails method is executed");
		String removeNameFromString[] = objMailDetailsDto.getFrom().split("<");
		String str = "select * from sp_insert_social_media_agent_mails('" + objMailDetailsDto.getSubject() + "','"
				+ objMailDetailsDto.getFrom() + "','" + objMailDetailsDto.getMessageBody()
				+ "','socialmediaagentdevelopment@gmail.com','','','" + objMailDetailsDto.getMailSentDatel()
				+ "',1,1,1)";
		log.info("insertMailDetails return status----->" + str);
		String status;
		try {
			status = objSocialMediaAgentDaoImpl.saveData(str);
			try {
				if (status != null && objMailDetailsDto.getAttachmentPath() != null) {
					log.info("size of attachment list---->" + objMailDetailsDto.getAttachmentPath().size());
					for (Object obj : objMailDetailsDto.getAttachmentPath()) {
						String rtnInsertAttachmentStatus = "select * from sp_insert_social_media_agent_attachments("
								+ Integer.parseInt(status) + ",'" + obj + "','" + objMailDetailsDto.getAttachmentType()
								+ "',1,1,1);";
						rtnStatus = objSocialMediaAgentDaoImpl.saveData(rtnInsertAttachmentStatus);
					}
				}
			} catch (NullPointerException e) {
				e.printStackTrace();
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		log.info("return insert status of  attachment files" + rtnStatus);
		return rtnStatus;
	}

	// select * from sp_select_social_media_mails(0);
	@Override
	public List<MailDetailsServiceDto> getMailDetails(String strRequestID) throws DataNotFoundException {
		MailDetailsMapper objMailDetailsMapper = new MailDetailsMapper();
		List<MailDetailsServiceDto> mailDetailsServiceDto = null;
		// String strQuery = "SELECT * FROM
		// sp_select_social_media_agent_mails_agent_attachments()";
		String strQuery = "select * from sp_select_social_media_mails(0);";
		log.info(strRequestID + ":::::::::::::" + strQuery);
		@SuppressWarnings("unchecked")
		List<Object[]> list = (List<Object[]>) objSocialMediaAgentDaoImpl.getData(strQuery);
		if (null != list && !list.isEmpty()) {
			List<MailDetailsPersistanceDto> gisPersDTOs = objMailDetailsMapper.conversiongetappointmentType(list);
			mailDetailsServiceDto = objMailDetailsMapper.conversionFromPersistanceTOServiceDTO(gisPersDTOs);
		} else {
			throw new DataNotFoundException(strRequestID + ":::::NogetMailDetails Found:::::");
		}
		return mailDetailsServiceDto;
	};

	@Override
	public String insertReplyMail(ReplyMailServiceDto objReplayMailServiceDto, String requestId) {
		String rtnInsertReplyMailStatus, rtnUpdateStatus = null;
		log.info("insertReplyMail method is executed inside MailServiceIml class");
		String replyStatusCode = "2";// reply status code is 2, becoz of that reason , i'm passing static value.
		String subject = objReplayMailServiceDto.getStrSubject_jkey();
		String replyTo = objReplayMailServiceDto.getStrfrom_mailId_jkey();
		String oldBody = objReplayMailServiceDto.getOldMessageBody_jkey();
		String newReplyContent = objReplayMailServiceDto.getNewReplyMailBody_jkey();

		// newReplyMailBody_jkey
		StringBuilder sBuilder = new StringBuilder(newReplyContent).append("\n").append("\n").append("\n")
				.append(oldBody);
		System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@=" + sBuilder.toString());
		try {
			String insertReplymail = "select * from sp_insert_social_media_agent_mails('" + subject + "','" + replyTo
					+ "','" + objReplayMailServiceDto.getNewReplyMailBody_jkey().toString().replace("&amp;", "&")
					+ "','socialmediaagentdevelopment@gmail.com','|','','now()',1,1,1)";
			log.info("print insertReplyMail store procedure with dynamic value::" + insertReplymail);
			rtnInsertReplyMailStatus = objSocialMediaAgentDaoImpl.saveData(insertReplymail);
			log.info("print insert reply mail status::" + rtnInsertReplyMailStatus);
			try {
				if (rtnInsertReplyMailStatus != null && rtnInsertReplyMailStatus.isEmpty() == false) {
					String updateMailStatus = "select * from sp_social_media_mail_status(" + rtnInsertReplyMailStatus
							+ "," + replyStatusCode + ")";
					log.info("print update statue  procedure with dynamic value::" + updateMailStatus);
					rtnUpdateStatus = objSocialMediaAgentDaoImpl.saveData(updateMailStatus);
					log.info("print return update status::" + rtnUpdateStatus);
				}
			} catch (NullPointerException e) {
				log.warn("Exception comming inside inner try block=" + e.getMessage());
				e.printStackTrace();
			} catch (Exception e) {
				log.warn("Exception comming inside inner try block=" + e.getMessage());
				e.printStackTrace();
			}
		} catch (Exception e) {
			log.warn("Exception comming inside outer try block=" + e.getMessage());
			e.printStackTrace();
		}
		return rtnUpdateStatus;
	}

	@Override
	public String insertMailStatus(MailDetailsServiceDto serviceDto, String strRequestID) throws DataNotFoundException {
		String rtnStatus = null;
		log.info("insertMailStatus method is executed inside MailServiceIml class");

		String str = " select * from sp_social_media_mail_status(" + serviceDto.getMail_id() + ","
				+ serviceDto.getMailStatus() + ");";
		log.info("display insertMailStatus store procedure::" + str);
		try {
			rtnStatus = objSocialMediaAgentDaoImpl.saveData(str);
			log.info("insert mail status rtn value:::" + rtnStatus);
		} catch (Exception e) {
			e.printStackTrace();
			log.warn("Error came into try-block=" + e.getMessage());
		}
		return rtnStatus;
	}

	@Override
	public List<MailDetailsServiceDto> getListOfMailDetailsMID(MailDetailsServiceDto serviceDto, String strRequestID)
			throws DataNotFoundException {

		MailDetailsMapper objMailDetailsMapper = new MailDetailsMapper();
		List<MailDetailsServiceDto> mailDetailsServiceDto = null;

		// select * from sp_select_social_media_mails(42) order by createddtm desc limit
		// 1;
		String strQuery = "select * from sp_select_social_media_mails(" + serviceDto.getMail_id()
				+ ") order by createddtm desc limit 1";
		log.info(strRequestID + ":::::::::::::" + strQuery);
		@SuppressWarnings("unchecked")
		List<Object[]> list = (List<Object[]>) objSocialMediaAgentDaoImpl.getData(strQuery);
		if (null != list && !list.isEmpty()) {
			List<MailDetailsPersistanceDto> gisPersDTOs = objMailDetailsMapper.conversiongetappointmentType(list);
			mailDetailsServiceDto = objMailDetailsMapper.conversionFromPersistanceTOServiceDTO(gisPersDTOs);
		} else {
			throw new DataNotFoundException(strRequestID + ":::::getListOfMailDetailsMID Found:::::");
		}
		return mailDetailsServiceDto;
	}

	@Override
	public String insertTemplate(MailDetailsServiceDto serviceDto, String strRequestID) throws DataNotFoundException {
		log.info("insertTemplate method is executed");
		
		String encriptedId = AES.encrypt(serviceDto.getMail_id());
		
		String rtnTemplateStatus = null;
		StringBuilder sb = new StringBuilder("select *  from sp_insert_hm_email_outbox_queue_trans(").append("0")
				.append(",0").append(",'").append(serviceDto.getFromMail()).append("','")
				.append(serviceDto.getSubject()).append("',").append("'','','").append(ConstructHtmlTemlpate
						.sendTemplate().replace("helprequestform.html", "helprequestform.html?"+encriptedId))
				.append("',3,27,false,1,1,1)");
		log.info("insertTemplate@@@@@@@@@@@@@@@@@@@@@::" + sb.toString());
		try {
			rtnTemplateStatus = objSocialMediaAgentDaoImpl.saveData(sb.toString());
		} catch (NullPointerException e) {
			log.warn("Exception comming into try-block" + e.getMessage() + ',' + "With RequestId=" + strRequestID);
			e.printStackTrace();
		} catch (Exception e) {
			log.warn("Exception comming into try-block" + e.getMessage() + ',' + "With RequestId=" + strRequestID);
			e.printStackTrace();
		}
		return rtnTemplateStatus;
	};
}
