package com.pro.social.media.agent.servicedto;



import lombok.Data;

/**
 * @author : Habiboon Patan
 * @Date : 2019-07-16
 */
@Data
public class AppointmentServiceDTO {
	private String eventId;
	private String time;
	private String date;
	private String patientId;
	private String victimId;
	private String description;
	private String userId;
	private String moduleId;
	private String roleid;
	
	private String futureTypeId;
	private String futureTypeName;
	
	private String futureDayCountId;
	private String futureCountDay;
	
	private String weekId;
	private String weekName;
	private String monthId;
	private String monthName;
	
}
