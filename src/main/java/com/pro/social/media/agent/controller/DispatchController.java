package com.pro.social.media.agent.controller;


import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.pro.social.media.agent.controllerdto.DispatchControllerDto;
import com.pro.social.media.agent.controllerdto.Response;
import com.pro.social.media.agent.controllerdto.StoreDescriptKey;
import com.pro.social.media.agent.exceptions.DataNotFoundException;
import com.pro.social.media.agent.exceptions.InSufficientInputException;
import com.pro.social.media.agent.mapper.DispatchMapper;
import com.pro.social.media.agent.mapper.ListOfDispatchRecordMapper;
import com.pro.social.media.agent.service.DispatchService;
import com.pro.social.media.agent.servicedto.ListOfDispatchRecordServiceDto;
import com.pro.social.media.agent.utills.AES;
import com.pro.social.media.agent.wrapper.DecriptedWrapper;
import com.pro.social.media.agent.wrapper.DispatchWrapper;
import com.pro.social.media.agent.wrapper.ListOfDispatchRecordWrapper;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class DispatchController {

	@Autowired
	@Qualifier("dispatchServiceImpl")
	DispatchService dispatchServiceImpl;
	@Autowired
	private HttpServletRequest request;
	@Autowired
	Environment environment;

	/**
	 * @author :Ranjeet kumar
	 * @throws DataNotFoundException
	 * @throws UnknownHostException
	 * @Des : insertDispatc form's Data in DB
	 * @URL : 192.168.1.105:2706/api/dispatch_con/insertDispatch
	 */
	@RequestMapping(value = "/api/dispatch_con/insertDispatch", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response insertDispatch(@RequestBody DispatchControllerDto controllerDto)
			throws InSufficientInputException, DataNotFoundException, UnknownHostException {
		DispatchWrapper wrapper = new DispatchWrapper();
		String strRequestID = request.getAttribute("reqid").toString();
		log.info(strRequestID + "::::insertDispatch method is executed::"+controllerDto.toString());
		if (controllerDto.getCallerName() != null && controllerDto.getCallerNumber() != null
				&& controllerDto.getAddress() != null && controllerDto.getEmergencyType() != null
				&& controllerDto.getGender() != null
				&& controllerDto.getMedicalChiefCompType() != null && controllerDto.getRemarks() != null
				&& controllerDto.getAddress() != null && controllerDto.getVictimName() != null) {
			
			log.info("inside if condition");
			try {
				String returnStatus = dispatchServiceImpl.insertDispatch(
						new DispatchMapper().conversionControllerDtoToServiceDto(controllerDto), request.toString());
				
				wrapper.setResponseCode(HttpStatus.OK.value());
				wrapper.setStatus(HttpStatus.OK.getReasonPhrase());
				wrapper.setCount(returnStatus);
			
			} catch (NullPointerException e) {
				log.warn("Exception is comming into try-block inside insertDispatch method and DispatchController::"
						+ e.getMessage());
				e.printStackTrace();
			} catch (Exception e) {
				log.warn("Exception is comming into try-block inside insertDispatch method and DispatchController::"
						+ e.getMessage());
				e.printStackTrace();
			}
		} 
		
		else {
			wrapper.setResponseCode(HttpStatus.BAD_REQUEST.value());
			wrapper.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
			throw new InSufficientInputException(controllerDto.toString());
		}
		log.info("::::OUTPUT::::::" + controllerDto.toString());
		return wrapper;
	}
	
	
	
	
	/**
	 * @author :Ranjeet kumar
	 * @throws DataNotFoundException
	 * @throws UnknownHostException
	 * @Des : through listOfDispatch method , we can get all dispatched data
	 * @URL : 192.168.1.105:2706/api/version_1/getMailDeatils
	 */
	@RequestMapping(value = "/api/version_1/getListOfDispatch", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response listOfDispatch() throws InSufficientInputException, DataNotFoundException, UnknownHostException {
		ListOfDispatchRecordWrapper wrapper = new ListOfDispatchRecordWrapper();
		String strRequestID = request.getAttribute("reqid").toString();
		log.info(strRequestID + "::::listOfDispatch All mail details::");
		List<ListOfDispatchRecordServiceDto> sDto = dispatchServiceImpl.getListOfDispatch(strRequestID);
		wrapper.setControllerDto(new ListOfDispatchRecordMapper ().conversionFromServiceToControllerDTO(sDto));
		wrapper.setResponseCode(org.springframework.http.HttpStatus.OK.value());
		wrapper.setStatus(org.springframework.http.HttpStatus.OK.getReasonPhrase());
		//wrapper.setCount(Integer.valueOf(sDto.size()).toString());
		wrapper.setServerIp(InetAddress.getLocalHost().getHostAddress());
		wrapper.setCount(environment.getProperty("local.server.port"));
		log.info(strRequestID + "::::OUTPUT:::::" + wrapper.toString());
		return wrapper;
	}
	
	@RequestMapping(value = "/api/version_1/getDescriptedId",method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE,consumes=MediaType.APPLICATION_JSON_VALUE)
	public Response getDescriptedId(@RequestBody StoreDescriptKey objStoreDescriptKey) throws InSufficientInputException, DataNotFoundException, UnknownHostException {
		log.info("getDescriptedId method  is executed");
		DecriptedWrapper wrapper = new DecriptedWrapper();
		if(objStoreDescriptKey.getId()!=null) {
		String decripedVlue = AES.decrypt(objStoreDescriptKey.getId());
		wrapper.setDescripted(decripedVlue);
		wrapper.setResponseCode(HttpStatus.OK.value());
		wrapper.setStatus(HttpStatus.OK.getReasonPhrase());
		}
		else {
			wrapper.setResponseCode(HttpStatus.BAD_REQUEST.value());
			wrapper.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
			throw new InSufficientInputException("");
		}
		return wrapper;
	}

	
}
