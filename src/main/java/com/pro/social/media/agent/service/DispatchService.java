package com.pro.social.media.agent.service;

import java.util.List;

import com.pro.social.media.agent.exceptions.DataNotFoundException;
import com.pro.social.media.agent.servicedto.DispatchServiceDto;
import com.pro.social.media.agent.servicedto.ListOfDispatchRecordServiceDto;


public interface DispatchService {
	
	public String  insertDispatch(DispatchServiceDto serviceDto , String request);
	
	
	public List<ListOfDispatchRecordServiceDto> getListOfDispatch(String strRequestID) throws DataNotFoundException;

}
