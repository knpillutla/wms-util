package com.threedsoft.util.dto;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;
import com.fasterxml.jackson.annotation.JsonInclude;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@JsonInclude(JsonInclude.Include.NON_DEFAULT)
@NoArgsConstructor
@Data
@AllArgsConstructor
public class ErrorResourceDTO<T> {
	int responseCode;
	String errorMsg;
	T requestObj;
	
	public ErrorResourceDTO(int responseCode, String errorMsg) {
		this.responseCode=responseCode;
		this.errorMsg = errorMsg;
	}
}
