package com.pro.social.media.agent.servicedto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ListOfDispatchRecordServiceDto {
	
	private String serial_Id;
	private String parentMailId;
	private String callerNumber;
	private String callerName;
	private String email;
	private String energencyTypeId;
	private String victimName;
	private String victimAge;
	private String genderId;
	private String medicalChiefComplaintId;
	private String policeChiefComplaintId;
	private String fireChiefComplaintId;
	private String address;
	private String remarks;
	private String isActive;
	private String createdById;
	private String createdByRoleid;
	private String createdByModuleId;
	private String createddtm;

}
