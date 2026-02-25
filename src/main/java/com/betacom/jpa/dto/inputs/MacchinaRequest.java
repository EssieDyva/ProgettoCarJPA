package com.betacom.jpa.dto.inputs;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MacchinaRequest {

	private Integer id;
	private Integer porte;
	private String targa;
	private Integer cilindrata;
}
