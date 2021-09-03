package com.pro.social.media.agent.dao;

import java.util.List;

import com.pro.social.media.agent.exceptions.DataNotFoundException;

public interface SocialMediaAgentDao {	
	public List<?> getData(String strQuery);

	public String saveData(String strQuery);

	public String getSingleData(String strQuery) throws DataNotFoundException;
}
