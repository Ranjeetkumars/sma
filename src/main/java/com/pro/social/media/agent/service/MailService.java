package com.pro.social.media.agent.service;

import java.util.List;
import com.pro.social.media.agent.controllerdto.MailDetailsDto;
import com.pro.social.media.agent.exceptions.DataNotFoundException;
import com.pro.social.media.agent.servicedto.MailDetailsServiceDto;
import com.pro.social.media.agent.servicedto.ReplyMailServiceDto;

public interface MailService {

	public String insertMailDetails(MailDetailsDto dataInfo, String requestId);

	public String insertReplyMail(ReplyMailServiceDto objReplyServiceDto, String requestId);

	public List<MailDetailsServiceDto> getMailDetails(String strRequestID) throws DataNotFoundException;

	public List<MailDetailsServiceDto> getListOfMailDetailsMID(MailDetailsServiceDto objMailDetailsServiceDto,
			String strRequestID) throws DataNotFoundException;

	public String insertMailStatus(MailDetailsServiceDto serviceDto, String strRequestID) throws DataNotFoundException;
	
	
	
	public String insertTemplate(MailDetailsServiceDto serviceDto, String strRequestID) throws DataNotFoundException;

}
