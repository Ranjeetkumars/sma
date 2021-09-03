package com.pro.social.media.agent.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeansException;

import com.pro.social.media.agent.controllerdto.DispatchControllerDto;
import com.pro.social.media.agent.servicedto.DispatchServiceDto;

import lombok.extern.slf4j.Slf4j;

/**
 * This DispatchMapper class is develop for to convert one layer's properties to
 * another layer's properties, Here we are converting controllerDto to
 * ServiceDto properties.
 * 
 * @author Ranjeet kr.
 * @since 19Nov 2019
 */
@Slf4j
public class DispatchMapper {

	public DispatchServiceDto conversionControllerDtoToServiceDto(DispatchControllerDto controllerDto) {
		log.info("conversionControllerDtoToServiceDto method is executed inside DispatchMapper");
		DispatchServiceDto serviceDto = new DispatchServiceDto();
		try {
			BeanUtils.copyProperties(controllerDto, serviceDto);
		} catch (BeansException e) {
			log.warn(" CopyProperties is throwing Exception becoz , It is unable to copy source to target"+e.getMessage());
			e.printStackTrace();
		} catch (Exception e) {
			log.warn(" CopyProperties is throwing Exception becoz , It is unable to copy source to target"+e.getMessage());
			e.printStackTrace();
		}
		return serviceDto;

	}

}
