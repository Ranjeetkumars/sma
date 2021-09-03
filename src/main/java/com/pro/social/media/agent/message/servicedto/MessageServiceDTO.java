/**
 * 
 */
package com.pro.social.media.agent.message.servicedto;

import lombok.Data;

/**
 * @author VENKAT_PRO
 *
 */
@Data
public class MessageServiceDTO {
	private String strMessage;
	private String transactionid;
	private String mobilenumber;
	private String sentdtm;
	private String sentby;
	private String messagestatus;
}
