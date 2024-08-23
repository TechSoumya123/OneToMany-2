package com.developer.dto.requestDto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public record EmployeeRequestDto(

		@JsonProperty(value = "emp_name") String employeeName) implements Serializable {

}
