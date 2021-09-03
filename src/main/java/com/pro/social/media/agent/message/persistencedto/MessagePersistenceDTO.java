/**
 * 
 */
package com.pro.social.media.agent.message.persistencedto;

import lombok.Data;

/**
 * @author VENKAT_PRO
 *
 */
@Data
public class MessagePersistenceDTO {
	private String strMessage;
	private String transactionid;
	private String mobilenumber;
	private String sentdtm;
	private String sentby;
	private String messagestatus;
}
