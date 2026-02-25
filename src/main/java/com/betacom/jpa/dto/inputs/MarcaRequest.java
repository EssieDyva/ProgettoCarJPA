package com.betacom.jpa.dto.inputs;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MarcaRequest {

	private Integer id;
	private String description;
}