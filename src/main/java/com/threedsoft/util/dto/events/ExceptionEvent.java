package com.threedsoft.util.dto.events;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.Data;
import lombok.NoArgsConstructor;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@NoArgsConstructor
@Data
public class ExceptionEvent extends WMSEvent{
	public String errorMsg;
	public Object exceptionObj;
	
	public ExceptionEvent(String eventName, String busName, Integer locnNbr, String company, String division, String busUnit, String busKey, String busVal, String serviceName,  String errorMsg) {
		this(eventName, busName, locnNbr, company, division, busUnit, busKey, busVal, null, null, serviceName, null, errorMsg, null);
	}
	public ExceptionEvent(String eventName, String busName, Integer locnNbr, String company, String division, String busUnit, String busKey, String busVal, String serviceName, Object requestObj,  String errorMsg, Exception exceptionObj) {
		this(eventName, busName, locnNbr, company, division, busUnit, busKey, busVal, null, null, serviceName, requestObj, errorMsg, exceptionObj);
	}
	public ExceptionEvent(String eventName, String busName, Integer locnNbr, String company, String division, String busUnit, String busKey, String busVal, String parentBusKey, String parentBusVal, String serviceName, Object requestObj, String errorMsg, Exception exceptionObj) {
		super(eventName, busName, locnNbr, company, division, busUnit, busKey, busVal, parentBusKey, parentBusVal, serviceName, requestObj);
		this.setFailed(true);
		this.errorMsg = errorMsg;
		this.exceptionObj = exceptionObj;
	}
}
