/**
 * 
 */
package com.pro.social.media.agent.message.wrappers;

import java.io.Serializable;
import java.util.List;

import com.pro.social.media.agent.message.controllerdto.MessageControllerDTO;
import com.pro.social.media.agent.message.controllerdto.Response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author VENKAT_PRO
 *
 */
@ToString
@Setter
@Getter
public class MessageWrapper extends Response implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 6095109055642551016L;
	private List<MessageControllerDTO> controllerDTOs;
}
