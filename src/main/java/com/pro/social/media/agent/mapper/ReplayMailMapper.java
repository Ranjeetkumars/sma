package com.pro.social.media.agent.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import com.pro.social.media.agent.controllerdto.ReplyMailControllerDto;
import com.pro.social.media.agent.servicedto.ReplyMailServiceDto;

import lombok.extern.slf4j.Slf4j;

/**
 * This MailDetailsMapper class is developed for to convert one layer to another
 * layer.which are ControllerDto,ServiceDto,PersistanceDto etc.
 * 
 * @author Ranjeet kumar
 */
@Slf4j
public class ReplayMailMapper {

	public ReplyMailServiceDto conversionControllerDtoToServiceReplyDto(
			ReplyMailControllerDto objReplyMailControllerDto) {
		log.info("conversionControllerDtoToServiceDto method is executed inside DispatchMapper");
		ReplyMailServiceDto objReplyMailServiceDto = new ReplyMailServiceDto();
		try {
			BeanUtils.copyProperties(objReplyMailControllerDto, objReplyMailServiceDto);
		} catch (BeansException e) {
			log.warn(" CopyProperties is throwing Exception becoz , It is unable to copy source to target"
					+ e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {

			log.warn(" CopyProperties is throwing Exception becoz , It is unable to copy source to target"
					+ e.getMessage());
			e.printStackTrace();
		}
		return objReplyMailServiceDto;
	}

}
