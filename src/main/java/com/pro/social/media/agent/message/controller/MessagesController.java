package com.pro.social.media.agent.message.controller;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.env.Environment;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.pro.social.media.agent.exceptions.DataNotFoundException;
import com.pro.social.media.agent.exceptions.InSufficientInputException;
import com.pro.social.media.agent.message.controllerdto.MessageControllerDTO;
import com.pro.social.media.agent.message.controllerdto.Response;
import com.pro.social.media.agent.message.mapper.MessageMapper;
import com.pro.social.media.agent.message.service.MessageServices;
import com.pro.social.media.agent.message.servicedto.MessageServiceDTO;
import com.pro.social.media.agent.message.wrappers.MessageWrapper;
import com.pro.social.media.agent.utills.GetTransactionID;
import com.pro.social.media.agent.utills.OnLineCalleres;
import lombok.extern.slf4j.Slf4j;
/**
 * @author VENKAT_PRO
 *
 */
@RestController
@RequestMapping("/messages")
@Slf4j
public class MessagesController {

	@Autowired
	@Qualifier("objMessageServicesImpl")
	private MessageServices objMessageServicesImpl;

	@Autowired
	private HttpServletRequest request;
	
	@Autowired
	Environment environment;
	
	//@Autowired
	//Publisher publisher;
	
	
	
	
    @RequestMapping(value = "/insertMessage", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response insertAgentMessage(@RequestBody MessageControllerDTO objMessageControllerDTO)
			throws InSufficientInputException, DataNotFoundException, UnknownHostException {
		String strRequestID = request.getAttribute("reqid").toString();
		MessageMapper messageMapper=new MessageMapper();
		MessageWrapper wraper=new MessageWrapper();
		log.info(strRequestID + "::::insertBreakDetails()::::INPUTS ARE::" + objMessageControllerDTO.toString());
		MessageServiceDTO messageServiceDTO=messageMapper.convertIntoServiceDTO(objMessageControllerDTO);
		String result =objMessageServicesImpl.insertIntoMessage(messageServiceDTO);		
		wraper.setResponseCode(org.springframework.http.HttpStatus.OK.value());
		wraper.setStatus(org.springframework.http.HttpStatus.OK.getReasonPhrase());
		wraper.setCount(result);
		String port = environment.getProperty("local.server.port");
		String ip = InetAddress.getLocalHost().getHostAddress();
		wraper.setServerIp(ip);
		wraper.setPort(port);
		return wraper;
	}
    
    
	@RequestMapping(value = "/allmessages/{mobilenumber}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response getMessages(@PathVariable("mobilenumber") String mobilenumber)
			throws InSufficientInputException, DataNotFoundException, UnknownHostException {
		List<MessageControllerDTO> controllerDTOs=new ArrayList<MessageControllerDTO>(); 
		MessageMapper messageMapper=new MessageMapper();
		MessageWrapper wraper=new MessageWrapper();
		String strRequestID = request.getAttribute("reqid").toString();
		log.info(strRequestID + "::::insertBreakDetails()::::INPUTS ARE::" +mobilenumber);
		List<MessageServiceDTO> messages=objMessageServicesImpl.getMessageTransactions(strRequestID, mobilenumber);
		controllerDTOs=messageMapper.convertListMessageServiceDTOtoMessageControllerDTOs(messages);
		wraper.setControllerDTOs(controllerDTOs);
		wraper.setResponseCode(org.springframework.http.HttpStatus.OK.value());
		wraper.setStatus(org.springframework.http.HttpStatus.OK.getReasonPhrase());
		wraper.setCount(controllerDTOs.size() + "");
		String port = environment.getProperty("local.server.port");
		String ip = InetAddress.getLocalHost().getHostAddress();
		wraper.setServerIp(ip);
		wraper.setPort(port);
		return wraper;
	}
	
	
//	
//	@RequestMapping("/send")
//	public String sendMessage(@RequestParam("msg") String msg){
//		System.out.println("*****"+msg);
//		//publisher.produceMsg(msg);
//		return "Successfully Msg Sent";
//	}
	
	
	
	
	@RequestMapping(value = "/insertTelcom", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response insertTelecomMessage(@RequestBody MessageControllerDTO objMessageControllerDTO)
			throws InSufficientInputException, DataNotFoundException, UnknownHostException {
		String strRequestID = request.getAttribute("reqid").toString();
		MessageMapper messageMapper=new MessageMapper();
		MessageWrapper wraper=new MessageWrapper();		
		objMessageControllerDTO.setTransactionid(GetTransactionID.getTransactionID()+"");
		log.info(strRequestID + "::::insertBreakDetails()::::INPUTS ARE::" + objMessageControllerDTO.toString());
		MessageServiceDTO messageServiceDTO=messageMapper.convertIntoServiceDTO(objMessageControllerDTO);
		String result =objMessageServicesImpl.insertIntoMessage(messageServiceDTO);	
		//publisher.sendMessageToAgent(objMessageControllerDTO);
		wraper.setResponseCode(org.springframework.http.HttpStatus.OK.value());
		wraper.setStatus(org.springframework.http.HttpStatus.OK.getReasonPhrase());
		wraper.setCount(result);
		String port = environment.getProperty("local.server.port");
		String ip = InetAddress.getLocalHost().getHostAddress();
		wraper.setServerIp(ip);
		wraper.setPort(port);
		return wraper;
	}
	
	
	
	@RequestMapping(value = "/allagents", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public Response getOnlineAgents()
			throws InSufficientInputException, DataNotFoundException, UnknownHostException {
		//List<MessageControllerDTO> controllerDTOs=new ArrayList<MessageControllerDTO>(); 
		MessageMapper messageMapper=new MessageMapper();
		MessageWrapper wraper=new MessageWrapper();
		String strRequestID = request.getAttribute("reqid").toString();				
		wraper.setControllerDTOs(new ArrayList<MessageControllerDTO> (OnLineCalleres.onlineAgents.values()));
		wraper.setResponseCode(org.springframework.http.HttpStatus.OK.value());
		wraper.setStatus(org.springframework.http.HttpStatus.OK.getReasonPhrase());
		wraper.setCount(OnLineCalleres.onlineAgents.values().size() + "");
		String port = environment.getProperty("local.server.port");
		String ip = InetAddress.getLocalHost().getHostAddress();
		wraper.setServerIp(ip);
		wraper.setPort(port);
		return wraper;
	}
	
	
}
