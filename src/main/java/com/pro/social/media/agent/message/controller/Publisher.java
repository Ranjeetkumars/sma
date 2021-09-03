package com.pro.social.media.agent.message.controller;
//package com.pro.socialmedia.controller;
//
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.stereotype.Component;
//
//import com.pro.socialmedia.controllerdto.MessageControllerDTO;
//import com.pro.socialmedia.utill.OnLineCalleres;
//@Component
//public class Publisher {
//	@Qualifier("amqpTemplate")
//	@Autowired
//	private AmqpTemplate amqpTemplate;
//	
//	@Value("${jsa.rabbitmq.exchange}")
//	private String exchange;
//	
//	@Value("${jsa.rabbitmq.routingkey}")
//	private String routingKey;
//	
//	public void produceMsg(String msg){
//		amqpTemplate.convertAndSend(exchange, routingKey, msg);
//		System.out.println("Send msg = " + msg);
//	}
//	
//	public void sendMessageToAgent(MessageControllerDTO msg){
//		OnLineCalleres.onlineAgents.put(msg.getMobilenumber(), msg);
//		amqpTemplate.convertAndSend(exchange, routingKey, msg);
//		System.out.println("Send msg = " + msg);
//	}
//}
