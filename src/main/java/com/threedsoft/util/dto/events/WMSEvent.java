package com.threedsoft.util.dto.events;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@NoArgsConstructor
@Data
@Slf4j
public class WMSEvent<T> implements Serializable{
	public String eventName;
	public String busName;
	public Integer locnNbr;
	public String company;
	public String division;
	public String busUnit;
	public String busKey;
	public String busVal;
	public String parentBusKey;
	public String parentBusVal;
	public String eventResourceClassName;
	public T eventResource;
	public boolean isFailed = false;
	public String serviceName;
	public Map headerMap = new HashMap();
	
   @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
   @JsonSerialize(using = LocalDateTimeSerializer.class)
   @JsonDeserialize(using = LocalDateTimeDeserializer.class)
	public LocalDateTime createdDttm;

	public WMSEvent(String eventName, String busName, Integer locnNbr, String company, String division, String busUnit, String busKey, String busVal, String serviceName) {
		this(eventName, busName, locnNbr, company, division, busUnit, busKey, busVal, null, null, serviceName, null);
	}
	public WMSEvent(String eventName, String busName, Integer locnNbr, String company, String division, String busUnit, String busKey, String busVal, String serviceName, T resourceDTO) {
		this(eventName, busName, locnNbr, company, division, busUnit, busKey, busVal, null, null, serviceName, resourceDTO);
	}
	public WMSEvent(String eventName, String busName, Integer locnNbr, String company, String division, String busUnit, String busKey, String busVal, String parentBusKey, String parentBusVal, String serviceName, T resourceDTO) {
		this.eventName = eventName;
		this.busName = busName;
		this.locnNbr = locnNbr;
		this.company = company;
		this.division = division;
		this.busUnit = busUnit;
		this.busKey = busKey;
		this.busVal = busVal;
		this.parentBusKey = parentBusKey;
		this.parentBusVal = parentBusVal;
		this.eventResource = resourceDTO;
		this.eventResourceClassName = resourceDTO.getClass().getName();
		this.serviceName = serviceName;
		this.addHeader("eventName", this.eventName);
		this.addHeader("busName", this.busName);
		this.addHeader("locnNbr", this.locnNbr);
		this.addHeader("company", this.company);
		this.addHeader("division", this.division);
		this.addHeader("busUnit", this.busUnit);
		this.addHeader("busKey", this.busKey);
		this.addHeader("busVal", this.busVal);
		this.addHeader("parentBusKey", this.parentBusKey);
		this.addHeader("parentBusVal", this.parentBusVal);
		this.addHeader("serviceName", this.serviceName);
		this.addHeader("isFailed", "N");
		
	}

	public void setFailed(boolean failed) {
		if(failed) {
			this.isFailed = true;
			this.addHeader("isFailed", "Y");
		}
	}
	public void addHeader(String key, String val) {
		headerMap.put(key, val);
	}
	public void addHeader(String key, Object val) {
		headerMap.put(key, val);
	}
	public void setHeaderMap(Map headerMap) {
		if(headerMap != null) {
			this.headerMap.putAll(headerMap);
		}else {
			log.warn("HeaderMap passed in is null for event:" + this.getEventName() + ", no additonal headers will be set" );
		}
	}
}
