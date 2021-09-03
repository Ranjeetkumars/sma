package com.pro.social.media.agent.message.service;

import java.util.List;

import com.pro.social.media.agent.message.servicedto.MessageServiceDTO;


public interface MessageServices {
 public List<MessageServiceDTO> getMessageTransactions(String strRequestID,String strMobileNumber);

public String insertIntoMessage(MessageServiceDTO messageServiceDTO);
}
