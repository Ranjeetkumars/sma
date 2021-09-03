package com.pro.social.media.agent.wrapper;

import java.io.Serializable;
import java.util.List;


import com.pro.social.media.agent.controllerdto.ListOfDispatchRecordControllerDto;
import com.pro.social.media.agent.controllerdto.Response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Setter
@Getter
@ToString
public class ListOfDispatchRecordWrapper  extends Response implements Serializable {
	private static final long serialVersionUID = -1438L;
	private List<ListOfDispatchRecordControllerDto> controllerDto;


}
