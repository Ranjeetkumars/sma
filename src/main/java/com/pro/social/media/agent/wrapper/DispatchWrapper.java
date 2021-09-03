package com.pro.social.media.agent.wrapper;

import java.io.Serializable;
import java.util.List;
import com.pro.social.media.agent.controllerdto.DispatchControllerDto;
import com.pro.social.media.agent.controllerdto.Response;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
@Getter
@Setter
@ToString
public class DispatchWrapper extends Response implements Serializable {
	private static final long serialVersionUID = -9144370667L;
	private List<DispatchControllerDto> controllerDto;

}
