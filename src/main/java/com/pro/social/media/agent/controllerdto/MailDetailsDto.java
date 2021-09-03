package com.pro.social.media.agent.controllerdto;




import java.util.ArrayList;
import java.util.List;

import lombok.Data;
@Data
//@JsonIgnoreProperties
public class MailDetailsDto {
	
	private String ticketId;
	private String subject;
	private String from;
	private String messageBody;
	private String listOf_To_mailId;
	private String listOf_CC_mailId;
	private String listOf_BCC_mailId;
	private String mailSentDatel;
	private String attachmentType;
	private String strfrom_mailId_jkey;
    private String strSubject_jkey;
    private String oldMessageBody_jkey;
    private String newReplyMailBody_jkey;
    
    private  List attachmentPath ;

};







