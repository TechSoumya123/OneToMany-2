package com.developer.dto.responseDto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

public record AddressResponse(

		@JsonProperty(value = "address_id") Long id, 
		
		@JsonProperty(value = "address_place") String name)
		implements Serializable {

}
