package com.pro.social.media.agent.controller;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.pro.social.media.agent.controllerdto.MailDetailsControllerDto;
import com.pro.social.media.agent.controllerdto.ReplyMailControllerDto;
import com.pro.social.media.agent.controllerdto.Response;
import com.pro.social.media.agent.exceptions.DataNotFoundException;
import com.pro.social.media.agent.exceptions.InSufficientInputException;
import com.pro.social.media.agent.mapper.MailDetailsMapper;
import com.pro.social.media.agent.mapper.ReplayMailMapper;
import com.pro.social.media.agent.service.MailService;
import com.pro.social.media.agent.servicedto.MailDetailsServiceDto;
import com.pro.social.media.agent.wrapper.MailDetailsWrapper;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/getMailDetailsController")
@Slf4j
public class GetMailDetailsController {

	@Autowired
	@Qualifier("objMailServiceImpl")
	MailService objMailServiceImpl;

	@Autowired
	private HttpServletRequest request;

	@Autowired
	Environment environment;

	/**
	 * @author :Ranjeet kumar
	 * @throws DataNotFoundException
	 * @throws UnknownHostException
	 * @Des : get all mail details
	 * @URL : localhost:2706/getMailDetailsController/getMailDeatils
	 */
	@RequestMapping(value = "/getMailDeatils", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response getMailDeatils() throws InSufficientInputException, DataNotFoundException, UnknownHostException {
		MailDetailsMapper objMailDetailsMapper = new MailDetailsMapper();
		MailDetailsWrapper objMailDetailsWrapper = new MailDetailsWrapper();
		String strRequestID = request.getAttribute("reqid").toString();
		log.info(strRequestID + "::::getMailDeatils All mail details::");
		List<MailDetailsServiceDto> sDto = objMailServiceImpl.getMailDetails(strRequestID);
		objMailDetailsWrapper
				.setObjAppointmentControllerDTO(objMailDetailsMapper.conversionFromServiceToControllerDTO(sDto));
		objMailDetailsWrapper.setResponseCode(org.springframework.http.HttpStatus.OK.value());
		objMailDetailsWrapper.setStatus(org.springframework.http.HttpStatus.OK.getReasonPhrase());
		objMailDetailsWrapper.setCount(Integer.valueOf(sDto.size()).toString());
		objMailDetailsWrapper.setServerIp(InetAddress.getLocalHost().getHostAddress());
		objMailDetailsWrapper.setCount(environment.getProperty("local.server.port"));
		log.info(strRequestID + "::::OUTPUT:::::" + objMailDetailsWrapper.toString());
		return objMailDetailsWrapper;
	}

	/**
	 * @author:Ranjeet kumar
	 * @throws: DataNotFoundException
	 * @throws: UnknownHostException
	 * @Des :This method will perform to update status of reply mail.
	 * @URL :localhost:2706/getMailDetailsController/replyMail
	 */

	@CrossOrigin
	@RequestMapping(value = "/replyMail", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response replyMail(@RequestBody ReplyMailControllerDto controllerDto)
			throws InSufficientInputException, DataNotFoundException, UnknownHostException {
		log.info("replyMail method is executed::" + controllerDto);
		MailDetailsWrapper objMailDetailsWrapper = new MailDetailsWrapper();
		String strRequestID = request.getAttribute("reqid").toString();
		log.info(strRequestID + "::::replyMail method is executed inside GetMailDetailsCOntroller");
		if (controllerDto.getStrSubject_jkey() != null && controllerDto.getStrfrom_mailId_jkey() != null
				&& controllerDto.getNewReplyMailBody_jkey() != null && controllerDto.getOldMessageBody_jkey() != null) {
			String rtnReplyStatus = objMailServiceImpl.insertReplyMail(
					new ReplayMailMapper().conversionControllerDtoToServiceReplyDto(controllerDto), strRequestID);
			objMailDetailsWrapper.setResponseCode(HttpStatus.OK.value());
			objMailDetailsWrapper.setStatus(HttpStatus.OK.getReasonPhrase());
			objMailDetailsWrapper.setCount(rtnReplyStatus);
		} else {
			objMailDetailsWrapper.setResponseCode(HttpStatus.BAD_REQUEST.value());
			objMailDetailsWrapper.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
			throw new InSufficientInputException("");
		}
		log.info("::::OUTPUT::::::" + objMailDetailsWrapper.toString());
		return objMailDetailsWrapper;
	}

	@CrossOrigin
	@RequestMapping(value = "/insertMailStatus", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response insertMailStatus(@RequestBody MailDetailsControllerDto objMailDetailsControllerDto)
			throws InSufficientInputException, DataNotFoundException, UnknownHostException {
		log.info("insertMailStatus method is executed, Inside getMailDetailsController");
		log.info("mailId------------>=" + objMailDetailsControllerDto.getMail_id());
		log.info("sttausId------------>=" + objMailDetailsControllerDto.getMailStatus());
		MailDetailsMapper objMailDetailsMapper = new MailDetailsMapper();
		MailDetailsWrapper objMailDetailsWrapper = new MailDetailsWrapper();
		String strRequestID = request.getAttribute("reqid").toString();
		log.info(strRequestID + "::::getMailDeatils All mail details::");
		if (objMailDetailsControllerDto.getMail_id() != null && objMailDetailsControllerDto.getMailStatus() != null) {
			String rtnValueOfMT = objMailServiceImpl.insertMailStatus(
					objMailDetailsMapper.conversionFromControllerTOServiceDTO(objMailDetailsControllerDto),
					strRequestID);
			if (rtnValueOfMT != null) {
				objMailDetailsWrapper.setResponseCode(HttpStatus.OK.value());
				objMailDetailsWrapper.setStatus(HttpStatus.OK.getReasonPhrase());
				objMailDetailsWrapper.setCount(rtnValueOfMT);
			} else {
				objMailDetailsWrapper.setResponseCode(HttpStatus.BAD_REQUEST.value());
				objMailDetailsWrapper.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
				objMailDetailsWrapper.setCount(rtnValueOfMT);
			}
		} else {
			throw new InSufficientInputException("");
		}
		log.info("::::OUTPUT::::::" + objMailDetailsWrapper.toString());
		return objMailDetailsWrapper;
	}

	/**
	 * @author :Ranjeet kumar
	 * @throws DataNotFoundException
	 * @throws UnknownHostException
	 * @Des : get list of mail deatils based on mail id
	 * @URL :
	 *      localhost:2706/ERO/getMailDetailsController/getListOfMailDetailsBasedOnMailId
	 */
	@RequestMapping(value = "/getListOfMailDetailsBasedOnMailId", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response getListOfMailDetailsBasedOnMailId(@RequestBody MailDetailsControllerDto controllerDto)
			throws InSufficientInputException, DataNotFoundException, UnknownHostException {
		MailDetailsMapper objMailDetailsMapper = new MailDetailsMapper();
		MailDetailsWrapper objMailDetailsWrapper = new MailDetailsWrapper();
		String strRequestID = request.getAttribute("reqid").toString();
		log.info(strRequestID + "::::getListOfMailDetailsBasedOnMailId All mail details::");
		if (controllerDto.getMail_id() != null) {
			List<MailDetailsServiceDto> sDto = objMailServiceImpl.getListOfMailDetailsMID(
					new MailDetailsMapper().conversionFromControllerTOServiceDTO(controllerDto), strRequestID);
			objMailDetailsWrapper
					.setObjAppointmentControllerDTO(objMailDetailsMapper.conversionFromServiceToControllerDTO(sDto));
			objMailDetailsWrapper.setResponseCode(org.springframework.http.HttpStatus.OK.value());
			objMailDetailsWrapper.setStatus(org.springframework.http.HttpStatus.OK.getReasonPhrase());
			objMailDetailsWrapper.setCount(Integer.valueOf(sDto.size()).toString());
			objMailDetailsWrapper.setServerIp(InetAddress.getLocalHost().getHostAddress());
			objMailDetailsWrapper.setCount(environment.getProperty("local.server.port"));
			log.info(strRequestID + "::::OUTPUT:::::" + objMailDetailsWrapper.toString());
		} else {
			objMailDetailsWrapper.setResponseCode(HttpStatus.BAD_REQUEST.value());
			objMailDetailsWrapper.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
			throw new InSufficientInputException(controllerDto.toString());
		}
		return objMailDetailsWrapper;
	}

	/**
	 * @author :Ranjeet kumar
	 * @throws DataNotFoundException
	 * @throws UnknownHostException
	 * @Des : sendTemplate
	 * @URL :192.168.1.105:2706/getMailDetailsController/sendTemplate
	 */
	@RequestMapping(value = "/sendTemplate", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
	public Response sendTemplate(@RequestBody MailDetailsControllerDto controllerDto)
			throws InSufficientInputException, DataNotFoundException,UnknownHostException {
		MailDetailsWrapper wrapper = new MailDetailsWrapper();
		String strRequestID = request.getAttribute("reqid").toString();
		log.info(strRequestID + "::::sendTemplate method is executed::" + controllerDto.toString());
		if (controllerDto.getFromMail() != null && controllerDto.getSubject() != null) {
			try {
				String rtnTemplateStatus = objMailServiceImpl.insertTemplate(
						new MailDetailsMapper().conversionFromControllerTOServiceDTO(controllerDto), strRequestID);
				wrapper.setResponseCode(HttpStatus.OK.value());
				wrapper.setStatus(HttpStatus.OK.getReasonPhrase());
				wrapper.setCount(rtnTemplateStatus);
			} catch (NullPointerException e) {
				log.warn("Exception is comming into try-block inside insertDispatch method and DispatchController::"
						+ e.getMessage());
				e.printStackTrace();
			} catch (Exception e) {
				log.warn("Exception is comming into try-block inside insertDispatch method and DispatchController::"
						+ e.getMessage());
				e.printStackTrace();
			}
		} else {
			wrapper.setResponseCode(HttpStatus.BAD_REQUEST.value());
			wrapper.setStatus(HttpStatus.BAD_REQUEST.getReasonPhrase());
			throw new InSufficientInputException(controllerDto.toString());
		}

		return wrapper;

	}
}
