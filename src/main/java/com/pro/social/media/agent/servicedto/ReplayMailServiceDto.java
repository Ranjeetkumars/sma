package com.pro.social.media.agent.servicedto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ReplayMailServiceDto {
	
	
	private String ticketId;
	private String subject;
	private String from;
	private String messageBody;
	private String listOf_To_mailId;
	private String listOf_CC_mailId;
	private String listOf_BCC_mailId;
	private String mailSentDatel;
	private String attachmentPath;
	private String attachmentType;
	private String strfrom_mailId_jkey;
    private String strSubject_jkey;
    private String oldMessageBody_jkey;
    private String newReplyMailBody_jkey;

}
