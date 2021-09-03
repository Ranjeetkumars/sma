package com.pro.social.media.agent.persistancedto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
public class MailDetailsPersistanceDto {
	
//	private String smsTicketId;
//	
//	private String from;
//	private String message_body;
//	private String listOfToMailIds;
//	private String listOfCcMailIds;
//	private String listOfBccMailIds;
//	private String sentDate;
//	private String attachmentPath;
//	private String attachmentType;
	
	
	
	private String mail_id;
	private String subject;
	private String fromName;
	private String fromMail;
	private String messageBody;
	private String toMailids;
	private String ccMailIds;
	private String bccMailIds;
	private String mailReceviedDate;
	private String mailStatus;
	private String chainType;
	private String parentId;
	private String createdById;
	private String createdByRoleId;
	private String createdByModuleId;
	private String createddtm;
	private String mailCount;


}
