package com.pro.social.media.agent.wrapper;

import java.io.Serializable;

import com.pro.social.media.agent.controllerdto.Response;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class DecriptedWrapper extends Response implements Serializable {
	private static final long serialVersionUID = -4055925064027662526L;
	
	private String descripted;
}
