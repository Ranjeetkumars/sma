package com.pro.social.media.agent.servicedto;

import lombok.Data;

@Data
public class DispatchServiceDto {
	
	private String callerNumber;
	private String callerName;
	private String email;
	private String emergencyType;
	private String victimName;
	private String victimAge;
	private String gender;
	private String medicalChiefCompType;
	private String policeChiefCompType;
	private String fireChiefCompType;
	private String address;
	private String remarks;
	

}
