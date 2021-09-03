package com.pro.social.media.agent.servicedto;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
public class ReplyMailServiceDto {

	private String strfrom_mailId_jkey;
	private String strSubject_jkey;
	private String oldMessageBody_jkey;
	private String newReplyMailBody_jkey;

}
