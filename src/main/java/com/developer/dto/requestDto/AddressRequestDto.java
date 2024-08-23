package com.developer.dto.requestDto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AddressRequestDto(

		@JsonProperty(value = "emp_city") String city,

		@JsonProperty(value = "emp_state") String state,

		@JsonProperty(value = "emp_district") String district,

		@JsonProperty(value = "emp_zip-code") Integer zipCode,

		@JsonProperty(value = "emp_id") Long empId) implements Serializable {

}
