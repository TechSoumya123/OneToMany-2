package com.developer.dto.responseDto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EmployeeResponse(
		
		@JsonProperty(value = "emp_id")
		Long id,
		
		@JsonProperty(value = "emp_name")
		String name) {

}
